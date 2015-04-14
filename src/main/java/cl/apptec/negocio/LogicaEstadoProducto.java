/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.EstadoProductoDao;
import cl.apptec.entidades.EstadoProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matias Harding
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaEstadoProducto {
    @Autowired
     private EstadoProductoDao estadoProductoDao;
     
     @Transactional(readOnly = true)
     public List<EstadoProducto> obtenerTodos() {
        return estadoProductoDao.obtenerTodos();
    }
    
      @Transactional(readOnly = false)
    public void guardarEstadoProducto(EstadoProducto ep) {
        estadoProductoDao.guardar(ep);
    }

    @Transactional(readOnly = false)
    public void eliminarEstadoProducto(EstadoProducto ep) {
        estadoProductoDao.eliminarEstadoProducto(ep);
    }
    
    @Transactional(readOnly = false)
    public boolean existeEstadoProducto(String nombreEstadoProducto) {
        return estadoProductoDao.existeEstadoProducto(nombreEstadoProducto);
    }
    
    
    
}
