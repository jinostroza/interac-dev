package cl.apptec.presentacion;

import cl.apptec.entidades.Ciudad;
import cl.apptec.entidades.Comuna;
import cl.apptec.entidades.Pais;
import cl.apptec.entidades.Sucursal;
import cl.apptec.negocio.LogicaCiudad;
import cl.apptec.negocio.LogicaComuna;
import cl.apptec.negocio.LogicaPais;
import cl.apptec.negocio.LogicaSucursal;

import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorSucursal implements Serializable {
    private List<Sucursal> sucursales;
    private List<Comuna> comunas;
    private List<Ciudad> ciudades;
    private List<Pais> paises;
    private Sucursal sucursalSeleccionada;
    private Ciudad ciudadSeleccionada;
    private Pais paisSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaSucursal logicaSucursal;

    @Autowired
    private LogicaCiudad logicaCiudad;

    @Autowired
    private LogicaComuna logicaComuna;

    @Autowired
    private LogicaPais logicaPais;

    public void inicio() {
        sucursales = logicaSucursal.obtenerTodos();
        paises = logicaPais.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        sucursalSeleccionada = new Sucursal();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Sucursal s) {
        sucursalSeleccionada = s;
        ciudadSeleccionada = s.getIdComuna().getIdCiudad();
        paisSeleccionado = ciudadSeleccionada.getIdPais();

        ciudades = logicaCiudad.obtenerPorPais(paisSeleccionado);
        comunas = logicaComuna.obtenerPorCiudad(ciudadSeleccionada);
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void actualizarCiudades() {
        ciudades = logicaCiudad.obtenerPorPais(paisSeleccionado);
        comunas = null;
    }

    public void actualizarComunas() {
        comunas = logicaComuna.obtenerPorCiudad(ciudadSeleccionada);
    }

    public void eliminarSucursal() {
        logicaSucursal.eliminarSucursal(sucursalSeleccionada);
        sucursales.remove(sucursalSeleccionada);
    }

    public void guardarSucursal() {
        logicaSucursal.guardarSucursal(sucursalSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            sucursalSeleccionada.setActivoSucursal(true);
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado la nueva sucursal [ID: " + sucursalSeleccionada.getIdSucursal() + "]");
            sucursales.add(sucursalSeleccionada);
            sucursalSeleccionada = new Sucursal();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado la sucursal [ID: " + sucursalSeleccionada.getIdSucursal() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<Sucursal> getSucursal() {
        return sucursales;
    }

    public void setSucursal(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public Sucursal getSucursalSeleccionada() {
        return sucursalSeleccionada;
    }

    public void setSucursalSeleccionada(Sucursal sucursalSeleccionada) {
        this.sucursalSeleccionada = sucursalSeleccionada;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<Comuna> getComunas() {
        return comunas;
    }

    public void setComunas(List<Comuna> comunas) {
        this.comunas = comunas;
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

    public Pais getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(Pais paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }
}
