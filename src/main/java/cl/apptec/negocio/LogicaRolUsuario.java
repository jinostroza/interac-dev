/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;


import cl.apptec.dao.RolUsuarioDAO;
import cl.apptec.entidades.RolUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apptec
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaRolUsuario {
    @Autowired
     private RolUsuarioDAO rolUsuarioDAO;
     
     @Transactional(readOnly = true)
     public List<RolUsuario> obtenerTodos() {
        return rolUsuarioDAO.obtenerTodos();
    }
     
    @Transactional(readOnly = true)
    public void guardarRolUsuario(RolUsuario r) {
        rolUsuarioDAO.guardar(r);
    }
    @Transactional(readOnly = true)
    public void eliminarRolUsuario(RolUsuario r){
        rolUsuarioDAO.eliminarRolUsuario(r);
    }
}
