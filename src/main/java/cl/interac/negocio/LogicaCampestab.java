/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.CampestabDAO;
import cl.interac.entidades.Campestab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaCampestab {

    @Autowired
    private CampestabDAO campestabDAO;

    @Transactional(readOnly = true)
    public List<Campestab> obtenerTodos() {
        return campestabDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardar(Campestab campestab) {
        campestabDAO.guardar(campestab);
    }

    @Transactional(readOnly = false)
    public void eliminar(Campestab campestab) {
        campestabDAO.eliminar(campestab);
    }

    @Transactional(readOnly = true)
    public List<Campestab> obtenerPorEstado(String user) {
        return campestabDAO.obtenerPorEstado(user);
    }
    @Transactional(readOnly = true)
    public List<Campestab> obtenerAprobado(String user) {
        return campestabDAO.obtenerAprobados(user);
    }
    public List<Campestab> obtenerEstados(String user) {
        return campestabDAO.obtenerEstado(user);
    }
    @Transactional(readOnly = true)
    public long obtenerPorNumero(String user){
        return campestabDAO.obtenerNumeroNuevas(user);
    }


}