/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;


import cl.apptec.entidades.EstadoVenta;
import cl.apptec.negocio.LogicaEstadoVenta;
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
public class MantenedorEstadoVenta implements Serializable {

    private List<EstadoVenta> estadosVenta;
    private EstadoVenta estadoVentaSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaEstadoVenta logicaEstadoVenta;

    public void inicio() {
        estadosVenta = logicaEstadoVenta.obtenerTodos();
    }

    public String irAgregar() {
        estadoVentaSeleccionado = new EstadoVenta();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(EstadoVenta e) {
        estadoVentaSeleccionado = e;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarEV() {
        estadosVenta.remove(estadoVentaSeleccionado);
        logicaEstadoVenta.eliminarEV(estadoVentaSeleccionado);
    }

    public void guardarEV() {
        logicaEstadoVenta.guardarEV(estadoVentaSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha creado el estado venta [ID: " + estadoVentaSeleccionado.getIdEstadoventa() + "]");
            estadosVenta.add(estadoVentaSeleccionado);
            estadoVentaSeleccionado = new EstadoVenta();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha editado el estado venta [ID: " + estadoVentaSeleccionado.getIdEstadoventa() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<EstadoVenta> getEstadosVenta() {
        return estadosVenta;
    }

    public void setEstadosVenta(List<EstadoVenta> estadosVenta) {
        this.estadosVenta = estadosVenta;
    }

    public EstadoVenta getEstadoVentaSeleccionado() {
        return estadoVentaSeleccionado;
    }

    public void setEstadoVentaSeleccionado(EstadoVenta estadoVentaSeleccionado) {
        this.estadoVentaSeleccionado = estadoVentaSeleccionado;
    }


}
