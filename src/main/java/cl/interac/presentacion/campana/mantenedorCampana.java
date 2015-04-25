package cl.interac.presentacion.campana;

import cl.interac.dao.CampanaDAO;

import cl.interac.entidades.Campana;
import cl.interac.negocio.LogicaCampana;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 25-04-2015.
 */
public class mantenedorCampana implements Serializable {


    private List<Campana> Campana;

    private Campana campana;

    private Campana campanaSeleccionada;

    public enum TipoOperador{
        EDITAR,
        INGRESAR,
    }

    private transient UploadedFile foto;
    private TipoOperador operacion;

    public void inicio() {
        Campana = logicaCampana.obtenerTodos();
    }


    @Autowired
    private LogicaCampana logicaCampana;
    // flows
    public void guardarCampana() {

        if (operacion == TipoOperador.INGRESAR) {
        } else {
        }
    }
    public void signUp() {
        System.err.println("elegir campana");

        FacesUtil.mostrarMensajeInformativo("Resultado de la operación", "Anuncio guardado exitosamente");
    }
    public boolean esIngreso() {
        return operacion == TipoOperador.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperador.EDITAR;
    }


    public List<Campana> getCampana() {
        return Campana;
    }

    public void setCampana(List<Campana> campana) {
        this.Campana = campana;
    }
}







