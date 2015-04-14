package cl.apptec.presentacion;

import cl.apptec.entidades.Comuna;
import cl.apptec.entidades.Proveedor;
import cl.apptec.negocio.LogicaProveedor;
import cl.apptec.negocio.LogicaComuna;

import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorProveedor implements Serializable {
    private List<Proveedor> proveedores;
    private List<Comuna> comunas;
    private Proveedor proveedorSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    
    private TipoOperacion operacion;

    @Autowired
    private LogicaProveedor logicaProveedor;
    
    @Autowired
    private LogicaComuna logicaComuna;
    
    public void inicio() {
        proveedores = logicaProveedor.obtenerTodos();
        comunas = logicaComuna.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        proveedorSeleccionado = new Proveedor();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Proveedor s) {
        proveedorSeleccionado = s;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarProveedor() {
        proveedores.remove(proveedorSeleccionado);
        logicaProveedor.eliminarProveedor(proveedorSeleccionado);
    }

    public void guardarProveedor() {
        logicaProveedor.guardarProveedor(proveedorSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado el nuevo proveedor [ID: "+proveedorSeleccionado.getIdProveedor()+"]");
            proveedores.add(proveedorSeleccionado);
            proveedorSeleccionado = new Proveedor();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operación existosa","Se ha editado el proveedor [ID: "+proveedorSeleccionado.getIdProveedor()+"]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<Proveedor> getProveedor() {
        return proveedores;
    }

    public void setProveedor(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public List<Comuna> getComunas() {
        return comunas;
    }

    public void setComunas(List<Comuna> comunas) {
        this.comunas = comunas;
    }
    
}
