/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.TipoFonoDAO;
import cl.apptec.entidades.TipoFono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 * @author Yesenia Doria L.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaTipoFono {
    @Autowired
    private TipoFonoDAO tipoFonoDAO;
    @Transactional(readOnly = true)
    public List<TipoFono> obtenerTodos() {
        return tipoFonoDAO.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarTipoFono(TipoFono f){
        tipoFonoDAO.guardar(f);
    }
    @Transactional(readOnly = false)
    public void eliminarTipoFono(TipoFono f){
        tipoFonoDAO.eliminarTipoFono(f);
    }
    
}
