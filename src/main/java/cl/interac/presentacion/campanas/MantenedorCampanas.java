package cl.interac.presentacion.campanas;

import cl.interac.entidades.Campana;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

import cl.interac.negocio.LogicaCampana;
/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorCampanas implements Serializable
{
    public Campana tmpCampana;
    @Autowired
    private LogicaCampana logicaCampana;

    private List<Campana> campanas;

    public void inicio() {
        tmpCampana = new Campana();
    }

    public List<Campana> getCampanas() {
        return campanas;
    }

    public void setCampanas(List<Campana> campanas) {
        this.campanas = campanas;
    }

    public void CrearCampana()
    {
        if (tmpCampana!=null)
        {
            logicaCampana.guardarCampana(tmpCampana);
        }
        tmpCampana = new Campana();
    }
}
