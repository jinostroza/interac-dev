/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Cliente;
import cl.apptec.entidades.EstadoCliente;
import cl.apptec.entidades.TipoFono;
import cl.apptec.negocio.LogicaCliente;
import cl.apptec.negocio.LogicaEstadoCliente;
import cl.apptec.negocio.LogicaTipoFono;
import cl.apptec.util.components.FacesUtil;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author apptec
 */
@Component
@Scope("flow")
public class MantenedorCliente implements Serializable{
    private List<Cliente> clientes;
    private List<TipoFono> tiposFono;
    private List<EstadoCliente> estadosCliente;
    private Cliente clienteSeleccionado;
    private enum TipoOperacion{
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
  
    @Autowired
    private LogicaCliente logicaCliente;
    @Autowired
    private LogicaTipoFono logicaTipoFono;
    @Autowired
    private LogicaEstadoCliente logicaEstadoCliente;
    
    public void inicio(){
        clientes = logicaCliente.obtenerTodos();
        tiposFono = logicaTipoFono.obtenerTodos();
        estadosCliente = logicaEstadoCliente.obtenerTodos();
    }
    
    public String irAgregar() {
        clienteSeleccionado = new Cliente();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(Cliente c){
        clienteSeleccionado = c;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    public String irListar() {
        return "flowListar";
    }
   
    public void eliminarCliente(){
        clientes.remove(clienteSeleccionado);
        logicaCliente.eliminarCliente(clienteSeleccionado);
    }
    public void guardarCliente(){
        logicaCliente.guardarCliente(clienteSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado el nuevo cliente [ID: "+clienteSeleccionado.getIdCliente()+"]");
            clientes.add(clienteSeleccionado);
            clienteSeleccionado = new Cliente();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado el cliente [ID: "+clienteSeleccionado.getIdCliente()+"]");
        }
    }
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public List<TipoFono> getTiposFono() {
        return tiposFono;
    }

    public void setTiposFono(List<TipoFono> tiposFono) {
        this.tiposFono = tiposFono;
    }

    public List<EstadoCliente> getEstadosCliente() {
        return estadosCliente;
    }

    public void setEstadosCliente(List<EstadoCliente> estadosCliente) {
        this.estadosCliente = estadosCliente;
    }
    
    
}
