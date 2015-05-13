package cl.interac.util.components;

import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
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
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

}
