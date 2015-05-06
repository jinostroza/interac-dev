package cl.interac.security;

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

    public Authentication authenticate(Authentication a) {
        System.err.println("LLEGOS");
        String user = a.getName();
        String password = null;
        try {
            password = a.getCredentials().toString();
        } catch (NullPointerException e) {}

        // acá en vez de un integer se debiera tratar de obtener el usuario desde la BD en base
        // a los datos de la autentificacion
        // ams algo importante aparten del login manager cree un componente del tipo sesion
        // para poder guardar el usuario traido desde la BD en la sesion
        Integer u = new Integer("0");
        if (u == null)
            throw new BadCredentialsException("Usuario y/o Contraseña Inválidos");
        else
            return new UsernamePasswordAuthenticationToken(user, a.getCredentials(), getAcceso(user));
    }

    private List<GrantedAuthority> getAcceso(String u) {
        List<GrantedAuthority> listaRoles = new ArrayList<GrantedAuthority>();

        // acá en base a la BD tambien se debieran agregar los roles asociados al usuario
        // y agregarlos a la lista de permisos, si eres observador notarás que los permisos
        // que acá se definan son los que se validan en el security.xml
        listaRoles.add(new SimpleGrantedAuthority("USUARIO_WEB"));
        return listaRoles;
    }

    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
