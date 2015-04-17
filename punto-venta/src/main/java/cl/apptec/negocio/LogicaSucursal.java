/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.SucursalDao;
import cl.apptec.entidades.Sucursal;
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
public class LogicaSucursal {
    @Autowired
    private SucursalDao sucursalDao;
    
    @Transactional(readOnly = true)
    public List<Sucursal> obtenerTodos() {
        return sucursalDao.obtenerTodos();
    }
    
    @Transactional(readOnly = false)
    public void guardarSucursal(Sucursal s) {
        sucursalDao.guardar(s);
    }

    @Transactional(readOnly = false)
    public void eliminarSucursal(Sucursal s) {
        sucursalDao.eliminarSucursal(s);
    }

    public Sucursal obtenerPorId(Integer idSucursal) { 
        return sucursalDao.obtenerPorId(idSucursal);
    }
    
    public Sucursal obtenerPorIdStock(Integer idStock) { 
        return sucursalDao.obtenerPorIdStock(idStock);
    }
}