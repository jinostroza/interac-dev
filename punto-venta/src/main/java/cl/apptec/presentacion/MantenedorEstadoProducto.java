package cl.apptec.presentacion;

import cl.apptec.entidades.EstadoProducto;
import cl.apptec.negocio.LogicaEstadoProducto;
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
public class MantenedorEstadoProducto implements Serializable{
    private List<EstadoProducto> estadosProductos;
    private EstadoProducto epSeleccionado;
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaEstadoProducto logicaEstadoProducto;
    
    public void inicio() {
        estadosProductos = logicaEstadoProducto.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        epSeleccionado = new EstadoProducto();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(EstadoProducto s) {
        epSeleccionado = s;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarEstadoProducto() {
        logicaEstadoProducto.eliminarEstadoProducto(epSeleccionado);
        estadosProductos.remove(epSeleccionado);

    }

    public void guardarEstadoProducto() {
        if (operacion == TipoOperacion.INGRESAR) {
            if (!logicaEstadoProducto.existeEstadoProducto(epSeleccionado.getNombreEstadoProducto())) {
                logicaEstadoProducto.guardarEstadoProducto(epSeleccionado);
                FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo país [ID: " + epSeleccionado.getIdEstadoProducto() + "]");
                estadosProductos.add(epSeleccionado);
                epSeleccionado = new EstadoProducto();
            } else {
                FacesUtil.mostrarMensajeError("Operación fallida", "El estado de producto ya existe");
            }
        } else {
            logicaEstadoProducto.guardarEstadoProducto(epSeleccionado);
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado el estado de producto [ID: " + epSeleccionado.getIdEstadoProducto() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<EstadoProducto> getEstadoProducto() {
        return estadosProductos;
    }

    public void setEstadoProducto(List<EstadoProducto> estadosProductos) {
        this.estadosProductos = estadosProductos;
    }

    public EstadoProducto getEstadoProductoSeleccionado() {
        return epSeleccionado;
    }

    public void setEstadoProductoSeleccionado(EstadoProducto epSeleccionado) {
        this.epSeleccionado = epSeleccionado;
    }
}
