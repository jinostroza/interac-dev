package cl.interac.negocio;

import cl.interac.entidades.Usuario;
import cl.interac.dao.UsuarioDAO;
import cl.interac.util.dto.UsuarioDto;
import java.util.List;


import cl.interac.util.pojo.Encriptador;
import cl.interac.util.pojo.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;

/**
 * Created by Jorge on 15-04-15.
 */
@Service
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


    @Transactional(readOnly = false)
    public void cambiarClave(String usuario, String clave) {
        usuarioDAO.cambiarClave(usuario, clave);
    }

    @Transactional(readOnly = true)
    public UsuarioDto logInExterno(String usuario, String password) {
        try {
            return usuarioDAO.obtenerUsuario(usuario,password);
        } catch (Exception e) {
            return null;
        }
    }
    @Transactional(readOnly = false)
    public void editarPerfil(String usuario,String correo, String empresa){
        usuarioDAO.editarPerfil(usuario,correo,empresa);
    }
  @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosMasRol(){
      return usuarioDAO.obtenerConrelacionMasRol();

    }



}
