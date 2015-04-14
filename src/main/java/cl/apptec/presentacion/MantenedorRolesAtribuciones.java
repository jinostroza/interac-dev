/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.RolAtribucion;
import cl.apptec.negocio.LogicaRolAtribucion;
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
public class MantenedorRolesAtribuciones implements Serializable{
    
    private List<RolAtribucion> rolesAtribuciones;
    private RolAtribucion rolAtribucuionSeleccionado;
    
    @Autowired
    private LogicaRolAtribucion logicaRolAtribucion;
    
    public void inicio(){
        rolesAtribuciones = logicaRolAtribucion.obtenerTodos();
    }
    public String irAgregar() {
        rolAtribucuionSeleccionado = new RolAtribucion();
        return "flowAgregar";
    }
    public String irEditar() {
        rolAtribucuionSeleccionado = new RolAtribucion();
        return "flowAgregar";
    }
    public String irListar() {
        return "flowListar";
    }
    
    public void guardarRolAtribucion(){
        logicaRolAtribucion.guardarRolAtribucion(rolAtribucuionSeleccionado);   
        rolAtribucuionSeleccionado = new RolAtribucion();
    }

    public List<RolAtribucion> getRolesAtribuciones() {
        return rolesAtribuciones;
    }

    public void setRolesAtribuciones(List<RolAtribucion> rolesAtribuciones) {
        this.rolesAtribuciones = rolesAtribuciones;
    }

    public RolAtribucion getRolAtribucuionSeleccionado() {
        return rolAtribucuionSeleccionado;
    }

    public void setRolAtribucuionSeleccionado(RolAtribucion rolAtribucuionSeleccionado) {
        this.rolAtribucuionSeleccionado = rolAtribucuionSeleccionado;
    }

   
    
    
    
}
