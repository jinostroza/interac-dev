/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.EstadoCliente;
import cl.apptec.negocio.LogicaEstadoCliente;
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
public class MantenedorEstadoCliente implements Serializable {
    private List<EstadoCliente> estadosCliente;
    private EstadoCliente estadoClienteSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    
    private TipoOperacion operacion;

    @Autowired
    private LogicaEstadoCliente logicaEstadoCliente;

    public void inicio() {
        estadosCliente = logicaEstadoCliente.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        estadoClienteSeleccionado = new EstadoCliente();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(EstadoCliente e) {
        estadoClienteSeleccionado = e;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarEstadoCliente() {
        estadosCliente.remove(estadoClienteSeleccionado);
        logicaEstadoCliente.eliminarEstadoCliente(estadoClienteSeleccionado);
    }
    // logica vista

    public void guardarEstadoCliente() {
        logicaEstadoCliente.guardarEstadCliente(estadoClienteSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo estado cliente [ID: " + estadoClienteSeleccionado.getIdEstadoCliente() + "]");
            estadosCliente.add(estadoClienteSeleccionado);
            estadoClienteSeleccionado = new EstadoCliente();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado estado cliente [ID: " + estadoClienteSeleccionado.getIdEstadoCliente() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<EstadoCliente> getEstadosCliente() {
        return estadosCliente;
    }

    public void setEstadosCliente(List<EstadoCliente> estadosCliente) {
        this.estadosCliente = estadosCliente;
    }

    public EstadoCliente getEstadoClienteSeleccionado() {
        return estadoClienteSeleccionado;
    }

    public void setEstadoClienteSeleccionado(EstadoCliente estadoClienteSeleccionado) {
        this.estadoClienteSeleccionado = estadoClienteSeleccionado;
    }


}
