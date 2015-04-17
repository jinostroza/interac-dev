/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Atribucion;
import cl.apptec.negocio.LogicaAtribucion;
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
public class MantenedorAtribucion implements Serializable {

    private List<Atribucion> atribuciones;
    private Atribucion atribucionSeleccionada;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;
    @Autowired
    private LogicaAtribucion logicaAtribucion;

    public void inicio() {
        atribuciones = logicaAtribucion.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        atribucionSeleccionada = new Atribucion();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Atribucion a) {
        atribucionSeleccionada = a;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarAtribuciones() {
        atribuciones.remove(atribucionSeleccionada);
        logicaAtribucion.eliminarAtribucion(atribucionSeleccionada);
    }

    public void guardarAtribucion() {
        logicaAtribucion.guardarAtribucion(atribucionSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nueva atribucion [ID: " + atribucionSeleccionada.getIdAtribucion() + "]");
            atribuciones.add(atribucionSeleccionada);
            atribucionSeleccionada = new Atribucion();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado atribucion [ID: " + atribucionSeleccionada.getIdAtribucion() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<Atribucion> getAtribuciones() {
        return atribuciones;
    }

    public void setAtribuciones(List<Atribucion> atribuciones) {
        this.atribuciones = atribuciones;
    }

    public Atribucion getAtribucionSeleccionada() {
        return atribucionSeleccionada;
    }

    public void setAtribucionSeleccionada(Atribucion atribucionSeleccionada) {
        this.atribucionSeleccionada = atribucionSeleccionada;
    }

}
