/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.AnuncioDAO;
import cl.interac.entidades.Anuncio;

import java.util.List;


import cl.interac.entidades.Campana;
import cl.interac.entidades.Categoria;
import cl.interac.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author edggar
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaAnuncio {
    @Autowired
    private AnuncioDAO anuncioDAO;

    @Transactional(readOnly = true)
    public List<Anuncio> obtenerTodos() {
        return anuncioDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardar(Anuncio anuncio) {
        anuncioDAO.guardar(anuncio);
    }

    @Transactional(readOnly = false)
    public void eliminarAnuncio(Anuncio anuncio) {
        anuncioDAO.eliminarAnuncio(anuncio);
    }

    @Transactional(readOnly = true)
    public Anuncio obtenerPorId(Integer id) {
        return anuncioDAO.obtenerPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Anuncio> obtenerConRelaciones() {
        return anuncioDAO.obtenerConRelaciones();
    }
}
