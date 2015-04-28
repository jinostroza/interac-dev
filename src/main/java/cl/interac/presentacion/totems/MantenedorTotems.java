package cl.interac.presentacion.totems;

import cl.interac.entidades.Totem;
import cl.interac.negocio.LogicaTotem;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorTotems implements Serializable {

    private List<Totem> totems;

    private Totem totem;

    private Totem totemSeleccionado;

    public enum TipoOperacion {
        INGRESAR,
        EDITAR;
    }

    private transient UploadedFile foto;
    private TipoOperacion operacion;

    public void inicio() {totems = logicaTotem.obtenerTodos();
    }


    @Autowired
    private LogicaTotem logicaTotem;

    // flows
    public void guardarTotem() {

        if (operacion == TipoOperacion.INGRESAR) {
        } else {
        }
    }

    public void signUp() {
        System.err.println("LLEGO A REGISTRAR");
        logicaTotem.guardar(totem);
        FacesUtil.mostrarMensajeInformativo("Resultado de la operación", "Anuncio guardado exitosamente");
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<Totem> getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }
}