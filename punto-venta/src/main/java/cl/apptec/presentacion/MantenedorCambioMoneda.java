/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.CambioMoneda;
import cl.apptec.negocio.LogicaCambioMoneda;

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
public class MantenedorCambioMoneda implements Serializable {
    private List<CambioMoneda> cambioMoneda;
    private CambioMoneda cambioMonedaSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;
    @Autowired
    private LogicaCambioMoneda logicaCambioMoneda;

    public void inicio() {

    }

    public String irAgregar() {
        cambioMonedaSeleccionado = new CambioMoneda();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(CambioMoneda m) {
        cambioMonedaSeleccionado = m;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "irListar";
    }

    public List<CambioMoneda> obtenerCambiosMoneda() {
        return logicaCambioMoneda.obtenerTodos();
    }

    public void eliminarCambioMoneda() {
        cambioMoneda.remove(cambioMonedaSeleccionado);
        logicaCambioMoneda.eliminarCambioM(cambioMonedaSeleccionado);
    }

    public void guardarCambioMoneda() {
        logicaCambioMoneda.guardarCambioM(cambioMonedaSeleccionado);
        cambioMoneda.add(cambioMonedaSeleccionado);
        cambioMonedaSeleccionado = new CambioMoneda();
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<CambioMoneda> getCambioMoneda() {
        return cambioMoneda;
    }

    public void setCambioMoneda(List<CambioMoneda> cambioMoneda) {
        this.cambioMoneda = cambioMoneda;
    }

    public CambioMoneda getCambioMonedaSeleccionado() {
        return cambioMonedaSeleccionado;
    }

    public void setCambioMonedaSeleccionado(CambioMoneda cambioMonedaSeleccionado) {
        this.cambioMonedaSeleccionado = cambioMonedaSeleccionado;
    }

}
