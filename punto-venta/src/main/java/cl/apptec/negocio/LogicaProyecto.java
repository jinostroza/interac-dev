/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.ProyectoDao;
import cl.apptec.entidades.Proyecto;
import cl.apptec.entidades.Pais;

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
public class LogicaProyecto {
    @Autowired
    private ProyectoDao proyectoDao;
    
    @Transactional(readOnly = true)
    public List<Proyecto> obtenerTodos() {
        return proyectoDao.obtenerTodos();
    }
    
    @Transactional(readOnly = false)
    public void guardarProyecto(Proyecto p) {
        proyectoDao.guardar(p);
    }

    @Transactional(readOnly = false)
    public void eliminarProyecto(Proyecto p) {
        proyectoDao.eliminarProyecto(p);
    }

    @Transactional(readOnly = true)
    public boolean existeProyecto(String nombreProyecto) {
        return proyectoDao.existeProyecto(nombreProyecto); // data access object
    }
    
    @Transactional(readOnly = true)
    public List<Proyecto> obtenerPorPais(Pais pais) {
        return proyectoDao.obtenerPorPais(pais);
    }
}
