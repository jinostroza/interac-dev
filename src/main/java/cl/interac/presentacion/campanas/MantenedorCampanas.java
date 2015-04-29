package cl.interac.presentacion.campanas;

import cl.interac.entidades.Campana;
import cl.interac.util.components.FacesUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

import cl.interac.negocio.LogicaCampana;

import javax.faces.context.FacesContext;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorCampanas implements Serializable
{
    private LogicaCampana logicaCampana;
    private Campana campana;

    public MantenedorCampanas () {
        campana = new Campana();
    }

    public enum TipoOperacion {
        INGRESAR,
        EDITAR;
    }


    private TipoOperacion operacion;

    public void inicio() {
        operacion = TipoOperacion.INGRESAR;
    }

    // flows
    public void guardar() {
        logicaCampana.guardarCampana(campana);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado correctamente el anuncio");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado correctamente el anuncio");
        }
    }


    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public Campana    getCampana() {
        return campana;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }
}
