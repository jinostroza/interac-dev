package cl.apptec.presentacion;

import cl.apptec.entidades.Pais;
import cl.apptec.negocio.LogicaPais;
import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author secabezas
 */
@Component
@Scope("flow")
public class MantenedorPaises implements Serializable {

    private List<Pais> paises;
    private Pais paisSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;

    @Autowired
    private LogicaPais logicaPais;

    public void inicio() {
        paises = logicaPais.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        paisSeleccionado = new Pais();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Pais p) {
        paisSeleccionado = p;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarPais() {
        paises.remove(paisSeleccionado);
        logicaPais.eliminarPais(paisSeleccionado);
    }

    public void guardarPais() {
        if (operacion == TipoOperacion.INGRESAR) {
            if (!logicaPais.existePais(paisSeleccionado.getNombrePais())) {
                logicaPais.guardarPais(paisSeleccionado);
                FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo país [ID: " + paisSeleccionado.getIdPais() + "]");
                paises.add(paisSeleccionado);
                paisSeleccionado = new Pais();
            } else {
                FacesUtil.mostrarMensajeError("Operación fallida", "El país ya existe");
            }
        } else {
            logicaPais.guardarPais(paisSeleccionado);
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado el país [ID: " + paisSeleccionado.getIdPais() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
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
