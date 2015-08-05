package cl.interac.presentacion.mantencion;

import cl.interac.presentacion.establecimiento.MantenedorEstablecimiento;
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

    public void cambioPestania(TabChangeEvent event) {
        String nombrePestania = event.getTab().getTitle();

        if (nombrePestania.equals("Establecimiento")) {
            mantenedorEstablecimiento.inicio();
        }
        if(nombrePestania.equals("totems")){

        }
    }

    public MantenedorEstablecimiento getMantenedorEstablecimiento() {
        return mantenedorEstablecimiento;
    }

    public void setMantenedorEstablecimiento(MantenedorEstablecimiento mantenedorEstablecimiento) {
        this.mantenedorEstablecimiento = mantenedorEstablecimiento;
    }
}