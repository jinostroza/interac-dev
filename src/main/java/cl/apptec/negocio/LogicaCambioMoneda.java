/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.CambioMonedaDao;
import cl.apptec.entidades.CambioMoneda;
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
public class LogicaCambioMoneda {
    @Autowired
    private CambioMonedaDao cambioMonedaDao;
    
    @Transactional(readOnly = true)
    public List<CambioMoneda> obtenerTodos() {
        return cambioMonedaDao.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarCambioM(CambioMoneda m){
        cambioMonedaDao.guardar(m);
    }
    @Transactional(readOnly = false)
    public void eliminarCambioM(CambioMoneda m){
        cambioMonedaDao.eliminarCambioMoneda(m);
    }
    
}