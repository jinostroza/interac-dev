/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.DetalleVentaDao;
import cl.apptec.entidades.DetalleVenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author secabezas
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaDetalleVenta {
    @Autowired
    private DetalleVentaDao detalleVentaDao;
    
    @Transactional(readOnly = true)
    public List<DetalleVenta> obtenerTodos() {
        return detalleVentaDao.obtenerTodos();
    }
}