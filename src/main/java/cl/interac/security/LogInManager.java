package cl.interac.security;

import cl.interac.logica.usuarios.LogicaUsuario;
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
        Integer u = new Integer("0");
        System.err.println("u: "+u!=null);
        if (u == null)
            throw new BadCredentialsException("Usuario y/o Contraseña Inválidos");
        else
            return new UsernamePasswordAuthenticationToken(user, a.getCredentials(), getAcceso(user));
    }

    private List<GrantedAuthority> getAcceso(String u) {
        List<GrantedAuthority> listaRoles = new ArrayList<GrantedAuthority>();
        listaRoles.add(new SimpleGrantedAuthority("USUARIO_WEB"));
        return listaRoles;
    }

    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
