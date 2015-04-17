/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.presentacion;

//import cl.interac.entidades.RolUsuario;
//import cl.interac.entidades.Sucursal;
import cl.interac.Entidades.Usuario;
//import cl.interac.negocio.LogicaRolUsuario;
//import cl.interac.negocio.LogicaSucursal;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;

import java.io.Serializable;
import java.util.List;

import cl.interac.util.services.FileUploader;
import cl.interac.security.SHA512;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author interac
 */
@Component
@Scope("flow")
public class MantenedorUsuario implements Serializable {

    private List<Usuario> usuarios;
   // private List<RolUsuario> roles;
   // private List<Sucursal> sucursales;
    //private MantenedorSucursal sucursal;
    private Usuario usuarioSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    
    private TipoOperacion operacion;
    
    private transient UploadedFile foto;

    @Autowired
    private LogicaUsuario logicaUsuario;

  /*  @Autowired
   private LogicaRolUsuario logicaRolUsuario;

    @Autowired
  private LogicaSucursal logicaSucursal;*/

    @Autowired
    private FileUploader fileUploader;

    public void inicio() {
        usuarios = logicaUsuario.obtenerTodos();
       // roles = logicaRolUsuario.obtenerTodos();
       // sucursales = logicaSucursal.obtenerTodos();
    }

    public String irAgregar() {
        usuarioSeleccionado = new Usuario();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(Usuario u) {
        usuarioSeleccionado = u;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarUsuario() {
        usuarios.remove(usuarioSeleccionado);
        logicaUsuario.eliminarUsuario(usuarioSeleccionado);
    }

    public void guardarUsuario() {
        if (foto != null) {
            String path = fileUploader.subir(foto, "/usuarios/"+usuarioSeleccionado.getIdUsuario());
            usuarioSeleccionado.setFotoUsuario(path);
        }
        usuarioSeleccionado.setPasswordUsuario(SHA512.encode(usuarioSeleccionado.getPasswordUsuario()));
        logicaUsuario.guardarUsuario(usuarioSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha creado el nuevo usuario [ID: " + usuarioSeleccionado.getIdUsuario() + "]");
            usuarios.add(usuarioSeleccionado);
            usuarioSeleccionado = new Usuario();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha editado el usuario [ID: " + usuarioSeleccionado.getIdUsuario() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

  /*  public List<RolUsuario> getRoles() {
        return roles;
    }

    public void setRoles(List<RolUsuario> roles) {
        this.roles = roles;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
    */
    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }
}
