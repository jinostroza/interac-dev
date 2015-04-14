package cl.apptec.presentacion;

import cl.apptec.entidades.EntradaSalida;
import cl.apptec.negocio.LogicaEntradaSalida;
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
public class MantenedorEntradaSalida implements Serializable{
    private List<EntradaSalida> entradasSalidas;
    private EntradaSalida esSeleccionada;
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;

    @Autowired
    private LogicaEntradaSalida logicaES;
    
    public void inicio() {
        entradasSalidas = logicaES.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        esSeleccionada = new EntradaSalida();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(EntradaSalida es) {
        esSeleccionada = es;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarEntradaSalida() {
        logicaES.eliminarEntradaSalida(esSeleccionada);
    }

    public void guardarEntradaSalida() {
        logicaES.guardarEntradaSalida(esSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado la nueva Entrada/Salida [ID: "+esSeleccionada.getIdEntradaSalida()+"]");
            entradasSalidas.add(esSeleccionada);
            esSeleccionada = new EntradaSalida();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado la Entrada/Salida [ID: "+esSeleccionada.getIdEntradaSalida()+"]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<EntradaSalida> getEntradasSalidas() {
        return entradasSalidas;
    }

    public void setEntradasSalidas(List<EntradaSalida> entradasSalidas) {
        this.entradasSalidas = entradasSalidas;
    }

    public EntradaSalida getEsSeleccionada() {
        return esSeleccionada;
    }

    public void setEsSeleccionada(EntradaSalida esSeleccionada) {
        this.esSeleccionada = esSeleccionada;
    }
}
