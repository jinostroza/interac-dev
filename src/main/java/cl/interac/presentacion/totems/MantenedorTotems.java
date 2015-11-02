package cl.interac.presentacion.totems;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorTotems implements Serializable {

    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaTipototem logicaTipototem;
    @Autowired
    private LogicaTotem logicaTotemConUsuario;
    @Autowired
    private UserSession userSession;

    private MapModel simpleModel;
    private Marker marker;

    private List<Totem> totems;
    private List<Tipototem> tipototems;
    private List<Totem> totemConFiltro;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientoConfiltro;
    private Totem totem;
    private Establecimiento establecimiento;
    private Tipototem tipototem;
    private List<Totem> totemPorUsuario;



    @PostConstruct
    public void inicio() {
        totems = logicaTotem.obtenerConRelacion();
        establecimientoList = logicaEstablecimiento.obtenerTodos();
        tipototems = logicaTipototem.obtenerTodos();
        totemPorUsuario = logicaTotemConUsuario.obtenerPorUsuario(userSession.getUsuario().getUsername());

        totem = new Totem();
    }



    // logica vista
    public void agregarTotem() {
        totem.setEstablecimiento(establecimiento);
        totem.setLat(establecimiento.getLat());
        totem.setLongi(establecimiento.getLongi());
        totem.setTipototem(tipototem);
        System.err.println("totem e: " + totem.getEstablecimiento());
        logicaTotem.guardar(totem);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el Totem [" + totem.getNoserie() + "]");

    }

    public void editarTotem(Totem t){
        totem = t;
        logicaTotem.guardar(totem);
        totems = logicaTotem.obtenerConRelacion();
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha Lobeznisado el Totem [" + totem.getNoserie() + "]");

    }



    public void eliminarTotem(Totem totem) {
        logicaTotem.eliminarTotem(totem);
    }
    //get and set

    public List<Totem> getTotemConFiltro() {
        return totemConFiltro;
    }

    public void setTotemConFiltro(List<Totem> totemConFiltro) {
        this.totemConFiltro = totemConFiltro;
    }

    public List<Totem> getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }

    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    public List<Establecimiento> getEstablecimientoConfiltro() {
        return establecimientoConfiltro;
    }

    public void setEstablecimientoConfiltro(List<Establecimiento> establecimientoConfiltro) {
        this.establecimientoConfiltro = establecimientoConfiltro;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public List<Tipototem> getTipototems() {
        return tipototems;
    }

    public void setTipototems(List<Tipototem> tipototems) {
        this.tipototems = tipototems;
    }

    public Tipototem getTipototem() {
        return tipototem;
    }

    public void setTipototem(Tipototem tipototem) {
        this.tipototem = tipototem;
    }

    public List<Totem> getTotemPorUsuario() {
        return totemPorUsuario;
    }

    public void setTotemPorUsuario(List<Totem> totemPorUsuario) {
        this.totemPorUsuario = totemPorUsuario;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}