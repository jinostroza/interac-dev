package cl.interac.presentacion.totems;

import cl.interac.entidades.Totem;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaTotem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorTotems implements Serializable
{
    public Totem totem;
    @Autowired
    private LogicaTotem logicaTotem;
    private LogicaCampana logicaCampana;

    private List<Totem> totems;

    // logica vista
    public void inicio() {
        totem = new Totem();
    }
    public void agregarTotem(){
        logicaTotem.guardar(totem);
    }

     public void eliminarTotem(){
             totems.remove(totem);
             logicaTotem.eliminarTotem(totem);
         }
     public void editarTotem(){
     }


    //get and set
    public List<Totem> getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }

    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    public LogicaTotem getLogicaTotem() {
        return logicaTotem;
    }

    public void setLogicaTotem(LogicaTotem logicaTotem) {
        this.logicaTotem = logicaTotem;
    }

    public LogicaCampana getLogicaCampana() {
        return logicaCampana;
    }

    public void setLogicaCampana(LogicaCampana logicaCampana) {
        this.logicaCampana = logicaCampana;
    }



}




