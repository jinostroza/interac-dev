/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Plan;
import cl.apptec.negocio.LogicaPlan;
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
public class MantenedorPlan implements Serializable {
    private List<Plan> planes;
    private Plan planSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaPlan logicaPlan;

    public void inicio() {
        planes = logicaPlan.obtenerTodos();
    }

    public String irAgregar() {
        planSeleccionado = new Plan();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Plan p) {
        planSeleccionado = p;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarPlan() {
        //planes.remove(planSeleccionado);
        logicaPlan.eliminarPlan(planSeleccionado);
    }

    public void guardarPlan() {
        logicaPlan.guardarPlan(planSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha agregado el plan  [ID: " + planSeleccionado.getIdPlan() + "]");
            planes.add(planSeleccionado);
            planSeleccionado = new Plan();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha editado el plan  [ID: " + planSeleccionado.getIdPlan() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }

    public Plan getPlanSeleccionado() {
        return planSeleccionado;
    }

    public void setPlanSeleccionado(Plan planSeleccionado) {
        this.planSeleccionado = planSeleccionado;
    }


}
