package cl.interac.presentacion.totems;

import cl.interac.entidades.Establecimiento;
import cl.interac.entidades.Totem;
import cl.interac.entidades.Ubicacion;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaTotem;
import cl.interac.negocio.LogicaUbicacion;
import cl.interac.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("prototype")
public class MantenedorTotems implements Serializable{

    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUbicacion logicaUbicacion;

    private Establecimiento establecimiento;
    private List<Establecimiento> establecimientoList;
    private Totem totem;
    private List<Totem> totems;
    private List<Totem> totemConFiltro;
    private Ubicacion ubicacion;


    // logica vista
    public MantenedorTotems() {
        totem = new Totem();
    }

    public void agregarTotem(){
        logicaTotem.guardar(totem);
        totem.setEstablecimiento(establecimiento);
        logicaUbicacion.guardar(ubicacion);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha Lobesnizado el Totem [" + totem.getNoserie() + "]");

    }

     public void eliminarTotem(){
             totems.remove(totem);
             logicaTotem.eliminarTotem(totem);
         }
    public void editarTotem(Totem t){

        totem = t;
        logicaTotem.guardar(totem);
        totems = logicaTotem.obtenerTodos();
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el Totem [" + totem.getNoserie() + "]");
    }

    public void inicio(){
        totems = logicaTotem.obtenerConRelacion();
        establecimientoList = logicaEstablecimiento.obtenerTodos();
        totem = new Totem();
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
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}




