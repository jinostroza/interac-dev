package cl.apptec.util.components;

import cl.apptec.entidades.Usuario;
import cl.apptec.negocio.LogicaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by claudio on 11-11-14.
 */
@Component
@Scope("session")
public class UserSession implements Serializable {
    @Autowired
    private LogicaUsuario logicaUsuario;

    private Usuario usuario;

    public UserSession() {
        usuario = null;
    }

    public Usuario getUsuario() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();

        if (a == null || "anonymousUser".equals(a.getName())) {
            usuario = null;
        } else if (!a.getName().equals("anonymousUser")) {
            try {
                if (usuario == null || !usuario.getUsernameUsuario().equals(a.getName()))
                    usuario = logicaUsuario.obtenerUsuario(a.getName());
            } catch (Exception e) {
                usuario = null;
            }
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
