/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.MedioPagoDao;
import cl.apptec.entidades.MedioPago;
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
public class LogicaMedioPago {
    @Autowired
    private MedioPagoDao medioPagoDao;
    
    @Transactional(readOnly = true)
    public List<MedioPago> obtenerTodos() {
        return medioPagoDao.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardar(MedioPago m){
        medioPagoDao.guardar(m);
    }
    @Transactional(readOnly = false)
    public void eliminarMP(MedioPago m){
        medioPagoDao.eliminarMP(m);
    }
}