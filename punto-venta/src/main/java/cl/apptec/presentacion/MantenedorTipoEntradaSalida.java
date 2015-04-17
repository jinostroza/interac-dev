package cl.apptec.presentacion;

import cl.apptec.entidades.TipoEntradaSalida;
import cl.apptec.negocio.LogicaTipoEntradaSalida;

import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author secabezas
 */
@Component
@Scope("flow")
public class MantenedorTipoEntradaSalida implements Serializable {
    private List<TipoEntradaSalida> tiposES;
    private TipoEntradaSalida tesSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaTipoEntradaSalida logicaTipoEntradaSalida;

    public void inicio() {
        tiposES = logicaTipoEntradaSalida.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        tesSeleccionado = new TipoEntradaSalida();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(TipoEntradaSalida tes) {
        tesSeleccionado = tes;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarTipoEntradaSalida() {
        tiposES.remove(tesSeleccionado);
        logicaTipoEntradaSalida.eliminarTipoEntradaSalida(tesSeleccionado);
    }

    public void guardarTipoEntradaSalida() {
        logicaTipoEntradaSalida.guardarTipoEntradaSalida(tesSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo tipo [ID: " + tesSeleccionado.getIdTipoEntradaSalida() + "]");
            tiposES.add(tesSeleccionado);
            tesSeleccionado = new TipoEntradaSalida();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado el tipo [ID: " + tesSeleccionado.getIdTipoEntradaSalida() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<TipoEntradaSalida> getTipoEntradaSalida() {
        return tiposES;
    }

    public void setTipoEntradaSalida(List<TipoEntradaSalida> tiposES) {
        this.tiposES = tiposES;
    }

    public TipoEntradaSalida getTipoEntradaSalidaSeleccionado() {
        return tesSeleccionado;
    }

    public void setTipoEntradaSalidaSeleccionado(TipoEntradaSalida tesSeleccionado) {
        this.tesSeleccionado = tesSeleccionado;
    }
}
