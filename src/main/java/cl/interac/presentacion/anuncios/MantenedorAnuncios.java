package cl.interac.presentacion.anuncios;

import cl.interac.entidades.Usuario;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cl.interac.entidades.Anuncio;
import cl.interac.negocio.LogicaAnuncio;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorAnuncios implements Serializable {
    @Autowired
    private LogicaAnuncio logicaAnuncio;
    private Anuncio anuncio;

    public MantenedorAnuncios () {
        anuncio = new Anuncio();
    }

    public enum TipoOperacion {
        INGRESAR,
        EDITAR;
    }

    public void UploadFile(FileUploadEvent event){

        anuncio.setMedia(event.getFile().getFileName());
    }
    private TipoOperacion operacion;

    public void inicio() {
        operacion = TipoOperacion.INGRESAR;
    }

    // flows
    public void guardar() {
        logicaAnuncio.guardar(anuncio);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado correctamente el anuncio");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado correctamente el anuncio");
        }
    }


    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}