/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.TipoEntradaSalidaDao;
import cl.apptec.entidades.TipoEntradaSalida;
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
public class LogicaTipoEntradaSalida {
    @Autowired
     private TipoEntradaSalidaDao tipoEntradaSalidaDao;
     
     @Transactional(readOnly = true)
     public List<TipoEntradaSalida> obtenerTodos() {
        return tipoEntradaSalidaDao.obtenerTodos();
    }
    
     @Transactional(readOnly = false)
    public void guardarTipoEntradaSalida(TipoEntradaSalida tes) {
        tipoEntradaSalidaDao.guardar(tes);
    }
    
    @Transactional(readOnly = false)
    public void eliminarTipoEntradaSalida(TipoEntradaSalida tes) {
        tipoEntradaSalidaDao.eliminar(tes);
    }
}
