package cl.interac.presentacion.establecimiento;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaTotem;
import cl.interac.negocio.LogicaUbicacion;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.presentacion.campana.MantenedorCampana;
import cl.interac.util.components.FacesUtil;
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
    private TipoOperacion operacion;
    private List<Establecimiento> establecimientoList;
    private List<Totem> totems;
    private List<Ubicacion> ubicacions;
    private List<Usuario> usuario;
    private String text1;


    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaUbicacion logicaUbicacion;
    @Autowired
    private LogicaTotem logicaTotem;

    public boolean esEditar() {
        return operacion == TipoOperacion.EDITAR;
    }

    public boolean esAgregar() {
        return operacion == TipoOperacion.INSERTAR;
    }

    public void buscar(String text1){
        logicaEstablecimiento.buscar(text1);

    }


    public MantenedorEstablecimiento(){new Establecimiento();}
    public void agregarEstablecimiento(){
        logicaEstablecimiento.guardar(establecimiento);
        establecimiento.setUsuario(userSession.getUsuario());

    }
    public void inicio(){

       establecimientoList = logicaEstablecimiento.obtenerTodos();


    }

    public TipoOperacion getOperacion() {
        return operacion;
    }

    public void setOperacion(TipoOperacion Operacion) {
        operacion = Operacion;
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

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }


}
