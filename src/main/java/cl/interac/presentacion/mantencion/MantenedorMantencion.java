package cl.interac.presentacion.mantencion;

import cl.interac.presentacion.campana.MantenedorCampana;
import cl.interac.presentacion.establecimiento.MantenedorEstablecimiento;
import cl.interac.presentacion.totems.MantenedorTotems;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by luisPc on 05-08-2015.
 */
@Component
@Scope("flow")
public class MantenedorMantencion implements Serializable {

    @Autowired
    private MantenedorEstablecimiento mantenedorEstablecimiento;
    @Autowired
    private MantenedorTotems mantenedorTotems;

    public void cambioPestania(TabChangeEvent event) {
        String nombrePestania = event.getTab().getTitle();

        if (nombrePestania.equals("Establecimiento")) {
            mantenedorEstablecimiento.inicio();
        } else if (nombrePestania.equals("Totem")) {
           mantenedorTotems.inicio();
        }
    }

    // getter and setter
    public MantenedorEstablecimiento getMantenedorEstablecimiento() {
        return mantenedorEstablecimiento;
    }

    public void setMantenedorEstablecimiento(MantenedorEstablecimiento mantenedorEstablecimiento) {
        this.mantenedorEstablecimiento = mantenedorEstablecimiento;
    }
    public MantenedorTotems getMantenedorTotems() {
        return mantenedorTotems;
    }

    public void setMantenedorTotems(MantenedorTotems mantenedorTotems) {
        this.mantenedorTotems = mantenedorTotems;
    }

}