package cl.interac.presentacion.campana;

import cl.interac.entidades.Campana;
import cl.interac.entidades.Totem;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaTotem;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 25-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorCampana implements Serializable {
    public enum TipoOperacion {
        INGRESAR,
        EDITAR
    };

    // manejo manual
    private TipoOperacion operacion;
    private List<Campana> campanas;
    private List<Totem> totems;
    private Campana campana;

    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private UserSession userSession;

    public MantenedorCampana() {
        campana = new Campana();
    }

    public void inicio() {
        totems = logicaTotem.obtenerTodos();
        // para los Lazy Exception (Excepcion de carga ligera) usar FetchType.EAGER (Con cautela) o hacer una query con las relaciones (Lo seguro aunque tardas más programando :P)
        //campanas = logicaCampana.obtenerTodas(); // para eager
        campanas = logicaCampana.obtenerTodosConRelaciones(); // para lazy
        operacion = TipoOperacion.INGRESAR;
    }
    //flows shift+f6 es refactor

    public void guardar() {
        campana.setCliente(userSession.getUsuario());
        logicaCampana.guardarCampana(campana);

        if (esEdicion()) {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado la campaña ");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado la campaña ");
        }
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public Campana getCampana() {
        return campana;
    }

    public void setCampana(List<Campana> obtenerCampana) {
        this.campanas = obtenerCampana;
    }

    public List<Campana> getCampanas() {
        return campanas;
    }

    public void setCampanas(List<Campana> campanas) {
        this.campanas = campanas;
    }

    public List<Totem> getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }
}










