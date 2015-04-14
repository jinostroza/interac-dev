/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.TipoFono;
import cl.apptec.negocio.LogicaTipoFono;
import cl.apptec.util.components.FacesUtil;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author apptec
 */
@Component
@Scope("flow")
public class MantenedorTipoFono implements Serializable {
    
    private List<TipoFono> tiposFono;
    private TipoFono tipoFonoSeleccionado;
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    
   private TipoOperacion operacion; 
           
    @Autowired
    private LogicaTipoFono logicaTipoFono;
    
    public void inicio(){
        tiposFono = logicaTipoFono.obtenerTodos();
    }
    
     // flows
    public String irAgregar() {
        tipoFonoSeleccionado = new TipoFono();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    public String irEditar(TipoFono f) {
        tipoFonoSeleccionado = f;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    
    public String irListar() {
        return "flowListar";
    }
    public void eliminarTipoFono() {
        tiposFono.remove(tipoFonoSeleccionado);
        logicaTipoFono.eliminarTipoFono(tipoFonoSeleccionado);
    }
    
     // logica vista
    public void guardarTipoFono(){
        logicaTipoFono.guardarTipoFono(tipoFonoSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado el nuevo tipo fono [ID: "+tipoFonoSeleccionado.getIdTipoFono()+"]");
            tiposFono.add(tipoFonoSeleccionado);
            tipoFonoSeleccionado = new TipoFono();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado el tipo fono [ID: "+tipoFonoSeleccionado.getIdTipoFono()+"]");
        }
    }
    
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<TipoFono> getTiposFono() {
        return tiposFono;
    }

    public void setTiposFono(List<TipoFono> tiposFono) {
        this.tiposFono = tiposFono;
    }

    public TipoFono getTipoFonoSeleccionado() {
        return tipoFonoSeleccionado;
    }

    public void setTipoFonoSeleccionado(TipoFono tipoFonoSeleccionado) {
        this.tipoFonoSeleccionado = tipoFonoSeleccionado;
    }
    
   
}
