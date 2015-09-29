package cl.interac.presentacion.campana;

import cl.interac.entidades.Campana;
import cl.interac.entidades.Contenido;
import cl.interac.entidades.Totem;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaContenido;
import cl.interac.negocio.LogicaTotem;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luis on 25-04-2015.
 */

@Component
@Scope("flow")
public class MantenedorCampana implements Serializable {

    // manejo manual

    private List<Campana> campanas;
    private List<Totem> totems;
    private Campana campana;
    private int pasada;
    private int precio;
    private String retor;
    private String end1;
    private List<Usuario> usuarios;
    private Totem totem;
    private Totem totemSelecionado;
    private List<Totem> totemSelecionados;
    private List<Totem> totemsConrelacion;
    private List<Contenido> contenidos;
    private Contenido contenidosSelecionado;
    private List<Contenido> contenidosSelecionados;
    private Contenido contenido;

    private MapModel simpleModel;

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

    public void inicio() {

        totemsConrelacion = logicaTotem.obtenerConRelacion();

        campanas = logicaCampana.obtenerPorUsuario(userSession.getUsuario().getUsername());
        contenidos = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
        usuarios = logicaUsuario.obtenerTodos();
        simpleModel = new DefaultMapModel();
        for(Totem totem : totemsConrelacion) {
            simpleModel.addOverlay(new Marker(new LatLng(totem.getLat(), totem.getLongi()),totem.getEstablecimiento().getNombreEstablecimiento() ));
        }
    }

    public String guardar() {
        campana.setFechaCreacion(Date.from(Instant.now())); // new Date() hhace lo mismo
        campana.setContenido(contenidosSelecionado);
        campana.setTotem(totemSelecionado);
        campana.setPasadas(pasada);
        logicaCampana.guardarCampana(campana);
        FacesUtil.mostrarMensajeInformativo("operacion exitosa","se ha creado tu campaña");
        return "end1";
    }
    //gettet and setter


    public List<Totem> getTotemSelecionados() {
        return totemSelecionados;
    }

    public void setTotemSelecionados(List<Totem> totemSelecionados) {
        this.totemSelecionados = totemSelecionados;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPasada() {
        return pasada;
    }

    public void setPasada(int pasada) {
        this.pasada = pasada;
    }

    public List<Totem> getTotemsConrelacion() {
        return totemsConrelacion;
    }

    public void setTotemsConrelacion(List<Totem> totemsConrelacion) {
        this.totemsConrelacion = totemsConrelacion;
    }

    public Totem getTotemSelecionado() {
        return totemSelecionado;
    }

    public void setTotemSelecionado(Totem totemSelecionado) {
        this.totemSelecionado = totemSelecionado;
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

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
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

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }
}










