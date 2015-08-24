package cl.interac.presentacion.anuncios;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaContenido;
import cl.interac.negocio.LogicaUsuario;
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

import java.lang.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.FileInputStream;
import java.io.Serializable;
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
    @PostConstruct
    public void inicio() {
        usuarios = logicaUsuario.obtenerTodos();
        contenidoList = logicaContenido.obtenerTodos();
        contenido = new Contenido();
    }
    public void subir(FileUploadEvent fue) {
        operacion = TipoOperacion.INSERTAR;
        System.err.println("LLEGO A LA WA " + fue);
        String path = fileUploader.subir(fue, "/anuncios/");
        System.err.println("SE SUPONE QUE SUBI EN " + path);
        String cont = fue.getFile().getFileName();
        contenido = new Contenido();
        contenido.setUsuario(userSession.getUsuario());
        contenido.setPath(cont);
        logicaContenido.guardar(contenido);
        if (esEditar()) {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado la campaña [" +cont + "]");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el afiche [" + cont + "]");
        }

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

}
