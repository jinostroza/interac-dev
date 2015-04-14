/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.CiudadDAO;
import cl.apptec.entidades.Ciudad;

import java.util.List;

import cl.apptec.entidades.Comuna;
import cl.apptec.entidades.Pais;
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
public class LogicaCiudad {
    @Autowired
    private CiudadDAO ciudadDAO;

    @Transactional(readOnly = true)
    public List<Ciudad> obtenerTodas() {
        return ciudadDAO.obtenerTodas();
    }

    @Transactional(readOnly = false)
    public void guardarCiudad(Ciudad c) {
        ciudadDAO.guardar(c);
    }

    @Transactional(readOnly = false)
    public void eliminarCiudad(Ciudad c) {
        ciudadDAO.eliminarCiudad(c);
    }

    @Transactional(readOnly = true)
    public List<Comuna> obtenerComunas(Ciudad ciudad) {
        return ciudadDAO.obtenerComunas(ciudad);
    }

    @Transactional(readOnly = true)
    public List<Ciudad> obtenerPorPais(Pais pais) {
        return ciudadDAO.obtenerPorPais(pais);
    }
}
