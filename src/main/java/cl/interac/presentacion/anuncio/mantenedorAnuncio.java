package cl.interac.presentacion.anuncio;
import java.io.Serializable;
import java.util.List;

import cl.interac.entidades.Anuncio;

import cl.interac.entidades.Usuario;
import cl.interac.util.components.FacesUtil;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cl.interac.negocio.LogicaAnuncio;


/**
 * Created by luis on 23-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorAnuncio implements Serializable {

private List<Anuncio> anuncios;

private Anuncio anuncio;

  private Anuncio anuncioSeleccionado;


  public enum TipoOperacion{
    INGRESAR,
    EDITAR;

  }

  private transient UploadedFile foto;
  private TipoOperacion operacion;

  public void inicio() {
    anuncios = logicaAnuncio.obtenerTodos();
  }


  @Autowired
  private LogicaAnuncio logicaAnuncio;
  // flows
  public void guardarAnuncio() {

    if (operacion == TipoOperacion.INGRESAR) {
    } else {
    }
  }
  public void signUp() {
    System.err.println("LLEGO A REGISTRAR");
    logicaAnuncio.guardar(anuncio);
    FacesUtil.mostrarMensajeInformativo("Resultado de la operación", "Anuncio guardado exitosamente");
  }
  public boolean esIngreso() {
    return operacion == TipoOperacion.INGRESAR;
  }

  public boolean esEdicion() {
    return operacion == TipoOperacion.EDITAR;
  }


  public List<Anuncio> getAnuncios() {
    return anuncios;
  }

  public void setAnuncios(List<Anuncio> anuncios) {
    this.anuncios = anuncios;
  }
}
