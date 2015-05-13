package cl.interac.negocio;

import cl.interac.entidades.Usuario;
import cl.interac.dao.UsuarioDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jorge on 15-04-15.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaUsuario {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Transactional(readOnly = false)
    public void guardar(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return usuarioDAO.obtenerTodos();
    }

    @Transactional(readOnly = true)
    public Usuario obtenerPorUsuarioContrasenna(String user, String password) {
        return usuarioDAO.obtenerPorUsuarioContrasenna(user, password);
    }

    @Transactional(readOnly = true)
    public Usuario obtener(String username) {
        return usuarioDAO.obtenerPorUsuario(username);
    }
}
