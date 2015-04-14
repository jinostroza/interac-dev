/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Ciudad;
import cl.apptec.entidades.CliPersona;
import cl.apptec.entidades.Comuna;
import cl.apptec.entidades.PlanCliente;
import cl.apptec.entidades.EstadoCliente;
import cl.apptec.entidades.Giro;
import cl.apptec.entidades.Proyecto;
import cl.apptec.entidades.Pais;
import cl.apptec.negocio.LogicaCiudad;

import cl.apptec.negocio.LogicaCliPersona;
import cl.apptec.negocio.LogicaComuna;
import cl.apptec.negocio.LogicaPlanCliente;
import cl.apptec.negocio.LogicaGiro;
import cl.apptec.negocio.LogicaEstadoCliente;
import cl.apptec.negocio.LogicaProyecto;

import cl.apptec.util.components.UserSession;
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
public class MantenedorCliPersona implements Serializable{
    private List<CliPersona> cliPersonas;
    private List<EstadoCliente> estadosClientes;
    private List<Comuna> comunas;
    private List<Ciudad> ciudades;
    private List<PlanCliente> planesClientes;
    private List<Giro> giros;
    
    private Proyecto proyectoSeleccionado;
    private Ciudad ciudadSeleccionada;
    private CliPersona cliPersonaSeleccionado;
    private Pais paisSeleccionado; //Si?
    private Proyecto proyecto;
    
    
    private enum TipoOperacion{
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
  
    @Autowired
    private LogicaCliPersona logicaCliPersona;

    @Autowired
    private LogicaEstadoCliente logicaEstadoCliente;
    
    @Autowired
    private LogicaComuna logicaComuna;
    
    @Autowired
    private LogicaPlanCliente logicaPlanCliente;
    
    @Autowired
    private LogicaGiro logicaGiro;
        
    @Autowired
    private LogicaCiudad logicaCiudad;
    
    @Autowired
    private UserSession userSession;
    
    public void inicio(){
        cliPersonas = logicaCliPersona.obtenerPorProyecto(userSession.getUsuario().getIdSucursal().getIdProyecto());
        //cliPersonas = logicaCliPersona.obtenerTodos();
        estadosClientes = logicaEstadoCliente.obtenerTodos();
        comunas = logicaComuna.obtenerTodos();
        planesClientes = logicaPlanCliente.obtenerTodos();
        giros = logicaGiro.obtenerTodos();
        ciudades = logicaCiudad.obtenerTodas();
    }
    
    public String irAgregar() {
        cliPersonaSeleccionado = new CliPersona();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(CliPersona cp){
        cliPersonaSeleccionado = cp;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    public String irListar() {
        return "flowListar";
    }
   
    public void eliminarCliPersona(){
        //cliPersonas.remove(cliPersonaSeleccionado);
        logicaCliPersona.eliminarCliPersona(cliPersonaSeleccionado);
    }
    
    public void guardarCliPersona(){
        logicaCliPersona.guardarCliPersona(cliPersonaSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado el nuevo Cliente Persona [ID: "+cliPersonaSeleccionado.getIdCliPersona()+"]");
            cliPersonas.add(cliPersonaSeleccionado);
            cliPersonaSeleccionado = new CliPersona();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado el Cliente Persona [ID: "+cliPersonaSeleccionado.getIdCliPersona()+"]");
        }
    }
    
    //ACTUALIZAR LISTAS
    
    public void actualizarCiudades() {
        //giros = logicaGiro.obtenerPorPais(paisSeleccionado); //Si?
        ciudades = logicaCiudad.obtenerTodas();
    }
    
    public void actualizarComunas() {
        comunas = logicaComuna.obtenerPorCiudad(ciudadSeleccionada); //Si?
    }
        
    //GETTER y SETTER
    //LISTAS
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }
    
    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }
    
    
    public List<CliPersona> getCliPersonas() {
        return cliPersonas;
    }

    public void setCliPersonas(List<CliPersona> cliPersonas) {
        
        this.cliPersonas = cliPersonas;
    }

     public List<EstadoCliente> getEstadosClientes() {
        return estadosClientes;
    }

    public void setEstadosClientes(List<EstadoCliente> estadosClientes) {
        this.estadosClientes = estadosClientes;
    }

    public List<Comuna> getComunas() {
        return comunas;
    }

    public void setComunas(List<Comuna> comunas) {
        this.comunas = comunas;
    }

    public List<PlanCliente> getPlanesClientes() {
        return planesClientes;
    }

    public void setPlanesClientes(List<PlanCliente> planesClientes) {
        this.planesClientes = planesClientes;
    }

    public List<Giro> getGiros() {
        return giros;
    }

    public void setGiros(List<Giro> giros) {
        this.giros = giros;
    }
    
    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    
    //SELECCIONADOS
    
    public CliPersona getCliPersonaSeleccionado() {
        return cliPersonaSeleccionado;
    }

    public void setCliPersonaSeleccionado(CliPersona cliPersonaSeleccionado) {
        this.cliPersonaSeleccionado = cliPersonaSeleccionado;
    }

    public Ciudad getCiudadSeleccionada() {
        return ciudadSeleccionada;
    }

    public void setCiudadSeleccionada(Ciudad ciudadSeleccionada) {
        this.ciudadSeleccionada = ciudadSeleccionada;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

}
