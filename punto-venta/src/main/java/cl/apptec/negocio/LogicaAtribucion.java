/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.AtribucionDAO;
import cl.apptec.entidades.Atribucion;
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
public class LogicaAtribucion {
    @Autowired
     private AtribucionDAO atribucionDAO;
     
     @Transactional(readOnly = true)
     public List<Atribucion> obtenerTodos() {
        return atribucionDAO.obtenerTodos();
    }
     @Transactional(readOnly = false)
    public void guardarAtribucion(Atribucion a){
        atribucionDAO.guardar(a);
    }
    @Transactional(readOnly = false)
    public void eliminarAtribucion(Atribucion a){
        atribucionDAO.eliminarAtribucion(a);
    }
}
