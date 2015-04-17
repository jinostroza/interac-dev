/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.UnidadDao;
import cl.apptec.entidades.Unidad;
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
public class LogicaUnidad {
    @Autowired
    private UnidadDao unidadDao;
     
    @Transactional(readOnly = true)
    public List<Unidad> obtenerTodos() {
        return unidadDao.obtenerTodos();
    }
    
    @Transactional(readOnly = false)
    public void guardarUnidad(Unidad u) {
        unidadDao.guardar(u);
    }

    @Transactional(readOnly = false)
    public void eliminarUnidad(Unidad u) {
        unidadDao.eliminarUnidad(u);
    }    
}
