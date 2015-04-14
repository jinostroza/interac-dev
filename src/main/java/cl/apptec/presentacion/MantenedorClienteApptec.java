/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.ClienteApptec;
import cl.apptec.entidades.Giro;
import cl.apptec.negocio.LogicaClienteApptec;
import cl.apptec.negocio.LogicaGiro;
import cl.apptec.util.components.FacesUtil;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author apptec
 */
@Component
@Scope("flow")
public class MantenedorClienteApptec implements Serializable {
    
    private List<ClienteApptec> clientesApptec;
    private List<Giro> giros;
    
    private ClienteApptec clienteApptecSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaClienteApptec logicaClienteApptec;
    
    @Autowired
    private LogicaGiro logicaGiro;

    public void inicio() {
        clientesApptec = logicaClienteApptec.obtenerTodos();
        giros = logicaGiro.obtenerTodos();
    }

    public String irAgregar() {
        clienteApptecSeleccionado = new ClienteApptec();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(ClienteApptec c) {
        clienteApptecSeleccionado = c;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }
    // logica vista
    public List<ClienteApptec> obtenerClientesApptec(){
        return logicaClienteApptec.obtenerTodos();
    }

    public void eliminarCA() {
        clientesApptec.remove(clienteApptecSeleccionado);
        logicaClienteApptec.eliminarCA(clienteApptecSeleccionado);
    }

    public void guardarClienteApptec() {
        logicaClienteApptec.guardarClienteApptec(clienteApptecSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo  cliente [ID: " + clienteApptecSeleccionado.getIdClienteApptec() + "]");
            clientesApptec.add(clienteApptecSeleccionado);
            clienteApptecSeleccionado = new ClienteApptec();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación existosa", "Se ha editado cliente [ID: " + clienteApptecSeleccionado.getIdClienteApptec() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<ClienteApptec> getClientesApptec() {
        return clientesApptec;
    }
   
    public List<Giro> getGiros() {
        return giros;
    }

    public void setGiros(List<Giro> giros) {
        this.giros = giros;
    }

    public void setClientesApptec(List<ClienteApptec> clientesApptec) {
        this.clientesApptec = clientesApptec;
    }

    
    public ClienteApptec getClienteApptecSeleccionado() {
        return clienteApptecSeleccionado;
    }

    public void setClienteApptecSeleccionado(ClienteApptec clienteApptecSeleccionado) {
        this.clienteApptecSeleccionado = clienteApptecSeleccionado;
    }
    
}
