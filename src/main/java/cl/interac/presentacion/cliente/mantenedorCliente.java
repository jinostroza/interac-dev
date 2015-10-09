package cl.interac.presentacion.cliente;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by pclucho on 28-09-15.
 */
@Component
@Scope("flow")
public class mantenedorCliente implements Serializable {
    //entities
    private Totem totem;
    private Totem totemSeleccionado;
    private Campana campana;
    private Usuario usuario;
    private Establecimiento establecimiento;
    private Tipototem tipototem;
    //list
    private List<Totem> totemList;
    private List<Campana> campanaList;
    private List<Establecimiento> establecimientoList;
    private List<Tipototem> tipototemList;
    private List<Totem> totemSeleccionados;
    private List<Totem> totemCampana;

    //autowired
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaTipototem logicaTipototem;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;


    public void inicio(){
        totemList = logicaTotem.obtenerPorUsuario(userSession.getUsuario().getUsername());
        totemCampana = logicaTotem.obtenerDeCampana();

    }
//getter and setter


    public List<Totem> getTotemCampana() {
        return totemCampana;
    }

    public void setTotemCampana(List<Totem> totemCampana) {
        this.totemCampana = totemCampana;
    }

    public void setTotemSeleccionado(Totem totemSeleccionado) {
        this.totemSeleccionado = totemSeleccionado;
    }

    public List<Totem> getTotemSeleccionado() {
        return totemSeleccionados;
    }

    public void setTotemSeleccionado(List<Totem> totemSeleccionado) {
        this.totemSeleccionados = totemSeleccionados;
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

    public void setCampana(Campana campana) {
        this.campana = campana;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public List<Totem> getTotemList() {
        return totemList;
    }

    public void setTotemList(List<Totem> totemList) {
        this.totemList = totemList;
    }

    public List<Campana> getCampanaList() {
        return campanaList;
    }

    public void setCampanaList(List<Campana> campanaList) {
        this.campanaList = campanaList;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }
}
