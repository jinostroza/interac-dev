package cl.interac.presentacion.anuncios;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaAnuncio;
import cl.interac.negocio.LogicaContenido;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Joaco on 17/08/2015.
 */

@Component
@Scope("flow")
public class MantenedorContenido implements Serializable {

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
    @Autowired
    private PropertyReader propertyReader;

    public MantenedorContenido() {
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
        contenidoList = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
        contenido = new Contenido();
    }

    public void subir(FileUploadEvent fue) {
        operacion = TipoOperacion.INSERTAR;
        contenido = new Contenido();

        try {
            String pathTemporal = fileUploader.subir(fue, "/contenido");

            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente))
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                contenido.setPath(pathTemporal);
            else if ("produccion".equals(ambiente)) {
                // si es producción estamos obligado a usar el ftp
                String totem = "colivares"; // por ahora, después se suponeque cambia

                // obtenemos el formato del archivo buscando el último .
                String nombreArchivo = pathTemporal.substring(pathTemporal.lastIndexOf('.'));

                // debemos asegurarnos que el nombre será unico para que no pise otra cosa en el FTP
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");

                // por ende le pasamos la fecha con hora minuto y segundo + formato rescatado anteriormente
                nombreArchivo = sdf.format(new Date()) + nombreArchivo;
                Files.copy(Paths.get(pathTemporal), Paths.get("/home/ec2-user/media/" + totem + "/" + nombreArchivo));
                contenido.setPath("/home/ec2-user/media/"+totem+"/"+nombreArchivo);
            }

            // error ql wn, estabamos mandando nul pq el path se seteaba antes de la instancia,silovi

            contenido.setUsuario(userSession.getUsuario());
            logicaContenido.guardar(contenido);

            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Su imagen a sido subida ");

        } catch (Exception e) {
            return;
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

    public List<Contenido> getSelecContenido() {
        return selecContenido;
    }

    public void setSelecContenido(List<Contenido> selecContenido) {
        this.selecContenido = selecContenido;
    }

}
