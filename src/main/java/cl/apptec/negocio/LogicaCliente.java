/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.ClienteDAO;
import cl.apptec.entidades.Cliente;
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
public class LogicaCliente {
     @Autowired
     private ClienteDAO clienteDAO;
     
     @Transactional(readOnly = true)
     public List<Cliente> obtenerTodos() {
        return clienteDAO.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarCliente(Cliente c){
        clienteDAO.guardar(c);
    }
    @Transactional(readOnly = false)
    public void eliminarCliente(Cliente c){
        clienteDAO.eliminarCliente(c);
    }

    @Transactional(readOnly = true)
    public Cliente obtenerPorRut(Integer rut) {
        return clienteDAO.obtenerPorRut(rut);
    }
}
