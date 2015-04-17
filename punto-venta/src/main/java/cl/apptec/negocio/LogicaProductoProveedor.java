/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.ProductoProveedorDao;
import cl.apptec.entidades.ProductoProveedor;
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
public class LogicaProductoProveedor {
    @Autowired
     private ProductoProveedorDao productoProveedorDao;
     
     @Transactional(readOnly = true)
     public List<ProductoProveedor> obtenerTodos() {
        return productoProveedorDao.obtenerTodos();
    }
     @Transactional(readOnly = false)
    public void guardarProductoProveedor(ProductoProveedor pp) {
        productoProveedorDao.guardar(pp);
    }

    @Transactional(readOnly = false)
    public void eliminarProductoProveedor(ProductoProveedor pp) {
        productoProveedorDao.eliminarProductoProveedor(pp);
    }
}
