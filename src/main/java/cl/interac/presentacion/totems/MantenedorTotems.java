package cl.interac.presentacion.totems;

import cl.interac.entidades.Totem;
import cl.interac.entidades.Ubicacion;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaTotem;
import cl.interac.negocio.LogicaUbicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("Prototype")
public class MantenedorTotems implements Serializable
{

    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUbicacion logicaUbicacion;


    private Totem totem;
    private List<Totem> totems;
    private List<Totem> totemConFiltro;
    private Ubicacion ubicacion;


    // logica vista
    public void mantenedorTotems() {
        totem = new Totem();
    }
    public void agregarTotem(){
        logicaTotem.guardar(totem);
        logicaUbicacion.guardar(ubicacion);

    }

     public void eliminarTotem(){
             totems.remove(totem);
             logicaTotem.eliminarTotem(totem);
         }



    public void inicio(){
        logicaTotem.obtenerConRelacion();
        logicaEstablecimiento.ObtenerConRelacion();
        totem = new Totem();

    }
    //get and set

    public List<Totem> getTotemConFiltro() {
        return totemConFiltro;
    }

    public void setTotemConFiltro(List<Totem> totemConFiltro) {
        this.totemConFiltro = totemConFiltro;
    }
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




