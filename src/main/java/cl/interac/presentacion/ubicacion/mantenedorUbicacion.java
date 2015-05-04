package cl.interac.presentacion.ubicacion;

import cl.interac.entidades.Ubicacion;
import cl.interac.negocio.LogicaUbicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 27-04-2015.
 */

@Component
@Scope("flow")
public class mantenedorUbicacion implements Serializable {

    public Ubicacion ubicacion;

    @Autowired

    private LogicaUbicacion logicaUbicacion;

    private List<Ubicacion> obtenerUbicacion;

    public void inicio(){ubicacion = new Ubicacion();}


    public List<Ubicacion> getObtenerUbicacion(){return obtenerUbicacion ;}

    public void setObtenerUbicacion(List<Ubicacion> obtenerUbicacion){this.obtenerUbicacion = obtenerUbicacion;}


}
