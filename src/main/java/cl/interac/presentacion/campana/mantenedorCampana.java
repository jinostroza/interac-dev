package cl.interac.presentacion.campana;

import cl.interac.dao.CampanaDAO;

import cl.interac.entidades.*;
import cl.interac.negocio.*;

import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by luis on 25-04-2015.
 */
@Component
@Scope("flow")
public class mantenedorCampana implements Serializable {
    public enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;
    @Autowired
    private LogicaCampana logicaCampana;
    private LogicaAnuncio logicaAnuncio;
    private List<Campana> obtenerCampana;
    private List<Anuncio> obtenerAnuncio;
    private List<Usuario> obtenerUsuario;
    private List<Totem> obtenerTotem;
    private Totem totem;
    private Anuncio anuncio;
    private Campana campana;
    private Usuario usuario;

    public mantenedorCampana() {
        campana = new Campana();
    }

    public void guardar() {
        logicaCampana.guardarCampana(campana);
        System.out.print("hueheuheuheuhue");
    }

    public void inicio() {
        obtenerCampana = logicaCampana.obtenerTodos();
    }


    public Campana getCampana() {
        return campana;
    }

    public void setCampana(List<Campana> obtenerCampana) {
        this.obtenerCampana = obtenerCampana;
    }

    public List<Campana> getObtenerCampana() {
        return obtenerCampana;
    }


    //flows
    public String idAgregar() {
        campana = new Campana();
        operacion = TipoOperacion.INGRESAR;
        guardar();
        return"flowAgregar";

    }

    //public String irEditar(Campana campana){
      //  obtenerCampana = logicaCampana.
    //}

}










