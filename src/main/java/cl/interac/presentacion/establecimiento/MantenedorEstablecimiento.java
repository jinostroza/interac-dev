package cl.interac.presentacion.establecimiento;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaTotem;
import cl.interac.negocio.LogicaUbicacion;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Pedro Pablo on 22-05-2015.
 */
@Component
@Scope("view")
public class MantenedorEstablecimiento implements Serializable {
    //manejo manual
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientoConfiltro;
    private List<Totem> totems;
    private List<Ubicacion> ubicaciones;
    private List<Usuario> usuario;

    private int idestable;
    private Establecimiento establecimiento;
    private Ubicacion ubicacion;

    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaUbicacion logicaUbicacion;
    @Autowired
    private LogicaTotem logicaTotem;


    @PostConstruct
    public void inicio() {
        establecimientoList = logicaEstablecimiento.obtenerConRelacion();
        ubicaciones = logicaUbicacion.obtenerTodas();
        establecimiento = new Establecimiento();
    }

    public void eliminar(Establecimiento establecimiento){
        logicaEstablecimiento.eliminar(establecimiento);
    }

    public void agregarEstablecimiento() {
        establecimiento.setUsuario(userSession.getUsuario());
        establecimiento.setUbicacion(ubicacion);
        logicaEstablecimiento.guardar(establecimiento);
        establecimientoList = logicaEstablecimiento.obtenerConRelacion();

        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el establecimiento [" + establecimiento.getNombreEstablecimiento() + "]");
    }

    public void editarEstablecimiento(Establecimiento e) {
        establecimiento = e;
        logicaEstablecimiento.guardar(establecimiento);
        establecimientoList = logicaEstablecimiento.obtenerConRelacion();

        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el establecimiento [" + establecimiento.getNombreEstablecimiento() + "]");
    }

    public List<Totem> getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
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

    public int getIdestable() {
        return idestable;
    }

    public void setIdestable(int idestable) {
        this.idestable = idestable;
    }
}

