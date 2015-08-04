package cl.interac.presentacion.establecimiento;

import cl.interac.entidades.Afiche;
import cl.interac.entidades.Establecimiento;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.presentacion.campana.MantenedorCampana;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pedro Pablo on 22-05-2015.
 */
@Component
@Scope("flow")
public class MantenedorEstablecimiento implements Serializable {
    public Establecimiento establecimiento;
    public enum TipoOperacion{
        INSERTAR,
        EDITAR,
    }

    //manejo manual
    private TipoOperacion Operacion;
    private List<Establecimiento> establecimientoList;

    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private UserSession userSession;


    public MantenedorEstablecimiento(){new Establecimiento();}
    public void agregarEstablecimiento(){
        logicaEstablecimiento.guardar(establecimiento);
    }
    public void inicio(){ logicaEstablecimiento.obtenerTodos();}

    public TipoOperacion getOperacion() {
        return Operacion;
    }

    public void setOperacion(TipoOperacion operacion) {
        Operacion = operacion;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

}
