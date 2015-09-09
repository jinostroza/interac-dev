package cl.interac.presentacion;

import cl.interac.entidades.Establecimiento;
import cl.interac.entidades.Tipototem;
import cl.interac.entidades.Totem;
import cl.interac.entidades.Ubicacion;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaTipototem;
import cl.interac.negocio.LogicaTotem;
import cl.interac.negocio.LogicaUbicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by luisPc on 31-08-2015.
 */
@Component
@Scope("view")
public class InicioPlataforma {

    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaTipototem logicaTipototem;
    @Autowired
    private LogicaUbicacion logicaUbicacion;


    private List<Totem> totemList;
    private Totem totem;
    private Establecimiento establecimiento;
    private List<Establecimiento> establecimientoList;
    private List<Tipototem> tipototems;
    private List<Ubicacion> ubicacionList;

    @PostConstruct
    public void inicio(){
        totem = new Totem();
        establecimientoList = logicaEstablecimiento.obtenerTodos();
        totemList = logicaTotem.obtenerConRelacion();
        tipototems = logicaTipototem.obtenerConRelacion();
        ubicacionList = logicaUbicacion.obtenerTodas();
    }

    public List<Totem> getTotemList() {
        return totemList;
    }

    public void setTotemList(List<Totem> totemList) {
        this.totemList = totemList;
    }

    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    public List<Tipototem> getTipototems() {
        return tipototems;
    }

    public void setTipototems(List<Tipototem> tipototems) {
        this.tipototems = tipototems;
    }
}