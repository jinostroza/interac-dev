package cl.interac.presentacion;

import cl.interac.entidades.Anuncio;
import cl.interac.negocio.LogicaAnuncio;
import cl.interac.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by claudio on 24-04-15.
 */
@Component
@Scope("view")
public class CreaAnuncio {
    @Autowired

    private LogicaAnuncio logicaAnuncio;
    private Anuncio anuncio;

    // Spring nos instanciará el bean cuando cree el componente, pero antes debemos setear el usuario para poder usar
    // sus atributos en el jsf
    public CreaAnuncio () {
        anuncio = new Anuncio();
    }

    public void signUp() {
        System.err.println("LLEGO A REGISTRAR");
        logicaAnuncio.guardar(anuncio);
        FacesUtil.mostrarMensajeInformativo("Resultado de la operación", "Anuncio Creado exitosamente");
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}
