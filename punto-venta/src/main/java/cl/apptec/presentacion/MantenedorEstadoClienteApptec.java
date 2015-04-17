/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.EstadoClienteApptec;
import cl.apptec.negocio.LogicaEstadoClienteApptec;
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
public class MantenedorEstadoClienteApptec implements Serializable {
    private List<EstadoClienteApptec> EstadoClienteApptec;
    private EstadoClienteApptec estadoClienteApptecSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;
    @Autowired
    private LogicaEstadoClienteApptec logicaEstadoClienteApptec;

    public void inicio() {
        EstadoClienteApptec = logicaEstadoClienteApptec.obtenerTodos();
    }

    public String irAgregar() {
        estadoClienteApptecSeleccionado = new EstadoClienteApptec();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(EstadoClienteApptec e) {
        estadoClienteApptecSeleccionado = e;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarECA() {
        logicaEstadoClienteApptec.eliminarECA(estadoClienteApptecSeleccionado);
        EstadoClienteApptec.remove(estadoClienteApptecSeleccionado);

    }

    public void guardarEstadoClienteApptec() {
        logicaEstadoClienteApptec.guardarEstadoClienteApptec(estadoClienteApptecSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Se ha agregado Estado de Cliente Apptec  [ID: " + estadoClienteApptecSeleccionado.getIdEstadoClienteApptec() + "]");
            EstadoClienteApptec.add(estadoClienteApptecSeleccionado);
            estadoClienteApptecSeleccionado = new EstadoClienteApptec();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Se ha editado Estado de Cliente Apptec  [ID: " + estadoClienteApptecSeleccionado.getIdEstadoClienteApptec() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<EstadoClienteApptec> getEstadoClienteApptec() {
        return EstadoClienteApptec;
    }

    public void setEstadoClienteApptec(List<EstadoClienteApptec> EstadoClienteApptec) {
        this.EstadoClienteApptec = EstadoClienteApptec;
    }

    public EstadoClienteApptec getEstadoClienteApptecSeleccionado() {
        return estadoClienteApptecSeleccionado;
    }

    public void setEstadoClienteApptecSeleccionado(EstadoClienteApptec estadoClienteApptecSeleccionado) {
        this.estadoClienteApptecSeleccionado = estadoClienteApptecSeleccionado;
    }


}
