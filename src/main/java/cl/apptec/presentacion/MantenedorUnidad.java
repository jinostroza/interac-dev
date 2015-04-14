package cl.apptec.presentacion;

import cl.apptec.entidades.Unidad;
import cl.apptec.negocio.LogicaUnidad;
import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorUnidad implements Serializable{
    private List<Unidad> unidades;
    private Unidad unidadSeleccionada;
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaUnidad logicaUnidad;
    
    public void inicio() {
        unidades = logicaUnidad.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        unidadSeleccionada = new Unidad();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(Unidad u) {
        unidadSeleccionada = u;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarUnidad() {
        unidades.remove(unidadSeleccionada);
        logicaUnidad.eliminarUnidad(unidadSeleccionada);

    }

    public void guardarUnidad() {
        logicaUnidad.guardarUnidad(unidadSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado la nueva unidad de medida [ID: "+unidadSeleccionada.getIdUnidad()+"]");
            unidades.add(unidadSeleccionada);
            unidadSeleccionada = new Unidad();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operación existosa","Se ha editado la unidad de medida [ID: "+unidadSeleccionada.getIdUnidad()+"]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<Unidad> getUnidad() {
        return unidades;
    }

    public void setUnidad(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public Unidad getUnidadSeleccionada() {
        return unidadSeleccionada;
    }

    public void setUnidadSeleccionada(Unidad unidadSeleccionada) {
        this.unidadSeleccionada = unidadSeleccionada;
    }
}
