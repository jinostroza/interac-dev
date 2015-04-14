/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.ClienteApptecDAO;
import cl.apptec.entidades.ClienteApptec;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Yesenia Doria L.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaClienteApptec {
    
     @Autowired
     private ClienteApptecDAO clienteApptecDAO;
     
     @Transactional(readOnly = true)
     public List<ClienteApptec> obtenerTodos() {
        return clienteApptecDAO.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarClienteApptec(ClienteApptec c) {
        clienteApptecDAO.guardar(c);
    }
    @Transactional(readOnly = false)
    public void eliminarCA(ClienteApptec c){
        clienteApptecDAO.eliminarCA(c);
    }
}
