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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private List<Anuncio> obtenerAnuncio;

    public MantenedorAnuncios () {
        anuncio = new Anuncio();
    }



    public void upload() {
        if(anuncio != null) {
            System.err.println("LLEGO A REGISTRAR");
            logicaAnuncio.guardar(anuncio);
            FacesMessage message = new FacesMessage("Succesful", anuncio.getMedia() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }





    // flows
    public void guardar() {

        System.err.println("su anuncio a sido subido saticfactoriamente");
        logicaAnuncio.guardar(anuncio);

    }


    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}