/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;


import cl.apptec.entidades.PlanCliente;
import cl.apptec.negocio.LogicaPlanCliente;
import cl.apptec.util.components.FacesUtil;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorPlanCliente implements Serializable{
    private List<PlanCliente> planesClientes;
    
    private PlanCliente planClienteSeleccionado;
    
    private enum TipoOperacion{
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaPlanCliente logicaPlanCliente;
    
    public void inicio(){
        planesClientes = logicaPlanCliente.obtenerTodos();
    }
    
    public String irAgregar() {
        planClienteSeleccionado = new PlanCliente();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(PlanCliente pc){
        planClienteSeleccionado = pc;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    public String irListar() {
        return "flowListar";
    }
    
    public void eliminarPlanCliente(){
        planesClientes.remove(planClienteSeleccionado);
        logicaPlanCliente.eliminarPlanCliente(planClienteSeleccionado);
    }
    public void guardarPlanCliente(){
        logicaPlanCliente.guardarPlanCliente(planClienteSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado el nuevo Plan Cliente [ID: "+planClienteSeleccionado.getIdPlanCliente()+"]");
            planesClientes.add(planClienteSeleccionado);
            planClienteSeleccionado = new PlanCliente();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado el Plan Cliente [ID: "+planClienteSeleccionado.getIdPlanCliente()+"]");
        }
    }
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<PlanCliente> getPlanesClientes() {
        return planesClientes;
    }

    public void setPlanesClientes(List<PlanCliente> planesClientes) {
        this.planesClientes = planesClientes;
    }

    public PlanCliente getPlanClienteSeleccionado() {
        return planClienteSeleccionado;
    }

    public void setPlanClienteSeleccionado(PlanCliente planClienteSeleccionado) {
        this.planClienteSeleccionado = planClienteSeleccionado;
    }

}