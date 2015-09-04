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
import java.util.List;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("view")
public class MantenedorTotems implements Serializable {

    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;

    @Autowired
    private LogicaTipototem logicaTipototem;

    private MapModel simpleModel;
    private List<Totem> totems;
    private List<Tipototem> tipototems;
    private List<Totem> totemConFiltro;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientoConfiltro;

    private Totem totem;
    private Establecimiento establecimiento;
    private Tipototem tipototem;

    @PostConstruct
    public void inicio() {
        totems = logicaTotem.obtenerConRelacion();
        establecimientoList = logicaEstablecimiento.obtenerTodos();
        tipototems = logicaTipototem.obtenerTodos();
        totem = new Totem();

    }
    // logica vista
    public void agregarTotem() {
        totem.setEstablecimiento(establecimiento);
        totem.setTipototem(tipototem);
        System.err.println("totem e: "+totem.getEstablecimiento());
        logicaTotem.guardar(totem);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el Totem [" + totem.getNoserie() + "]");

    }

    public void editarTotem(Totem t){
        totem = t;
        logicaTotem.guardar(totem);
        totems = logicaTotem.obtenerConRelacion();
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha Lobeznisado el Totem [" + totem.getNoserie() + "]");

    }
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        //Shared coordinates
        LatLng coord1 = new LatLng(-33.044740, -71.613915);
        LatLng coord2 = new LatLng(-33.044740, -72.613915);
        LatLng coord3 = new LatLng(-34.044740, -71.613915);
        LatLng coord4 = new LatLng(-33.044740, -71.613800);

        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
        simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
        simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
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
}




