/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.MonedaDao;
import cl.apptec.entidades.Moneda;
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
public class LogicaMoneda {
    @Autowired
    private MonedaDao monedaDao;
    
    @Transactional(readOnly = true)
    public List<Moneda> obtenerTodos() {
        return monedaDao.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarMoneda(Moneda m){
        monedaDao.guardar(m);
    }
    @Transactional(readOnly = false)
    public void eliminarMoneda(Moneda m){
        monedaDao.eliminarMoneda(m);
    }
}