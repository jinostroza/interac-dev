package cl.interac.util.components;

import cl.interac.dao.UsuarioDAO;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by claudio on 11-11-14.
 */
@Component
@Scope("session")
public class UserSession implements Serializable {
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private Constantes constantes;
    @Autowired
    private transient UsuarioDAO usuarioDAO;

    private Usuario usuario;

    private boolean InicioSesion;



    public Usuario getUsuario() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();

        if (a == null || "anonymousUser".equals(a.getName())) {
            usuario = null;
        } else if (!a.getName().equals("anonymousUser")) {
            try {
                if (usuario == null || !usuario.getUsername().equals(a.getName()))
                    usuario = logicaUsuario.obtener(a.getName());
            } catch (Exception e) {
                usuario = null;
            }
        }
        return usuario;
    }


    public UserSession() {
        usuario = null;
    }



    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public boolean estaAutentificado() {
        return getUsuario() != null;
    }

    public boolean tienePermiso(String permiso) {
        if (!this.estaAutentificado()) {
            return false;
        }
        HttpServletRequest request = (HttpServletRequest) FacesUtil.obtenerHttpServletRequest();
        return request.isUserInRole(permiso);
    }


}
