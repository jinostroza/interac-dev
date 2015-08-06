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

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pedro Pablo on 22-05-2015.
 */
@Component
@Scope("prototype")
public class MantenedorEstablecimiento implements Serializable {

    public enum TipoOperacion {
        INSERTAR,
        EDITAR,
    }

    //manejo manual
    private TipoOperacion operacion;
    private List<Establecimiento> establecimientoList;
    private List<Totem> totems;
    private List<Ubicacion> ubicaciones;
    private List<Usuario> usuario;
    private String text1;
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

    public boolean esEditar() {
        return operacion == TipoOperacion.EDITAR;
    }

    public boolean esAgregar() {
        return operacion == TipoOperacion.INSERTAR;
    }

    public void buscar(String text1) {
        logicaEstablecimiento.buscar(text1);
    }

    public void inicio() {
        establecimientoList = logicaEstablecimiento.obtenerTodos();
        ubicaciones = logicaUbicacion.obtenerTodas();
        establecimiento = new Establecimiento();
    }

   public void eliminar(Establecimiento establecimiento){
       logicaEstablecimiento.eliminar(establecimiento);
   }


    public MantenedorEstablecimiento() {
        new Establecimiento();
    }

    public void agregarEstablecimiento() {
        operacion = TipoOperacion.INSERTAR;
        logicaEstablecimiento.guardar(establecimiento);
        establecimiento.setUsuario(userSession.getUsuario());
        logicaUbicacion.guardar(ubicacion);
        if (esAgregar()) {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el establecimiento [" + establecimiento.getNombreEstablecimiento() + "]");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el establecimiento [" + establecimiento.getNombreEstablecimiento() + "]");
        }

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

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }
}
