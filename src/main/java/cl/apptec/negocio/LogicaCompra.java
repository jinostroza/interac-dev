/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.CompraDao;
import cl.apptec.entidades.Compra;

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
public class LogicaCompra {
    @Autowired
    private CompraDao compraDao;

    @Transactional(readOnly = true)
    public List<Compra> obtenerTodos() {
        return compraDao.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarCompra(Compra u) {
        compraDao.guardar(u);
    }

    @Transactional(readOnly = false)
    public void eliminarCompra(Compra u) {
        compraDao.eliminarCompra(u);
    }
/*
    @Transactional(readOnly = true)
    public Compra obtenerCompra(Compra c) {
        return compraDao.obtenerCompra(c);
    }

    @Transactional(readOnly = true)
    public Compra obtenerCompra(String username, String password) {
        return compraDao.obtenerCompra(username, password);
    }
*/
}