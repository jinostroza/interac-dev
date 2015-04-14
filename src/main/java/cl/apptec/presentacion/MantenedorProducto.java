package cl.apptec.presentacion;

import cl.apptec.entidades.*;
import cl.apptec.negocio.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.apptec.util.components.FacesUtil;
import cl.apptec.util.components.UserSession;
import cl.apptec.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorProducto implements Serializable {
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private List<Proveedor> proveedores;
    private List<Producto> productos;
    private List<Unidad> unidades;
    private List<EstadoProducto> estados;
    private List<CategoriaProducto> categorias;
    private List<CategoriaProducto> subcategorias;
    private List<Sucursal> sucursales;
    private TipoOperacion operacion;
    private Stock stock;
    private Proveedor proveedor;
    private Producto productoSeleccionado;
    private CategoriaProducto categoria;
    private Map<String, List<String>> pendientes;

    @Autowired
    private LogicaProducto logicaProducto;

    @Autowired
    private LogicaProveedor logicaProveedor;

    @Autowired
    private LogicaUnidad logicaUnidad;

    @Autowired
    private LogicaEstadoProducto logicaEstadoProducto;

    @Autowired
    private LogicaCategoriaProducto logicaCategoriaProducto;
    
    @Autowired
    private LogicaStock logicaStock;
    
    @Autowired
    private LogicaSucursal logicaSucursal;

    @Autowired
    private FileUploader fileUploader;

    @Autowired
    private UserSession sesion;

    public void inicio() {
       productos = logicaProducto.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        productoSeleccionado = new Producto();
        categoria = null;
        operacion = TipoOperacion.INGRESAR;
        cargaDatos();
        return "flowAgregar";
    }

    public String irEditar(Producto p) {
        productoSeleccionado = logicaProducto.obtenerConRelaciones(p);
        categoria = productoSeleccionado.getCategoriaProducto().getPadre();
        subcategorias = logicaCategoriaProducto.obtenerPorPadre(categoria);
        
        operacion = TipoOperacion.EDITAR;
        cargaDatos();
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    private void cargaDatos() {
        proveedores = logicaProveedor.obtenerTodos();
        unidades = logicaUnidad.obtenerTodos();
        estados = logicaEstadoProducto.obtenerTodos();
        categorias = logicaCategoriaProducto.obtenerCategoriasPadres();
        sucursales = logicaSucursal.obtenerTodos();
    }

    public boolean tieneImagenes() {
        return productoSeleccionado != null && productoSeleccionado.getImagenes() != null
                && !productoSeleccionado.getImagenes().isEmpty();
    }

    public void eliminarProducto() {
        logicaProducto.eliminarProducto(productoSeleccionado);
    }

    public void guardarProducto() {
        logicaProducto.guardarProducto(productoSeleccionado, proveedor, pendientes.get("tmp"+sesion.getUsuario().getIdUsuario()));
        Stock stock = new Stock();
        productoSeleccionado.setStockList(new ArrayList<Stock>());
        stock.setIdProducto(productoSeleccionado);
        productoSeleccionado.getStockList().add(stock);
        FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo producto [ID: " + productoSeleccionado.getIdProducto() + "]");
        productoSeleccionado = new Producto();
    }

    public void subirImagen(FileUploadEvent fue) {
        String path = fileUploader.subir(fue, "/tmp/"+sesion.getUsuario().getIdUsuario());
        if (pendientes == null) {
            pendientes = new HashMap<String, List<String>>();
            pendientes.put("tmp"+sesion.getUsuario().getIdUsuario(), new ArrayList<String>());
        }
        pendientes.get("tmp"+sesion.getUsuario().getIdUsuario()).add(path);
    }

    public void obtenerCategoriasSegundoNivel() {
        subcategorias = logicaCategoriaProducto.obtenerPorPadre(categoria);
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<Producto> getProducto() {
        return productos;
    }

    public void setProducto(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public TipoOperacion getOperacion() {
        return operacion;
    }

    public void setOperacion(TipoOperacion operacion) {
        this.operacion = operacion;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public List<EstadoProducto> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoProducto> estados) {
        this.estados = estados;
    }

    public List<CategoriaProducto> getCategorias() {
        return categorias;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public void setCategorias(List<CategoriaProducto> categorias) {
        this.categorias = categorias;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public List<CategoriaProducto> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<CategoriaProducto> subcategorias) {
        this.subcategorias = subcategorias;
    }    
}
