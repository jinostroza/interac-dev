/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Moneda;
import cl.apptec.negocio.LogicaMoneda;
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
public class MantenedorMoneda implements Serializable {
    private List<Moneda> monedas;
    private Moneda monedaSeleccionada;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaMoneda logicaMoneda;

    public void inicio() {
        monedas = logicaMoneda.obtenerTodos();
    }

    public String irAgregar() {
        monedaSeleccionada = new Moneda();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Moneda m) {
        monedaSeleccionada = m;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarmonedas() {
        monedas.remove(monedaSeleccionada);
        logicaMoneda.eliminarMoneda(monedaSeleccionada);
    }

    public void guardarMoneda() {
        logicaMoneda.guardarMoneda(monedaSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha agregado la moneda [ID: " + monedaSeleccionada.getIdMoneda());
            monedas.add(monedaSeleccionada);
            monedaSeleccionada = new Moneda();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha editado la moneda [ID: " + monedaSeleccionada.getIdMoneda());
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public Moneda getMonedaSeleccionada() {
        return monedaSeleccionada;
    }

    public void setMonedaSeleccionada(Moneda monedaSeleccionada) {
        this.monedaSeleccionada = monedaSeleccionada;
    }

}
