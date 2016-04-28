package cl.interac.presentacion.talleres;

import cl.interac.entidades.Taller;
import cl.interac.negocio.LogicaTaller;
import cl.interac.util.components.UserSession;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Created by joaco on 25/04/2016.
 */
@Component()
@Scope("view")

public class MantenedorLista implements Serializable {

    private List<Taller> tallerList;
    private Taller taller;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaTaller logicaTaller;

    @ManagedProperty("#{param.user}")
    private Integer user;
    @PostConstruct
    public void inicio(){

        tallerList=logicaTaller.obtenerTodos(2);
        taller = new Taller();
    }

    public void reset() {
        RequestContext.getCurrentInstance().reset("form:panel");
    }
//Getter and Setter
    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public List<Taller> getTallerList() {
        return tallerList;
    }

    public void setTallerList(List<Taller> tallerList) {
        this.tallerList = tallerList;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
