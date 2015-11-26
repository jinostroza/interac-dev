/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.TotemDAO;
import cl.interac.entidades.Campana;
import cl.interac.entidades.Totem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author edggar
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaTotem {
    @Autowired
    private TotemDAO totemDAO;

    @Transactional(readOnly = true)
    public List<Totem> obtenerTodos() {
        return totemDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardar(Totem t) {
        totemDAO.guardar(t);
    }

    @Transactional(readOnly = false)
    public void eliminarTotem(Totem t) {
        totemDAO.eliminarTotem(t);
    }

    @Transactional(readOnly = true)
    public List<Totem> obtenerConRelacion() {
        return totemDAO.obtenerConRelacion();
    }

    @Transactional(readOnly = true)
    public List<Totem> obtenerPorEstado() {
        return totemDAO.obtenerPorEstado();
    }


    @Transactional(readOnly = true)
    public List<Totem> obtenerPorUsuario(String username){
        return totemDAO.obtenerConUsuario(username);
    }

    @Transactional(readOnly = true)
    public List<Totem> obtenerDeCampana(String user){return totemDAO.obtenerConCampana(user);}


    @Transactional(readOnly = true)
    public long obtenerPorNumero(Integer establecimiento){
        return totemDAO.obtenerNumero(establecimiento);
    }
    @Transactional(readOnly = true)
    public  List<Totem> obtenerPorestablecimiento(Integer establecimiento){
        return totemDAO.obtenerPorEstablecimiento(establecimiento);
    }


}


