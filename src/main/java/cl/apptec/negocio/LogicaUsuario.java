/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.UsuarioDAO;
import cl.apptec.entidades.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author apptec
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
