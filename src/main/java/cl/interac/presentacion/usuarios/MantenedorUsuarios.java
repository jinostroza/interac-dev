package cl.interac.presentacion.usuarios;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaRol;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * @author interac
 */
@Component
@Scope("view")
public class MantenedorUsuarios implements Serializable {
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private LogicaRol logicaRol;

    private Rol rol;
    private List<Rol> roles;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosFiltro;
    private Usuario usuario;

    @PostConstruct
    public void inicio() {
        usuarios = logicaUsuario.obtenetConRol();
        roles = logicaRol.obtenerTodos();
        usuario = new Usuario();
    }

    public void editarPerfil(Usuario u) {
        usuario = u;
        logicaUsuario.guardar(usuario);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el establecimiento [" + usuario.getUsername() + "]");
    }


    public void guardarUsuario() {
        usuario.setRol(rol);
        logicaUsuario.guardar(usuario);

        FacesUtil.mostrarMensajeInformativo("el usuario", "[" + usuario.getUsername() + "] ha sido registrado con exito");
    }

    // getter and settter

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuariosFiltro() {
        return usuariosFiltro;
    }

    public void setUsuariosFiltro(List<Usuario> usuariosFiltro) {
        this.usuariosFiltro = usuariosFiltro;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}