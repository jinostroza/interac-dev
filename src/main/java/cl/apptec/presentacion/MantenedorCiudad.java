/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Ciudad;
import cl.apptec.entidades.Pais;
import cl.apptec.negocio.LogicaCiudad;
import cl.apptec.negocio.LogicaPais;
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
public class MantenedorCiudad implements Serializable{
    private List<Ciudad> ciudades;
    private List<Pais> paises;
    private Ciudad ciudadSeleccionada;
    
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaCiudad logicaCiudad;
    
    @Autowired
    private LogicaPais logicaPais;
    
    public void inicio(){
        ciudades = logicaCiudad.obtenerTodas();
          paises = logicaPais.obtenerTodos();
    }
    
    public String irAgregar(){
        ciudadSeleccionada = new Ciudad();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    public String irEditar(Ciudad c){
        ciudadSeleccionada = c;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    public String irListar(){
        ciudadSeleccionada = new Ciudad();        
        return "flowListar";
    }   
    public void eliminarCiudad(){
        ciudades.remove(ciudadSeleccionada);
        logicaCiudad.eliminarCiudad(ciudadSeleccionada);
    }
    
    public void guardarCiudad(){
        logicaCiudad.guardarCiudad(ciudadSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            ciudades.add(ciudadSeleccionada);
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha agregado Ciudad  [ID: "+ciudadSeleccionada.getIdCiudad()+"]");
            ciudadSeleccionada = new Ciudad();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha editado Ciudad  [ID: "+ciudadSeleccionada.getIdCiudad()+"]");
        }
    }
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public Ciudad getCiudadSeleccionada() {
        return ciudadSeleccionada;
    }

    public void setCiudadSeleccionada(Ciudad ciudadSeleccionada) {
        this.ciudadSeleccionada = ciudadSeleccionada;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public LogicaPais getLogicaPais() {
        return logicaPais;
    }

    public void setLogicaPais(LogicaPais logicaPais) {
        this.logicaPais = logicaPais;
    }
    
}
