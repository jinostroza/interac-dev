/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Compra;
import cl.apptec.entidades.EstadoVenta;
import cl.apptec.entidades.Impuesto;
import cl.apptec.entidades.MedioPago;
import cl.apptec.entidades.Moneda;
import cl.apptec.entidades.Proveedor;
import cl.apptec.entidades.Sucursal;
import cl.apptec.entidades.Usuario;
import cl.apptec.negocio.LogicaCompra;
import cl.apptec.negocio.LogicaEstadoVenta;
import cl.apptec.negocio.LogicaImpuesto;
import cl.apptec.negocio.LogicaMedioPago;
import cl.apptec.negocio.LogicaMoneda;
import cl.apptec.negocio.LogicaProveedor;
import cl.apptec.negocio.LogicaSucursal;
import cl.apptec.negocio.LogicaUsuario;
import cl.apptec.util.components.FacesUtil;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorCompra implements Serializable{
    private List<Compra> compras;
    private List<Proveedor> proveedores;
    private List<Usuario> usuarios;
    private List<Impuesto> impuestos;
    private List<Moneda> monedas;
    private List<Sucursal> sucursales;
    private List<EstadoVenta> estadosCompras;
    private List<MedioPago> mediosPagos;


    
    private Compra compraSeleccionada;

    private enum TipoOperacion{
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
  
    @Autowired
    private LogicaCompra logicaCompra;
    @Autowired
    private LogicaProveedor logicaProveedor;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private LogicaImpuesto logicaImpuesto;
    @Autowired
    private LogicaMoneda logicaMoneda;
    @Autowired
    private LogicaSucursal logicaSucursal;
    @Autowired
    private LogicaEstadoVenta logicaEstadoCompra;
    @Autowired
    private LogicaMedioPago logicaMedioPago;
    
    public void inicio(){
        compras = logicaCompra.obtenerTodos();
        proveedores = logicaProveedor.obtenerTodos();
        usuarios = logicaUsuario.obtenerTodos();
        impuestos = logicaImpuesto.obtenerTodos();
        monedas = logicaMoneda.obtenerTodos();
        sucursales = logicaSucursal.obtenerTodos();
        estadosCompras = logicaEstadoCompra.obtenerTodos();      
        mediosPagos = logicaMedioPago.obtenerTodos();
    }
    
    public String irAgregar() {
        compraSeleccionada = new Compra();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    public String irEditar(Compra c){
        compraSeleccionada = c;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    public String irListar(){       
        return "flowListar";
    }    
    
    public void eliminarCompra(){
        compras.remove(compraSeleccionada);
        logicaCompra.eliminarCompra(compraSeleccionada);
    }
    
    public void guardarCompra(){
        logicaCompra.guardarCompra(compraSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Se ha agregado la compra  [ID: "+compraSeleccionada.getId()+"]");
            compras.add(compraSeleccionada);
            compraSeleccionada =  new Compra();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Se ha editado la compra  [ID: "+compraSeleccionada.getId()+"]");
        }
    }
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }
    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public Compra getCompraSeleccionada() {
        return compraSeleccionada;
    }

    public void setCompraSeleccionada(Compra compraSeleccionada) {
        this.compraSeleccionada = compraSeleccionada;
    }

}
