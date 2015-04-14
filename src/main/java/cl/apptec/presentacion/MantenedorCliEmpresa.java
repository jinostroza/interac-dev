/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.Ciudad;
import cl.apptec.entidades.CliEmpresa;
import cl.apptec.entidades.Comuna;
import cl.apptec.entidades.PlanCliente;
import cl.apptec.entidades.EstadoCliente;
import cl.apptec.entidades.Giro;
import cl.apptec.entidades.Proyecto;
import cl.apptec.entidades.Pais;
import cl.apptec.negocio.LogicaCiudad;

import cl.apptec.negocio.LogicaCliEmpresa;
import cl.apptec.negocio.LogicaComuna;
import cl.apptec.negocio.LogicaPlanCliente;
import cl.apptec.negocio.LogicaGiro;
import cl.apptec.negocio.LogicaEstadoCliente;
import cl.apptec.negocio.LogicaProyecto;

import cl.apptec.util.components.FacesUtil;
import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorCliEmpresa implements Serializable{
    private List<CliEmpresa> cliEmpresas;
    private List<EstadoCliente> estadosClientes;
    private List<Comuna> comunas;
    private List<PlanCliente> planesClientes;
    private List<Giro> giros;
    private List<Proyecto> proyectos;
    private List<Ciudad> ciudades;

    private CliEmpresa cliEmpresaSeleccionado;
    private Ciudad ciudadSeleccionada;
    
    private enum TipoOperacion{
        INGRESAR,
        EDITAR
    };
    
    private TipoOperacion operacion;
  
    @Autowired
    private LogicaCliEmpresa logicaCliEmpresa;

    @Autowired
    private LogicaEstadoCliente logicaEstadoCliente;
    
    @Autowired
    private LogicaComuna logicaComuna;
    
    @Autowired
    private LogicaPlanCliente logicaPlanCliente;
    
    @Autowired
    private LogicaGiro logicaGiro;
    
    @Autowired
    private LogicaProyecto logicaProyectos;
    
    @Autowired
    private LogicaCiudad logicaCiudades;

    @Autowired
    private UserSession userSession;
    
        
    public void inicio(){
        cliEmpresas = logicaCliEmpresa.obtenerPorProyecto(userSession.getUsuario().getIdSucursal().getIdProyecto());
        estadosClientes = logicaEstadoCliente.obtenerTodos();
        comunas = logicaComuna.obtenerTodos();
        planesClientes = logicaPlanCliente.obtenerTodos();
        giros = logicaGiro.obtenerTodos();
        proyectos = logicaProyectos.obtenerTodos();
        ciudades = logicaCiudades.obtenerTodas();
    }
    
    public String irAgregar() {
        cliEmpresaSeleccionado = new CliEmpresa();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(CliEmpresa ce){
        cliEmpresaSeleccionado = ce;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }
    public String irListar() {
        return "flowListar";
    }
   
    public void eliminarCliEmpresa(){
        //cliEmpresas.remove(cliEmpresaSeleccionado);
        logicaCliEmpresa.eliminarCliEmpresa(cliEmpresaSeleccionado);
    }
    
    public void guardarCliEmpresa(){
        logicaCliEmpresa.guardarCliEmpresa(cliEmpresaSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado el nuevo Cliente Empresa [ID: "+cliEmpresaSeleccionado.getIdCliEmpresa()+"]");
            cliEmpresas.add(cliEmpresaSeleccionado);
            cliEmpresaSeleccionado = new CliEmpresa();
        }else{
            FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha editado el Cliente Empresa [ID: "+cliEmpresaSeleccionado.getIdCliEmpresa()+"]");
        }
    }
    
    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }
    
    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }
    
    public List<CliEmpresa> getCliEmpresas() {
        return cliEmpresas;
    }

    public void setCliEmpresas(List<CliEmpresa> cliEmpresas) {
        this.cliEmpresas = cliEmpresas;
    }

    public CliEmpresa getCliEmpresaSeleccionado() {
        return cliEmpresaSeleccionado;
    }

    public void setCliEmpresaSeleccionado(CliEmpresa cliEmpresaSeleccionado) {
        this.cliEmpresaSeleccionado = cliEmpresaSeleccionado;
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

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
/*    public void actualizarGiros() {
        giros = logicaGiro.obtenerPorPais(paisSeleccionado); //Si?
        giros = logicaGiro.obtenerTodos();
    }
*/
    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    
    public void actualizarComunas() {
        comunas = logicaComuna.obtenerPorCiudad(ciudadSeleccionada); //Si?
    }

    public Ciudad getCiudadSeleccionada() {
        return ciudadSeleccionada;
    }

    public void setCiudadSeleccionada(Ciudad ciudadSeleccionada) {
        this.ciudadSeleccionada = ciudadSeleccionada;
    }
    
    
    
}
