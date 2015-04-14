/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;


import cl.apptec.dao.ComunaDAO;
import cl.apptec.entidades.Ciudad;
import cl.apptec.entidades.Comuna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author apptec
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaComuna {
    @Autowired
    private ComunaDAO comunaDAO;

    @Transactional(readOnly = true)
    public List<Comuna> obtenerTodos() {
        return comunaDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarComuna(Comuna c) {
        comunaDAO.guardar(c);
    }

    @Transactional(readOnly = false)
    public void eliminarComuna(Comuna c) {
        comunaDAO.eliminarComuna(c);
    }

    @Transactional(readOnly = true)
    public List<Comuna> obtenerPorCiudad(Ciudad ciudad) {
        return comunaDAO.obtenerPorCiudad(ciudad);
    }

}