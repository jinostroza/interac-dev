package cl.interac.presentacion.anuncios;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaContenido;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.UserSession;
import cl.interac.negocio.LogicaAnuncio;
import cl.interac.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cl.interac.negocio.LogicaAnuncio;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.lang.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
/**
 * Created by Joaco on 17/08/2015.
 */

@Component
@Scope("flow")
public class MantenedorContenido implements Serializable{

    private enum TipoOperacion {
        INSERTAR,
        EDITAR
    }
    private TipoOperacion operacion;
    private List<Campana> campanas;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private Contenido contenido;
    private List<Contenido> contenidoList;
    private Anuncio anuncio;
    private Categoria categoria;
    private List<Contenido> selecContenido;

    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private LogicaAnuncio logicaAnuncio;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private FileUploader fileUploader; // es un componente
    @Autowired
    private UserSession userSession;

    @Autowired
    private Constantes constantes;

    public MantenedorContenido(){
        contenido = new Contenido();
    }
    //flows
    public boolean esEditar() {
        return operacion == TipoOperacion.EDITAR;
    }

    public boolean esAgregar() {
        return operacion == TipoOperacion.INSERTAR;
    }

    public void inicio() {
        usuarios = logicaUsuario.obtenerTodos();
        contenidoList = logicaContenido.obtenerTodos();
        contenido = new Contenido();
    }

    public void subir(FileUploadEvent fue) {
        operacion = TipoOperacion.INSERTAR;

            try {
                String path = fileUploader.subir(fue, "/anuncios/" + userSession.getUsuario().getUsername() + "/");
                System.err.println("SE SUPONE QUE SUBI EN " + path);
                contenido = new Contenido();
                contenido.setUsuario(userSession.getUsuario());
                contenido.setPath(path);
                logicaContenido.guardar(contenido);
                anuncio.setCategoria(categoria);
                logicaAnuncio.guardar(anuncio);
                 /*  boolean renombrado = archivo1.renameTo(archivo2);
                     File archivo1 = new File(path);
                File archivo2 = new File(constantes.getPathArchivos() +"/anuncios/"+userSession.getUsuario().getUsername()+"/"  + ".jpg");
                if (renombrado) {
                    FacesUtil.mostrarMensajeInformativo(archivo2.getName(), "Archivo Renombrado con éxito");
                } else {
                    FacesUtil.mostrarMensajeError(null, "No se pudo renombrar el archivo");
                } */
                    FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Su imagen a sido subida ");

            }catch (Exception e){ return;}
    }


    public void eliminar() {
       logicaContenido.eliminarContenido(contenido);
    }



    public List<Campana> getCampanas() {
        return campanas;
    }


    public void setCampanas(List<Campana> campanas) {
        this.campanas = campanas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
    public List<Contenido> getSelecContenido() {
        return selecContenido;
    }
    public void setSelecContenido(List<Contenido> selecContenido) {
        this.selecContenido = selecContenido;
    }

}
