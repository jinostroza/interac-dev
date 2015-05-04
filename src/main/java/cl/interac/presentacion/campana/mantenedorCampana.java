package cl.interac.presentacion.campana;

import cl.interac.dao.CampanaDAO;

import cl.interac.entidades.Campana;
import cl.interac.negocio.LogicaCampana;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 25-04-2015.
 */
@Component
@Scope("flow")
public class mantenedorCampana implements Serializable {

    @Autowired
     private LogicaCampana logicaCampana;
    private List<Campana> obtenerCampana;

    private Campana campana;


    public void inicio() { obtenerCampana  = logicaCampana.obtenerTodos();}

    public mantenedorCampana() {campana = new Campana();}

    public Campana getCampana() {
        return campana;
    }

    public void setCampana(List<Campana> obtenerCampana) {
        this.obtenerCampana = obtenerCampana;
    }
    public List<Campana> getObtenerCampana(){return obtenerCampana;}

}







