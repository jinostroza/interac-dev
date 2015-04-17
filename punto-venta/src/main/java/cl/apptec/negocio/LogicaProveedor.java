/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.ProveedorDao;
import cl.apptec.entidades.Proveedor;
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
public class LogicaProveedor {
    @Autowired
     private ProveedorDao proveedorDao;
     
     @Transactional(readOnly = true)
     public List<Proveedor> obtenerTodos() {
        return proveedorDao.obtenerTodos();
    }
    
     @Transactional(readOnly = false)
    public void guardarProveedor(Proveedor p) {
        proveedorDao.guardar(p);
    }

    @Transactional(readOnly = false)
    public void eliminarProveedor(Proveedor p) {
        proveedorDao.eliminarProveedor(p);
    }
}
