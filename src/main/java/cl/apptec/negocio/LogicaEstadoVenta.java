/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.EstadoVentaDao;
import cl.apptec.entidades.EstadoVenta;
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
public class LogicaEstadoVenta {
    @Autowired
    private EstadoVentaDao estadoVentaDao;
    
    @Transactional(readOnly = true)
    public List<EstadoVenta> obtenerTodos() {
        return estadoVentaDao.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarEV(EstadoVenta e){
        estadoVentaDao.guardar(e);
    }
    @Transactional(readOnly = false)
    public void eliminarEV(EstadoVenta e){
        estadoVentaDao.eliminarEV(e);
    }
}