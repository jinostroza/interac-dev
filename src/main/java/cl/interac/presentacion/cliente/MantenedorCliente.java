package cl.interac.presentacion.cliente;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.EmailUtils;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by pclucho on 28-09-15.
 */
@Component
@Scope("flow")
public class MantenedorCliente implements Serializable {

    //entities
    private Totem totem;
    private Totem totemSeleccionado;
    private Campana campana;
    private Usuario usuario;
    private Establecimiento establecimiento;
    private Tipototem tipototem;
    private Contenido contenido;
    private Long numeroCampanas;

    //list
    private List<Totem> totemList;
    private List<Campana> campanaList;
    private List<Establecimiento> establecimientoList;
    private List<Tipototem> tipototemList;
    private List<Totem> totemSeleccionados;
    private List<Totem> totemCampana;
    private List<Campana> campanaEnEspera;
    private List<Campana> traerNuevaCampana;
    private Totem[] totems;

    //Variables
    private String rechazarSelectOneMenu;
    private String rechazarInputTextArea;

    //autowired
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaTipototem logicaTipototem;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private Constantes constantes;

    // inicio y Logica de vista

    public void inicio(){
        numeroCampanas = logicaCampana.obtenerPorNumero(userSession.getUsuario().getUsername());
        totemCampana = logicaTotem.obtenerDeCampana(userSession.getUsuario().getUsername());
        campanaList= logicaCampana.obtenerLasCampanasDeLosTotems(userSession.getUsuario().getUsername());
        campanaEnEspera = logicaCampana.obtenerPorEstado(userSession.getUsuario().getUsername());
    }

    public void aprobar(Campana ca,Contenido c){
        try {
            campana = ca ;
            contenido = c;
            String aprobado = "Aprobado";
            campana.setEstado(aprobado);
            logicaCampana.guardarCampana(campana);
            String carpetaDestino = campana.getEstablecimiento().getCarpetaFtp();
            Files.copy(Paths.get("/home/ec2-user/media/interac/" + contenido.getPath()),
                    Paths.get("/home/ec2-user/media/" + carpetaDestino + "/" + contenido.getPath()));
        }catch (Exception e){
            FacesUtil.mostrarMensajeError("Operación Fallida", "algo ocurrio");
        }

        String[] destinos = new String[3];
        destinos[0] = userSession.getUsuario().getCorreo();
        destinos[1] = "contacto@interac.cl";
        destinos[2] = campana.getContenido().getUsuario().getCorreo();

        //Cuerpo del mensaje
        String mensajeAnunciante = new String(constantes.getAprobar());
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$Id",String.valueOf(campana.getIdcampana()));
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$establecimiento",campana.getEstablecimiento().getNombreEstablecimiento());
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$numerodePantallas",String.valueOf(campana.getEstablecimiento().getNumeroPantallas()));
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$valormensual",String.valueOf(campana.getEstablecimiento().getValorMensual()));
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$total", String.valueOf(9999999));


        mailSender.send(destinos,"Interac",mensajeAnunciante);

        logicaContenido.guardar(contenido);
        campanaEnEspera.clear();
        campanaEnEspera = logicaCampana.obtenerPorEstado(userSession.getUsuario().getUsername());
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha aprobado el anuncio  [" + campana.getContenido().getNombrecont() + "]");
    }

    public void rechazar(Campana ca , Contenido c){
        try {
            campana = ca;
            contenido = c ;
            String rechazado = "Rechazado";
            campana.setEstado(rechazado);
            logicaCampana.guardarCampana(campana);

            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha rechazado el anuncio  [" + campana.getContenido().getNombrecont() + "]");

        }catch (Exception e){
            FacesUtil.mostrarMensajeError("Operación Fallida","algo ocurrio");
        }

        String[] destinos = new String[3];
        destinos[0] = userSession.getUsuario().getCorreo();
        destinos[1] = "pe.pastene@gmail.com";
        destinos[2] = contenido.getUsuario().getCorreo();

        //Cuerpo del mensaje
        String mensajeAnunciante = new String(constantes.getRechazar());
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$Id",String.valueOf(campana.getIdcampana()));
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$establecimiento",campana.getEstablecimiento().getNombreEstablecimiento());
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$razonRechazo",rechazarSelectOneMenu);
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$comentarios",rechazarInputTextArea);

        mailSender.send(destinos, "interac " + rechazarSelectOneMenu, mensajeAnunciante);

        campanaEnEspera.clear();
        campanaEnEspera = logicaCampana.obtenerPorEstado(userSession.getUsuario().getUsername());
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha rechazado el anuncio  [" + campana.getContenido().getNombrecont() + "]");
    }


   // inicio y logica de vista
//getter and setter


    public Long getNumeroCampanas() {
        return numeroCampanas;
    }

    public void setNumeroCampanas(Long numeroCampanas) {
        this.numeroCampanas = numeroCampanas;
    }

    public List<Campana> getCampanaEnEspera() {
        return campanaEnEspera;
    }

    public void setCampanaEnEspera(List<Campana> campanaEnEspera) {
        this.campanaEnEspera = campanaEnEspera;
    }

    public List<Totem> getTotemCampana() {
        return totemCampana;
    }

    public void setTotemCampana(List<Totem> totemCampana) {
        this.totemCampana = totemCampana;
    }

    public void setTotemSeleccionado(Totem totemSeleccionado) {
        this.totemSeleccionado = totemSeleccionado;
    }

    public List<Totem> getTotemSeleccionado() {
        return totemSeleccionados;
    }

    public void setTotemSeleccionado(List<Totem> totemSeleccionado) {
        this.totemSeleccionados = totemSeleccionados;
    }

    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    public Campana getCampana() {
        return campana;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public List<Totem> getTotemList() {
        return totemList;
    }

    public void setTotemList(List<Totem> totemList) {
        this.totemList = totemList;
    }

    public List<Campana> getCampanaList() {
        return campanaList;
    }

    public void setCampanaList(List<Campana> campanaList) {
        this.campanaList = campanaList;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    public String getRechazarSelectOneMenu() { return rechazarSelectOneMenu; }

    public void setRechazarSelectOneMenu(String rechazarSelectOneMenu) {
        this.rechazarSelectOneMenu = rechazarSelectOneMenu;
    }

    public String getRechazarInputTextArea() { return rechazarInputTextArea; }

    public void setRechazarInputTextArea(String rechazarInputTextArea) {
        this.rechazarInputTextArea = rechazarInputTextArea;
    }
}
