package cl.apptec.presentacion;

import cl.apptec.entidades.ProductoProveedor;
import cl.apptec.negocio.LogicaProductoProveedor;

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
public class MantenedorProductoProveedor implements Serializable {
    private List<ProductoProveedor> productoProveedores;
    private ProductoProveedor ppSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaProductoProveedor logicaProductoProveedor;

    public void inicio() {
        productoProveedores = logicaProductoProveedor.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        ppSeleccionado = new ProductoProveedor();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(ProductoProveedor s) {
        ppSeleccionado = s;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarProductoProveedor() {
        logicaProductoProveedor.eliminarProductoProveedor(ppSeleccionado);
    }

    public void guardarProductoProveedor() {
        logicaProductoProveedor.guardarProductoProveedor(ppSeleccionado);
        FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo Producto Proveedor [ID: " + ppSeleccionado.getIdProductoProveedor() + "]");
        ppSeleccionado = new ProductoProveedor();
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<ProductoProveedor> getProductoProveedor() {
        return productoProveedores;
    }

    public void setProductoProveedor(List<ProductoProveedor> productoProveedores) {
        this.productoProveedores = productoProveedores;
    }

    public ProductoProveedor getProductoProveedorSeleccionado() {
        return ppSeleccionado;
    }

    public void setProductoProveedorSeleccionado(ProductoProveedor ppSeleccionado) {
        this.ppSeleccionado = ppSeleccionado;
    }
}
