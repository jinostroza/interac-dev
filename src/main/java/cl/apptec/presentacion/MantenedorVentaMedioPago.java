package cl.apptec.presentacion;

import cl.apptec.entidades.VentaMedioPago;
import cl.apptec.negocio.LogicaVentaMedioPago;
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
public class MantenedorVentaMedioPago implements Serializable {
    private List<VentaMedioPago> vmps;
    private VentaMedioPago vmpSeleccionada;
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaVentaMedioPago logicaVentaMedioPago;
    
    public void inicio() {
        vmps = logicaVentaMedioPago.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        vmpSeleccionada = new VentaMedioPago();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(VentaMedioPago p) {
        vmpSeleccionada = p;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarVentaMedioPago() {
        vmps.remove(vmpSeleccionada);
        logicaVentaMedioPago.eliminarVentaMedioPago(vmpSeleccionada);
    }

    public void guardarVentaMedioPago() {
        logicaVentaMedioPago.guardarVentaMedioPago(vmpSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado la nueva venta [ID: "+vmpSeleccionada.getIdVentaMedioDePago()+"]");
            vmps.add(vmpSeleccionada);
            vmpSeleccionada = new VentaMedioPago();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado la venta [ID: "+vmpSeleccionada.getIdVentaMedioDePago()+"]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<VentaMedioPago> getVMPs() {
        return vmps;
    }

    public void setVMPs(List<VentaMedioPago> vmps) {
        this.vmps = vmps;
    }

    public VentaMedioPago getVentaMedioPagoSeleccionada() {
        return vmpSeleccionada;
    }

    public void setVentaMedioPagoSeleccionada(VentaMedioPago vmpSeleccionada) {
        this.vmpSeleccionada = vmpSeleccionada;
    }
}
