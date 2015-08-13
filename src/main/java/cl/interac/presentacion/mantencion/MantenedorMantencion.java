package cl.interac.presentacion.mantencion;

import cl.interac.presentacion.campana.MantenedorCampana;
import cl.interac.presentacion.establecimiento.MantenedorEstablecimiento;
import cl.interac.presentacion.totems.MantenedorTotems;
<<<<<<< HEAD
import cl.interac.presentacion.categorias.MantenedorCategoria;
=======
import cl.interac.presentacion.usuarios.MantenedorUsuarios;
>>>>>>> ca5e50f17c6dc7209764d3cbe7af29094fc3ad38
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
    @Autowired
<<<<<<< HEAD
    private MantenedorCategoria mantenedorCategoria;
=======
    private MantenedorUsuarios mantenedorUsuarios;
>>>>>>> ca5e50f17c6dc7209764d3cbe7af29094fc3ad38

    public void cambioPestania(TabChangeEvent event) {
        String nombrePestania = event.getTab().getTitle();

        if (nombrePestania.equals("Establecimiento")) {
            mantenedorEstablecimiento.inicio();
        } else if (nombrePestania.equals("Totem")) {
            mantenedorTotems.inicio();
        }else if (nombrePestania.equals("Perfiles")){
            mantenedorUsuarios.inicio();
        }
        else if (nombrePestania.equals("Categorias Campaña")) {
            mantenedorCategoria.inicio();
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
    public MantenedorCategoria getMantenedorCategoria() {
        return mantenedorCategoria;
    }

    public void setMantenedorCategoria(MantenedorCategoria mantenedorCategoria) {
        this.mantenedorCategoria = mantenedorCategoria;
    }

    public MantenedorUsuarios getMantenedorUsuarios() {
        return mantenedorUsuarios;
    }

    public void setMantenedorUsuarios(MantenedorUsuarios mantenedorUsuarios) {
        this.mantenedorUsuarios = mantenedorUsuarios;
    }
}