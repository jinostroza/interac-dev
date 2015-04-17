/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;


import cl.apptec.dao.RolAtribucionDAO;
import cl.apptec.entidades.RolAtribucion;
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
public class LogicaRolAtribucion {
    @Autowired
     private RolAtribucionDAO rolAtribucionDAO;
     
     @Transactional(readOnly = true)
     public List<RolAtribucion> obtenerTodos() {
        return rolAtribucionDAO.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarRolAtribucion(RolAtribucion r) {
        rolAtribucionDAO.guardar(r);
    }
    
}
