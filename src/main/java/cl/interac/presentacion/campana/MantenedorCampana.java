package cl.interac.presentacion.campana;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * Created by luis on 25-04-2015.
 */

@Component
@Scope("flow")
public class MantenedorCampana implements Serializable {

    // manejo manual

    private List<Campana> campanas;
    private List<Campana> campanaList;
    private List<Totem> totems;
    private Campana campana;
    private Integer precio;

    public List<Campana> getCampanaList() {
        return campanaList;
    }

    public void setCampanaList(List<Campana> campanaList) {
        this.campanaList = campanaList;
    }

    private String retor;
    private String end1;
    private List<Usuario> usuarios;
    private Totem totem;
    private Totem[] totemSelecionados;
    private List<Totem> totemsConrelacion;
    private List<Totem> totemCampana;
    private Contenido contenido;
    private List<Contenido> contenidos;
    private List<Categoria> categoriaList;
    private Categoria categoria;
    private MapModel simpleModel;
    private Contenido contenidosSelecionado;
    private List<Contenido> contenidosSelecionados;

    @Autowired
    private LogicaCategoria logicaCategoria;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private FileUploader fileUploader;
    private int fileUploadCount;


    public void inicio() {
        usuarios = logicaUsuario.obtenerTodos();
        categoriaList = logicaCategoria.obtenerTodos();
        totemsConrelacion = logicaTotem.obtenerConRelacion();
        totems = logicaTotem.obtenerPorUsuario(userSession.getUsuario().getUsername());
        campanas = logicaCampana.obtenerPorUsuario(userSession.getUsuario().getUsername());
        contenidos = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
        campanaList= logicaCampana.obtenerLasCampanasDeLosTotems(userSession.getUsuario().getUsername());
        totemCampana = logicaTotem.obtenerDeCampana(userSession.getUsuario().getUsername());
        usuarios = logicaUsuario.obtenerTodos();
        simpleModel = new DefaultMapModel();

        for(Totem totem: totemsConrelacion) {
            simpleModel.addOverlay(new Marker(new LatLng(totem.getLat(), totem.getLongi()),totem.getEstablecimiento().getNombreEstablecimiento() ));
        }

    }

    public void subir(FileUploadEvent fue) {


        contenido = new Contenido();

        try {
            String pathTemporal = fileUploader.subir(fue,"/contenido");

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
                contenido.setPath(nombreArchivo);
            }

            // error ql wn, estabamos mandando nul pq el path se seteaba antes de la instancia,silovi

            contenido.setUsuario(userSession.getUsuario());
            contenido.setEstado("Validando");
            logicaContenido.guardar(contenido);


            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Su imagen a sido subida ");
            fileUploadCount = fileUploadCount + 1;

        } catch (Exception e) {
            return;
        }
    }
    public String editarContenido(Contenido c) {
        contenido = c;
        contenido.setCategoria(categoria);
        System.err.println("Estado:"+ contenido.getEstado());
        logicaContenido.guardar(contenido);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el Contenido [" + contenido.getIdcontenido() + "]");

      return irCrear(c);

    }

    public String irPagar(Campana ca ){
        campana = ca ;
        return "paga";
    }

     public String irCrear(Contenido c) {
         contenido = c;
         campana = new Campana();
         return "subir";
    }

    public String guardar() {
        System.out.println("llego");
        try {
            campana.setContenido(contenido);
            System.err.print(contenido.getIdcontenido());
            campana.setTotemList(Arrays.asList(totemSelecionados));
            campana.setFechaCreacion(Date.from(Instant.now()));
            logicaCampana.guardarCampana(campana);
            FacesUtil.mostrarMensajeInformativo("operacion exitosa","se ha creado tu campaña");

        }catch (Exception e){
            FacesUtil.mostrarMensajeInformativo("operacion no exitosa","ocurrio Algo");
        }
        return "end1";
    }

    public void eliminarFichero(Contenido conte){

        try {
            logicaContenido.eliminarContenido(conte);
            Files.delete(Paths.get("/home/ec2-user/media/colivares/" + conte.getPath()));
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha borrado la imagen");

        }catch (Exception e){
            FacesUtil.mostrarMensajeInformativo("Operación Fallida","Algo Ocurrio");
        }
    }
    public String ver(int t){
        System.err.println("Totem:"+ t);
        logicaCampana.obtenerPorIdConTotems(t);


        return "ver";

    }
    public void calculator(){
         precio= precio * 3000;

    }


   //getter and setter
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public Integer getFileUploadCount() {
        return fileUploadCount;
    }

    public void setFileUploadCount(Integer fileUploadCount) {
        this.fileUploadCount = fileUploadCount;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public Totem[] getTotemSelecionados() {
        return totemSelecionados;
    }

    public void setTotemSelecionados(Totem[] totemSelecionados) {
        this.totemSelecionados = totemSelecionados;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public List<Totem> getTotemsConrelacion() {
        return totemsConrelacion;
    }

    public void setTotemsConrelacion(List<Totem> totemsConrelacion) {
        this.totemsConrelacion = totemsConrelacion;
    }

    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
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

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }
    public Contenido getContenidosSelecionado() {
        return contenidosSelecionado;
    }

    public void setContenidosSelecionado(Contenido contenidosSelecionado) {
        this.contenidosSelecionado = contenidosSelecionado;
    }

    public List<Contenido> getContenidosSelecionados() {
        return contenidosSelecionados;
    }

    public void setContenidosSelecionados(List<Contenido> contenidosSelecionados) {
        this.contenidosSelecionados = contenidosSelecionados;
    }

    public List<Totem> getTotemCampana() {
        return totemCampana;
    }

    public void setTotemCampana(List<Totem> totemCampana) {
        this.totemCampana = totemCampana;
    }
}










