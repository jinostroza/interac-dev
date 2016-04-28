package cl.interac.presentacion.talleres;

import cl.interac.entidades.Taller;
import cl.interac.negocio.LogicaTaller;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mary on 07/04/2016.
 */
@Component()
@Scope("flow")
public class MantenedorTaller implements Serializable {

    private List<Taller> tallerList;
    private Taller taller;
    @Autowired
    private UserSession userSession;

    @Autowired
    private LogicaTaller logicaTaller;

    @PostConstruct
    public void inicio(){

        tallerList=logicaTaller.obtenerTodos(userSession.getUsuario().getIdUsuario());
        taller = new Taller();
    }
    public String agregar(){
        taller.setUsuario(userSession.getUsuario());

        logicaTaller.guardar(taller);

        tallerList=logicaTaller.obtenerTodos(userSession.getUsuario().getIdUsuario());
        FacesUtil.mostrarMensajeInformativo("Exito","Se Agrego nuevo elemento :  "+taller.getNombre());
     return "next" ;
    }
    public String editar(Taller t){
        taller=t;
        logicaTaller.guardar(taller);
        tallerList=logicaTaller.obtenerTodos(userSession.getUsuario().getIdUsuario());
        FacesUtil.mostrarMensajeInformativo("Exito","Se ha modificado elemento :  "+taller.getNombre());
        return "next" ;
    }
    public String eliminar(Taller t){

        taller=t;
        logicaTaller.eliminar(taller);
        tallerList=logicaTaller.obtenerTodos(userSession.getUsuario().getIdUsuario());
        FacesUtil.mostrarMensajeError("Exito", "Se ha eliminado elemento :  " + taller.getNombre());
        return "next" ;
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
}
