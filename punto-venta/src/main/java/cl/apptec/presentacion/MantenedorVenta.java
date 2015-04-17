package cl.apptec.presentacion;

import cl.apptec.entidades.CambioMoneda;
import cl.apptec.entidades.CategoriaVenta;
import cl.apptec.entidades.Cliente;
import cl.apptec.entidades.DetalleVenta;
import cl.apptec.entidades.EstadoVenta;
import cl.apptec.entidades.Moneda;
import cl.apptec.entidades.Producto;
import cl.apptec.entidades.Sucursal;
import cl.apptec.entidades.Usuario;
import cl.apptec.entidades.Venta;
import cl.apptec.negocio.LogicaCambioMoneda;
import cl.apptec.negocio.LogicaCategoriaVenta;
import cl.apptec.negocio.LogicaCliente;
import cl.apptec.negocio.LogicaEstadoVenta;
import cl.apptec.negocio.LogicaMoneda;
import cl.apptec.negocio.LogicaProducto;
import cl.apptec.negocio.LogicaSucursal;
import cl.apptec.negocio.LogicaUsuario;
import cl.apptec.negocio.LogicaVenta;
import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import cl.apptec.util.components.UserSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorVenta implements Serializable {
    private List<Venta> ventas;
    private List<CategoriaVenta> categoriasVenta;
    private List<EstadoVenta> estadosVenta;
    private List<Moneda> monedas;
    private List<CambioMoneda> cambiosMoneda;
    private List<Usuario> usuarios;
    private List<Sucursal> sucursales;
    private Venta ventaSeleccionada;
    private UserSession usuarioLogueado;
    private List<Producto> productos;
    private Producto producto;
    
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    private String rutCliente;
    
    @Autowired
    private LogicaVenta logicaVenta;
    @Autowired
    private LogicaCategoriaVenta logicacategoriaVenta;
    @Autowired
    private LogicaEstadoVenta logicaestadoVenta;
    @Autowired
    private LogicaMoneda logicamoneda;
    @Autowired
    private LogicaCambioMoneda logicaCambioMoneda;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private LogicaSucursal logicaSucursal;
    @Autowired
    private LogicaCliente logicaCliente;
    @Autowired
    private LogicaProducto logicaProducto;
    @Autowired
    private UserSession sesion;
    
    public void inicio() {
        ventas = logicaVenta.obtenerTodos();
        categoriasVenta = logicacategoriaVenta.obtenerTodos();
        estadosVenta = logicaestadoVenta.obtenerTodos();
        monedas = logicamoneda.obtenerTodos();
        cambiosMoneda = logicaCambioMoneda.obtenerTodos();
        usuarios = logicaUsuario.obtenerTodos();
        sucursales = logicaSucursal.obtenerTodos();
        
        // hardcoder time
        if (sesion.getUsuario() == null) {
            sesion.setUsuario(new Usuario()); // obviamente esto despues desparece
            sesion.getUsuario().setNombresPersonaUsuario("DEMO");
            sesion.getUsuario().setIdSucursal(logicaSucursal.obtenerPorId(2)); // no se cual tiene stock xD
        }
        productos = logicaProducto.obtenerPorSucursal(sesion.getUsuario().getIdSucursal());
    }

    // flows
    public String irAgregar() {
        ventaSeleccionada = new Venta();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(Venta p) {
        ventaSeleccionada = p;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void agregarProducto() {
        if (ventaSeleccionada.getDetalleVentaList()==null) ventaSeleccionada.setDetalleVentaList(new ArrayList<DetalleVenta>());
        boolean encontrado = false;
        for (DetalleVenta dv : ventaSeleccionada.getDetalleVentaList()) {
            if (dv.getIdProducto().equals(producto)) {
                dv.setCantidadDetalleVenta(dv.getCantidadDetalleVenta()+1); // o en realidad podriamos agregar un campo que tenga las unidades
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            DetalleVenta dv = new DetalleVenta();
            dv.setIdProducto(producto);
            dv.setCantidadDetalleVenta(1); // o parametro desde la vista, para no tener que estar agregando el mismo varias veces
            dv.setIdVenta(ventaSeleccionada);
            dv.setPrecioUnitarioDetalleVenta(1); // estamos generosos
            dv.setNombreProducto(null); // campo innecesario por POO
            ventaSeleccionada.getDetalleVentaList().add(dv);
        }
    }
    
    public boolean filtrarProductos(Object valor, Object filtro, Locale idioma) {
        if (valor == null) return false;
        if (filtro == null) return true;
        if (((Producto)valor).getNombreProducto().contains(filtro.toString())) return true;
        else if (String.valueOf(((Producto)valor).getIdProducto()).contains(filtro.toString())) return true; // recordar que por ahora estamos usando ID, pero los productos debieran tener código
        else return false;
    }
    
    public void procesarRutCliente() {
        System.err.println("LLEGA CON FORMATO: "+rutCliente);
        int rut = Integer.valueOf(rutCliente.split("-")[0].replaceAll("[^0-9]",""));
        System.err.println("OBTENEMOS SOLO EL ENTERO "+rut);
        Cliente c = logicaCliente.obtenerPorRut(rut);
        // que faltaria aca
        if (c == null) {
            c = new Cliente();
            c.setRut(rut);
        }
        ventaSeleccionada.setIdCliente(c);
        // la gracia que en la vista podrás usar los otros campos con
        // mantenedorVenta.ventaSeleccionada.nombresCliente pq este método llenará los valores
        //
        System.err.println("OBTUVIMOS: "+c);
    }
   
    public void eliminarVenta() {
        ventas.remove(ventaSeleccionada);
        logicaVenta.eliminarVenta(ventaSeleccionada);
    }

    public void guardarVenta() {
        ventaSeleccionada.setIdUsuario(usuarioLogueado.getUsuario());
        ventaSeleccionada.setIdSucursal(ventaSeleccionada.getIdUsuario().getIdSucursal());
        ventaSeleccionada.setIdCambiomoneda(null);        
        ventaSeleccionada.setFechaVenta(Calendar.getInstance().getTime());
        logicaVenta.guardarVenta(ventaSeleccionada);
       
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado la nueva venta [ID: "+ventaSeleccionada.getIdVenta()+"]");
            ventas.add(ventaSeleccionada);
            ventaSeleccionada = new Venta();
            
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado la venta [ID: "+ventaSeleccionada.getIdVenta()+"]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Venta getVentaSeleccionada() {
        return ventaSeleccionada;
    }

    public void setVentaSeleccionada(Venta ventaSeleccionada) {
        this.ventaSeleccionada = ventaSeleccionada;
    }

    public List<CategoriaVenta> getCategoriasVenta() {
        return categoriasVenta;
    }

    public void setCategoriasVenta(List<CategoriaVenta> categoriasVenta) {
        this.categoriasVenta = categoriasVenta;
    }

    public List<EstadoVenta> getEstadosVenta() {
        return estadosVenta;
    }

    public void setEstadosVenta(List<EstadoVenta> estadosVenta) {
        this.estadosVenta = estadosVenta;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public List<CambioMoneda> getCambiosMoneda() {
        return cambiosMoneda;
    }

    public void setCambiosMoneda(List<CambioMoneda> cambiosMoneda) {
        this.cambiosMoneda = cambiosMoneda;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
