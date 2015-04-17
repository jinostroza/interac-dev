/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.VentaMedioPagoDao;
import cl.apptec.entidades.VentaMedioPago;
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
public class LogicaVentaMedioPago {
    @Autowired
    private VentaMedioPagoDao ventaMedioPagoDao;
    
    @Transactional(readOnly = true)
    public List<VentaMedioPago> obtenerTodos() {
        return ventaMedioPagoDao.obtenerTodos();
    }
         @Transactional(readOnly = false)
    public void guardarVentaMedioPago(VentaMedioPago vmp) {
        ventaMedioPagoDao.guardar(vmp);
    }

    @Transactional(readOnly = false)
    public void eliminarVentaMedioPago(VentaMedioPago vmp) {
        ventaMedioPagoDao.eliminarVentaMedioPago(vmp);
    }
}