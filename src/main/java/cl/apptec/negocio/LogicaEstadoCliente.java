/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.EstadoClienteDAO;
import cl.apptec.entidades.EstadoCliente;
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
public class LogicaEstadoCliente {
     @Autowired
     private EstadoClienteDAO estadoClienteDAO;
     
     @Transactional(readOnly = true)
     public List<EstadoCliente> obtenerTodos() {
        return estadoClienteDAO.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarEstadCliente(EstadoCliente e){
        estadoClienteDAO.guardar(e);
    }
    @Transactional(readOnly = false)
    public void eliminarEstadoCliente(EstadoCliente e){
        estadoClienteDAO.eliminarEstadoCliente(e);
    }

    
    
    
}
