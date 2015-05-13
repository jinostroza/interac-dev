package cl.interac.presentacion.anuncios;

import cl.interac.entidades.Campana;
import cl.interac.entidades.Usuario;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cl.interac.entidades.Anuncio;
import cl.interac.negocio.LogicaAnuncio;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorAnuncios implements Serializable {
    @Autowired
    private LogicaAnuncio logicaAnuncio;
    private Anuncio anuncio;
    private Campana campana;
    private List<Anuncio> obtenerAnuncio;

    private enum TipoOperacion {
        INSERTAR,
        EDITAR
    }

    private TipoOperacion operacion;

    //flows
    public String irAgregar() {
        anuncio = new Anuncio();
        operacion = TipoOperacion.INSERTAR;
        return "flowAgregar";
    }

    public String irEditar(Anuncio a) {
        anuncio = new Anuncio();
        operacion = TipoOperacion.EDITAR;
        return "flowEditar";
    }

    public String irlistar() {
        return "flowListar";
    }

//logica Vista


    public void inicio() {
        obtenerAnuncio = logicaAnuncio.obtenerTodos();
    }

    public List<Anuncio> obtenerAnuncio() {
        return obtenerAnuncio();
    }

    public void setAnuncio(List<Anuncio> obtenerAnuncio) {
        this.obtenerAnuncio = obtenerAnuncio;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}
