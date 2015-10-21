package cl.interac.presentacion.cliente;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
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
    private Usuario usuario;
    private Establecimiento establecimiento;
    private Tipototem tipototem;
    private Contenido contenido;
    //list
    private List<Totem> totemList;
    private List<Campana> campanaList;
    private List<Establecimiento> establecimientoList;
    private List<Tipototem> tipototemList;
    private List<Totem> totemSeleccionados;
    private List<Totem> totemCampana;
    private List<Campana> campanaEnEspera;
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

    // inicio y Logica de vista

    public void inicio(){
        totemCampana = logicaTotem.obtenerDeCampana(userSession.getUsuario().getUsername());
        campanaList= logicaCampana.obtenerLasCampanasDeLosTotems(userSession.getUsuario().getUsername());
        campanaEnEspera = logicaCampana.obtenerPorEstado(userSession.getUsuario().getUsername());

    }

    public void aprobar(Campana c){
        try {
            campana =c ;
            String aprobado = "aprobado";
            campana.getContenido().setEstado(aprobado);
            logicaCampana.guardarCampana(c);
            campanaEnEspera.clear();
            campanaEnEspera = logicaCampana.obtenerPorEstado(userSession.getUsuario().getUsername());
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha rechazado campaña  [" +campana.getContenido().getNombrecont() + "]");

        }catch (Exception e){
            FacesUtil.mostrarMensajeError("Operación Fallida", "algo ocurrio");
        }
    }

    public void rechazar(Campana c){
        try {

            campana= c ;
            String rechazado = "rechazado";
            campana.getContenido().setEstado(rechazado);
            campanaEnEspera.clear();
            campanaEnEspera = logicaCampana.obtenerPorEstado(userSession.getUsuario().getUsername());
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha rechazado campaña  [" + campana.getContenido().getNombrecont() + "]");

        }catch (Exception e){
            FacesUtil.mostrarMensajeError("Operación Fallida","algo ocurrio");
        }
    }


   // inicio y logica de vista
//getter and setter



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
}
