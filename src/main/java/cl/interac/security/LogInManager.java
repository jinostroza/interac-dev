package cl.interac.security;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author colivares
 */
@Component
public class LogInManager implements AuthenticationProvider, Serializable {
    @Autowired
    private LogicaUsuario logicaUsuario;
     private Usuario usuario;
    private Rol rol;

    public Authentication authenticate(Authentication a) {
        String user = a.getName();
        String password = null;
        try {
            password = a.getCredentials().toString();
        } catch (NullPointerException e) {}

        usuario = logicaUsuario.obtenerPorUsuarioContrasenna(user, password);

        if (usuario == null)
            throw new BadCredentialsException("Usuario y/o Contraseña Inválidos");
        else
            return new UsernamePasswordAuthenticationToken(user, a.getCredentials(), getAcceso(user));
    }

    private List<GrantedAuthority> getAcceso(String u) {
        List<GrantedAuthority> listaRoles = new ArrayList<GrantedAuthority>();

        if (listaRoles.getIdRol() != null && Rol.getIdrol().equalsIgnoreCase("1")) {
            listaRoles.add(new SimpleGrantedAuthority("ADMIN"));
            listaRoles.add(new SimpleGrantedAuthority("ADMIN_MANTENEDORES"));
       }

        listaRoles.add(new SimpleGrantedAuthority("USUARIO_WEB"));
        return listaRoles;
    }

    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
