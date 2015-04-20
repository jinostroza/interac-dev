package cl.interac.negocio;

import cl.interac.Entidades.Usuario;
import cl.interac.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jorge on 15-04-15.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaUsuario {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return usuarioDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarUsuario(Usuario u) {
        usuarioDAO.guardar(u);
    }

    @Transactional(readOnly = false)
    public void eliminarUsuario(Usuario u) {
        usuarioDAO.eliminarUsuario(u);
    }

    @Transactional(readOnly = true)
    public Usuario obtenerUsuario(String username) {
        return usuarioDAO.obtenerUsuario(username);
    }

    @Transactional(readOnly = true)
    public Usuario obtenerUsuario(String username, String password) {
        return usuarioDAO.obtenerUsuario(username, password);
    }
}
