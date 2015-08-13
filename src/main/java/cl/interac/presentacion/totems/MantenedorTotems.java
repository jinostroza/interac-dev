package cl.interac.presentacion.totems;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaTotem;
import cl.interac.negocio.LogicaUbicacion;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
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
public class MantenedorTotems implements Serializable {

    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUbicacion logicaUbicacion;

    public enum TipoOperacion {
        INSERTAR,
        EDITAR,
    }

    private TipoOperacion operacion;
    private Totem totem;
    private List<Totem> totems;
    private List<Totem> totemConFiltro;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientoConfiltro;
    private Establecimiento establecimiento;

    public boolean esEditar() {
        return operacion == TipoOperacion.EDITAR;
    }


    public boolean esAgregar() {
        return operacion == TipoOperacion.INSERTAR;
    }

    // logica vista

   public void editarTotem(Totem t){
       operacion = TipoOperacion.EDITAR;

       totem = t;
       logicaTotem.guardar(totem);
       totems = logicaTotem.obtenerConRelacion();

       FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el establecimiento [" + totem.getNoserie() + "]");
   }

    public void agregarTotem() {
        operacion = TipoOperacion.INSERTAR;
        totem.setEstablecimiento(establecimiento);
        System.err.println("totem e: "+totem.getEstablecimiento());
        totems = logicaTotem.obtenerConRelacion();
        logicaTotem.guardar(totem);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el Totem [" + totem.getNoserie() + "]");
        
    }

    public void eliminarTotem(Totem totem) {

        logicaTotem.eliminarTotem(totem);
    }


    public void inicio() {
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


    public TipoOperacion getOperacion() {
        return operacion;
    }

    public void setOperacion(TipoOperacion Operacion) {
        operacion = Operacion;
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


}




