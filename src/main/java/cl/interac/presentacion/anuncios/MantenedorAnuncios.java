package cl.interac.presentacion.anuncios;

import cl.interac.entidades.Campana;
import cl.interac.entidades.Categoria;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaCategoria;
import cl.interac.util.components.UserSession;
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
    private enum TipoOperacion {
        INSERTAR,
        EDITAR
    }
    private TipoOperacion operacion;

    private UserSession userSession;
    private List<Categoria> obtenerCategoria;
    private List<Anuncio> obtenerAnuncio;
    private List<Campana> obtenerCampanas;
    private Anuncio anuncio;
    private Campana campana;
    private Categoria categoria;
    @Autowired
    private LogicaAnuncio logicaAnuncio;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaCategoria logicaCategoria;


    //flows
  public boolean esEditar(){

      return operacion== TipoOperacion.EDITAR;
  }
    public boolean esAgregar(){
        return  operacion == TipoOperacion.INSERTAR;
    }

    public String irlistar() {
        return "flowListar";
    }

  //logica Vista
    public void guardar() {
       logicaAnuncio.guardar(anuncio);
        logicaCampana.guardarCampana(campana);
        logicaCategoria.guardar(categoria);
        if (esEditar()) {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado la campaña ["+anuncio.getDescanuncio()+"]");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado la campaña ["+anuncio.getDescanuncio()+"]");
        }
    }

    public void inicio() {
        obtenerCampanas = logicaCampana.obtenerTodos();
        obtenerCategoria = logicaCategoria.obtenerTodos();
        obtenerAnuncio = logicaAnuncio.obtenerConRelaciones();
          operacion = TipoOperacion.INSERTAR;
    }

    //get and set
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

    public List<Campana> getObtenerCampanas() {
        return obtenerCampanas;
    }

    public void setObtenerCampanas(List<Campana> obtenerCampanas) {
        this.obtenerCampanas = obtenerCampanas;
    }
    public List<Categoria> getObtenerCategoria() {
        return obtenerCategoria;
    }

    public void setObtenerCategoria(List<Categoria> obtenerCategoria) {
        this.obtenerCategoria = obtenerCategoria;
    }
}
