/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.CliEmpresaDao;
import cl.apptec.entidades.CliEmpresa;
import java.util.List;

import cl.apptec.entidades.Proyecto;
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
public class LogicaCliEmpresa {
     @Autowired
     private CliEmpresaDao cliEmpresaDao;
     
     @Transactional(readOnly = true)
     public List<CliEmpresa> obtenerTodos() {
        return cliEmpresaDao.obtenerTodos();
    }
     
    @Transactional(readOnly = true)
    public List<CliEmpresa> obtenerPorProyecto(Proyecto proyecto) {
        return cliEmpresaDao.obtenerPorProyecto(proyecto);
    }
     
    @Transactional(readOnly = false)
    public void guardarCliEmpresa(CliEmpresa ce){
        cliEmpresaDao.guardar(ce);
    }
    @Transactional(readOnly = false)
    public void eliminarCliEmpresa(CliEmpresa ce){
        cliEmpresaDao.eliminarCliEmpresa(ce);
    }

    @Transactional(readOnly = true)
    public CliEmpresa obtenerPorRut(Integer rut) {
        return cliEmpresaDao.obtenerPorRut(rut);
    }
}