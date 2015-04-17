/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Ciudad;
import cl.apptec.entidades.Comuna;
import cl.apptec.negocio.LogicaCiudad;
import cl.apptec.negocio.LogicaComuna;
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
public class MantenedorComuna  implements Serializable{
    
    private List<Comuna> comunas;
    private List<Ciudad> ciudades;
    private Comuna comunaSeleccionada;
    
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaComuna logicaComuna;
    @Autowired
    private LogicaCiudad logicaCiudad;
    
    public void inicio(){
        comunas = logicaComuna.obtenerTodos();
        ciudades = logicaCiudad.obtenerTodas();
    }
    
    public String irAgregar(){
        comunaSeleccionada = new Comuna();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    public String irEditar(Comuna c){
        comunaSeleccionada = c;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    public String irListar(){       
        return "flowListar";
    }    
    
    public void eliminarComuna(){
        comunas.remove(comunaSeleccionada);
        logicaComuna.eliminarComuna(comunaSeleccionada);
    }
    
    public void guardarComuna(){
        logicaComuna.guardarComuna(comunaSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Se ha agregado la comuna  [ID: "+comunaSeleccionada.getIdComuna()+"]");
            comunas.add(comunaSeleccionada);
            comunaSeleccionada =  new Comuna();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Se ha editado la comuna  [ID: "+comunaSeleccionada.getIdComuna()+"]");
        }
    }
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }
    public List<Comuna> getComunas() {
        return comunas;
    }

    public void setComunas(List<Comuna> comunas) {
        this.comunas = comunas;
    }

    public Comuna getComunaSeleccionada() {
        return comunaSeleccionada;
    }

    public void setComunaSeleccionada(Comuna comunaSeleccionada) {
        this.comunaSeleccionada = comunaSeleccionada;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    
    
}
