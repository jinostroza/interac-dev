/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.ClienteApptec;
import cl.apptec.entidades.Moneda;
import cl.apptec.entidades.Pais;
import cl.apptec.entidades.Proyecto;
import cl.apptec.negocio.LogicaClienteApptec;
import cl.apptec.negocio.LogicaMoneda;
import cl.apptec.negocio.LogicaProyecto;
import cl.apptec.negocio.LogicaPais;
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
public class MantenedorProyecto implements Serializable{
    private List<Proyecto> proyectos;
    private List<Pais> paises;
    private List<ClienteApptec> clientesApptec;
    private List<Moneda> monedas;
    private List<Proyecto> proyectosFiltrados;
    private Proyecto proyectoSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;

    @Autowired
    private LogicaProyecto logicaProyecto;
    
    @Autowired
    private LogicaPais logicaPais;
    
    @Autowired
    private LogicaClienteApptec logicaClienteApptec;
    
    @Autowired
    private LogicaMoneda logicaMoneda;

    public void inicio() {
        proyectos = logicaProyecto.obtenerTodos();
        proyectosFiltrados = logicaProyecto.obtenerTodos();
        paises = logicaPais.obtenerTodos();
        clientesApptec = logicaClienteApptec.obtenerTodos();
        monedas = logicaMoneda.obtenerTodos();
    
    }

    // flows
    public String irAgregar() {
        proyectoSeleccionado = new Proyecto();
        proyectoSeleccionado.setActivoProyecto(true);
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Proyecto p) {
        proyectoSeleccionado = p;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarProyecto() {
        //proyectos.remove(proyectoSeleccionado); eliminar solo desabilita, no borra datos.
        logicaProyecto.eliminarProyecto(proyectoSeleccionado);
    }

    public void guardarProyecto() {
        if (operacion == TipoOperacion.INGRESAR) {
            if (!logicaProyecto.existeProyecto(proyectoSeleccionado.getNombreProyecto())) {
                logicaProyecto.guardarProyecto(proyectoSeleccionado);
                FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo país [ID: " + proyectoSeleccionado.getIdProyecto() + "]");
                proyectos.add(proyectoSeleccionado);
                proyectoSeleccionado = new Proyecto();
            } else {
                FacesUtil.mostrarMensajeError("Operación fallida", "El país ya existe");
            }
        } else {
            logicaProyecto.guardarProyecto(proyectoSeleccionado);
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado el país [ID: " + proyectoSeleccionado.getIdProyecto() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    //Getter y Setter
    
    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
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

    public List<ClienteApptec> getClientesApptec() {
        return clientesApptec;
    }

    public void setClientesApptec(List<ClienteApptec> clientesApptec) {
        this.clientesApptec = clientesApptec;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public List<Proyecto> getProyectosFiltrados() {
        return proyectosFiltrados;
    }

    public void setProyectosFiltrados(List<Proyecto> proyectosFiltrados) {
        this.proyectosFiltrados = proyectosFiltrados;
    }
    
    
    
}
