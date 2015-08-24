package cl.interac.presentacion.anuncios;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaContenido;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaCategoria;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cl.interac.negocio.LogicaAnuncio;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.*;
import javax.annotation.PostConstruct;
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
    private enum TipoOperacion {
        INSERTAR,
        EDITAR
    }

    private TipoOperacion operacion;
    private List<Categoria> categorias;
    private List<Campana> campanas;
    private List<Anuncio> anuncios;
    private Anuncio anuncio;

    private Categoria categoria;
    private Usuario usuario;
    private Contenido contenido;
    private List<Contenido> contenidoList;

    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private LogicaAnuncio logicaAnuncio;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaCategoria logicaCategoria;
    @Autowired
    private FileUploader fileUploader; // es un componente
    @Autowired
    private UserSession userSession;



    //flows
    public boolean esEditar() {
        return operacion == TipoOperacion.EDITAR;
    }

    public boolean esAgregar() {
        return operacion == TipoOperacion.INSERTAR;
    }


    //logica Vista
    public void guardar() {
        operacion = TipoOperacion.INSERTAR;
        anuncio.setCategoria(categoria);
        anuncio.setContenido(contenido);
        logicaAnuncio.guardar(anuncio);

         if (esEditar()) {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado la campaña [" + contenido.getPath() + "]");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado la campaña [" + contenido.getPath() + "]");
        }

    }

    public void eliminar() {
        logicaAnuncio.eliminarAnuncio(anuncio);
    }


    public void subir(FileUploadEvent fue) {
        operacion = TipoOperacion.INSERTAR;
        System.err.println("LLEGO A LA WA " + fue);
        String path = fileUploader.subir(fue, "/anuncios/");
        System.err.println("SE SUPONE QUE SUBI EN " + path);
        contenido = new Contenido();
        contenido.setUsuario(userSession.getUsuario());
          contenido.setPath(path);
          logicaContenido.guardar(contenido);
        if (esEditar()) {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado la campaña [" + contenido.getIdcontenido() + "]");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el afiche [" + contenido.getIdcontenido() + "]");
        }

    }

    @PostConstruct
    public void inicio() {
        categorias = logicaCategoria.obtenerTodos();
        campanas = logicaCampana.obtenerTodos();
        contenidoList = logicaContenido.obtenerTodos();
        anuncios = logicaAnuncio.obtenerConRelaciones();

        anuncio = new Anuncio();
    }


    //get and set

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public List<Campana> getCampanas() {
        return campanas;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setCampanas(List<Campana> campanas) {
        this.campanas = campanas;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }



}
