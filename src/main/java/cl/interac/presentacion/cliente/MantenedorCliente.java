package cl.interac.presentacion.cliente;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.MailSender;
import cl.interac.util.components.PropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
    private Campestab campestab;
    private Usuario usuario;
    private Establecimiento establecimiento;
    private Tipototem tipototem;
    private Contenido contenido;
    private Long numeroCampanas;

    //list
    private List<Totem> totemList;
    private List<Campana> campanaList;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientos;
    private List<Tipototem> tipototemList;
    private List<Totem> totemSeleccionados;
    private List<Totem> totemCampana;
    private List<Campestab> campestabs;
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
    private LogicaCampestab logicaCampestab;
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
    @Autowired
    private PropertyReader propertyReader;

    // inicio y Logica de vista

    public void inicio(){
        numeroCampanas = logicaCampestab.obtenerPorNumero(userSession.getUsuario().getUsername());
        totemCampana = logicaTotem.obtenerDeCampana(userSession.getUsuario().getUsername());
        campanaEnEspera = logicaCampana.obtenerPorEstado(userSession.getUsuario().getUsername());
        campestabs = logicaCampestab.obtenerPorEstado(userSession.getUsuario().getUsername());

    }

    public String aprobar(Campestab ca){
        try {
            String ambiente = propertyReader.get("ambiente");
            campestab = ca ;
            if ("desarrollo".equals(ambiente)) {
                String aprobado = "Aprobado";
                campestab.setEstado(aprobado);
                logicaCampestab.guardar(campestab);
                FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha aprobado el Campaña  [" + campestab.getCampana().getNombrecampana() + "]");
                campestabs = logicaCampestab.obtenerPorEstado(userSession.getUsuario().getUsername());
            }else if ("produccion".equals(ambiente)) {
                String aprobado = "Aprobado";
                campestab.setEstado(aprobado);
                logicaCampestab.guardar(campestab);

            for(Establecimiento et : ca.getCampana().getEstablecimientoList()) {
                String carpetaDestino = et.getCarpetaFtp();
                for(Contenido co : ca.getCampana().getContenidoList()) {

                    Files.copy(Paths.get("/home/ec2-user/media/interac/" + co.getPath()),
                            Paths.get("/home/ec2-user/media/" + carpetaDestino + "/" + co.getPath()));
                }
            }
                String[] destinos = new String[3];
                destinos[0] = userSession.getUsuario().getCorreo();
                destinos[1] = "contacto@interac.cl";


                //Cuerpo del mensaje
                String mensajeAnunciante = new String(constantes.getAprobar());
                mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$Id",String.valueOf(campestab.getCampana().getIdcampana()));
                mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$establecimiento","prueba");
                mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$numerodePantallas","4");
                mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$valormensual","4");
                mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$total", String.valueOf(9999999));


                mailSender.send(destinos, "Interac", mensajeAnunciante);

                campestabs = logicaCampestab.obtenerPorEstado(userSession.getUsuario().getUsername());
                FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha aprobado el Campaña  [" + campestab.getCampana().getNombrecampana() + "]");

            }
        }catch (Exception e){
            FacesUtil.mostrarMensajeError("Operación Fallida", "algo ocurrio");
        }

    return "notifica";
    }

    public String rechazar(Campestab ca){
        try {
            campestab = ca;
            String rechazado = "Rechazado";
            campestab.setEstado(rechazado);
            logicaCampestab.guardar(campestab);

            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha rechazado la Campaña  [" + campestab.getCampana().getNombrecampana() + "]");
            campestabs = logicaCampestab.obtenerPorEstado(userSession.getUsuario().getUsername());
        }catch (Exception e){
            FacesUtil.mostrarMensajeError("Operación Fallida","algo ocurrio");
        }

        String[] destinos = new String[3];
        destinos[0] = userSession.getUsuario().getCorreo();
        destinos[1] = "contacto@interac.cl";
        //destinos[2] = contenido.getUsuario().getCorreo();

        //Cuerpo del mensaje
        String mensajeAnunciante = new String(constantes.getRechazar());
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$id",String.valueOf(campestab.getCampana().getIdcampana()));
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$establecimiento", "prueba");
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$razonRechazo",rechazarSelectOneMenu);
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$comentarios",rechazarInputTextArea);
        mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$valor", "1");

        mailSender.send(destinos, "interac " + rechazarSelectOneMenu, mensajeAnunciante);

        campestabs = logicaCampestab.obtenerPorEstado(userSession.getUsuario().getUsername());
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ah enviado la notificacion via email  ");
        return "notifica";
    }



   // inicio y logica de vista

    //Getter and Setter
    public Long getNumeroCampanas() { return numeroCampanas; }

    public void setNumeroCampanas(Long numeroCampanas) {
        this.numeroCampanas = numeroCampanas;
    }

    public List<Campestab> getCampestabs() {
        return campestabs;
    }

    public void setCampestabs(List<Campestab> campestabs) {
        this.campestabs = campestabs;
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

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public Campestab getCampestab() {
        return campestab;
    }

    public void setCampestab(Campestab campestab) {
        this.campestab = campestab;
    }
}
