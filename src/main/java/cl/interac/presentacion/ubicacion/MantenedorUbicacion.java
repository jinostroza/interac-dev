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
public class MantenedorUbicacion implements Serializable {

  private Ubicacion ubicacion;

    @Autowired

    private LogicaUbicacion logicaUbicacion;

    private List<Ubicacion> obtenerUbicacion;

    public void inicio(){ubicacion = new Ubicacion();}

    public void guardarUb(){logicaUbicacion.guardar(ubicacion);}

    public List<Ubicacion> getObtenerUbicacion(){return obtenerUbicacion ;}

    public void setObtenerUbicacion(List<Ubicacion> obtenerUbicacion){this.obtenerUbicacion = obtenerUbicacion;}

     public Ubicacion getUbicacion(){return ubicacion;}

}
