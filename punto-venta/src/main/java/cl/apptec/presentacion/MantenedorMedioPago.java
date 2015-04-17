/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.MedioPago;
import cl.apptec.negocio.LogicaMedioPago;
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
public class MantenedorMedioPago implements Serializable {
    private List<MedioPago> mediosPago;
    private MedioPago medioPagoSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;
    @Autowired
    private LogicaMedioPago logicaMedioPago;

    public void inicio() {
        mediosPago = logicaMedioPago.obtenerTodos();
    }

    public String irAgregar() {
        medioPagoSeleccionado = new MedioPago();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(MedioPago m) {
        medioPagoSeleccionado = m;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarMp() {
        mediosPago.remove(medioPagoSeleccionado);
        logicaMedioPago.eliminarMP(medioPagoSeleccionado);
    }

    public void guardarMedioPago() {
        logicaMedioPago.guardar(medioPagoSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha agregado el nuevo medio de pago [ID: " + medioPagoSeleccionado.getIdMediopago() + "]");
            mediosPago.add(medioPagoSeleccionado);
            medioPagoSeleccionado = new MedioPago();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha editado el medio de pago [ID: " + medioPagoSeleccionado.getIdMediopago() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<MedioPago> getMediosPago() {
        return mediosPago;
    }

    public void setMediosPago(List<MedioPago> mediosPago) {
        this.mediosPago = mediosPago;
    }

    public MedioPago getMedioPagoSeleccionado() {
        return medioPagoSeleccionado;
    }

    public void setMedioPagoSeleccionado(MedioPago medioPagoSeleccionado) {
        this.medioPagoSeleccionado = medioPagoSeleccionado;
    }


}
