/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.EntradaSalidaDao;
import cl.apptec.entidades.EntradaSalida;
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
public class LogicaEntradaSalida {
    @Autowired
     private EntradaSalidaDao entradaSalidaDao;
     
     @Transactional(readOnly = true)
     public List<EntradaSalida> obtenerTodos() {
        return entradaSalidaDao.obtenerTodos();
    }
    
     @Transactional(readOnly = false)
    public void guardarEntradaSalida(EntradaSalida es) {
        entradaSalidaDao.guardar(es);
    }

    @Transactional(readOnly = false)
    public void eliminarEntradaSalida(EntradaSalida es) {
        entradaSalidaDao.eliminarEntradaSalida(es);
    }
    
}
