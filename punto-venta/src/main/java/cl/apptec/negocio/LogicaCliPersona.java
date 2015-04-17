/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.CliPersonaDao;
import cl.apptec.entidades.CliPersona;
import cl.apptec.entidades.Proyecto;
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
public class LogicaCliPersona {
     @Autowired
     private CliPersonaDao cliPersonaDao;
     
    @Transactional(readOnly = true)
    public List<CliPersona> obtenerTodos() {
        return cliPersonaDao.obtenerTodos();
    }
    
    @Transactional(readOnly = false)
    public void guardarCliPersona(CliPersona cp){
        cliPersonaDao.guardar(cp);
    }
    @Transactional(readOnly = false)
    public void eliminarCliPersona(CliPersona cp){
        cliPersonaDao.eliminarCliPersona(cp);
    }

    @Transactional(readOnly = true)
    public CliPersona obtenerPorRut(Integer rut) {
        return cliPersonaDao.obtenerPorRut(rut);
    }
    
    @Transactional(readOnly = true)
    public List<CliPersona> obtenerPorProyecto(Proyecto proyecto) {
        return cliPersonaDao.obtenerPorProyecto(proyecto);
    }
}