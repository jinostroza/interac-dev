package cl.interac.security;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.presentacion.AutenticarBean;
import cl.interac.util.dto.UsuarioDto;
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



    public Authentication authenticate(Authentication a) {
        String user = a.getName();
        String password = null;
        System.err.println("LLEGO: " + user);


        try {
            password = a.getCredentials().toString();
        } catch (NullPointerException e) {
        }
        usuario = logicaUsuario.obtenerPorUsuarioContrasenna(user, password);

        if (usuario == null)
            throw new BadCredentialsException("Usuario y/o Contraseña Inválidos");
        else
            return new UsernamePasswordAuthenticationToken(user, a.getCredentials(), getAcceso(usuario));
    }

    private List<GrantedAuthority> getAcceso(Usuario usuario) {


        List<GrantedAuthority> listaRoles = new ArrayList<GrantedAuthority>();
        List<Usuario> roles = null;

        // asi debiera ser la idea, pero puede que esté toda la wea demas si es que el usuario
        // ya tiene cargado su rol al autentificarse, verificamos y el usuario de la sesión estaba sin rol
        // por ende estuvo bien irlo a buscar

        Rol r = logicaUsuario.obtenerRol(usuario);
        listaRoles.add(new SimpleGrantedAuthority(r.getNamerol().toUpperCase().replaceAll(" ", "_")));

        //listaRoles.add(new SimpleGrantedAuthority("USUARIO_WEB")); // este era a modo de ejemplo, no sé si sea válido aún

        return listaRoles;

    }

    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }


}
