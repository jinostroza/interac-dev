package cl.interac.presentacion.anuncio;
import java.io.Serializable;
import java.util.List;

import cl.interac.entidades.Anuncio;

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
public class mantenedorAnuncio implements Serializable {

private List<Anuncio> anuncios;



  // private List<RolUsuario> roles;
  // private List<Sucursal> sucursales;
  //private MantenedorSucursal sucursal;
  private Anuncio anuncioSeleccionado;


  public enum tipoOperacion{
    INGRESAR,
    EDITAR;

  }


/*  private TipoOperacion operacion;

  private transient UploadedFile foto;

  @Autowired
  private LogicaAnuncio logicaAnuncio;
  // flows
  public String irAgregar() {
    atribucionSeleccionada  = new ();
    operacion = TipoOperacion.INGRESAR;
    return "flowAgregar";
  }

  public String irEditar(Atribucion a) {
    atribucionSeleccionada = a;
    operacion = TipoOperacion.EDITAR;
    return "flowAgregar";
  }

  public String irListar() {
    return "flowListar";
  }

  // logica vista
  public void eliminarAtribuciones() {
    atribuciones.remove(atribucionSeleccionada);
    logicaAtribucion.eliminarAtribucion(atribucionSeleccionada);
  }

  public void guardarAtribucion() {
    logicaAtribucion.guardarAtribucion(atribucionSeleccionada);
    if (operacion == TipoOperacion.INGRESAR) {
      FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nueva atribucion [ID: " + atribucionSeleccionada.getIdAtribucion() + "]");
      atribuciones.add(atribucionSeleccionada);
      atribucionSeleccionada = new Atribucion();
    } else {
      FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado atribucion [ID: " + atribucionSeleccionada.getIdAtribucion() + "]");
    }
  }

  public boolean esIngreso() {
    return operacion == TipoOperacion.INGRESAR;
  }

  public boolean esEdicion() {
    return operacion == TipoOperacion.EDITAR;
  }

  public List<Atribucion> getAtribuciones() {
    return atribuciones;
  }

  public void setAtribuciones(List<Atribucion> atribuciones) {
    this.atribuciones = atribuciones;
  }

  public Atribucion getAtribucionSeleccionada() {
    return atribucionSeleccionada;
  }

  public void setAtribucionSeleccionada(Atribucion atribucionSeleccionada) {
    this.atribucionSeleccionada = atribucionSeleccionada;
  }

*/

}
