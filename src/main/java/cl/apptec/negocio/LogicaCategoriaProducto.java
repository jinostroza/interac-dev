/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.CategoriaProductoDao;
import cl.apptec.entidades.CategoriaProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Matias Harding
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaCategoriaProducto {
    @Autowired
    private CategoriaProductoDao categoriaProductoDao;

    @Transactional(readOnly = true)
    public List<CategoriaProducto> obtenerTodas() {
        return categoriaProductoDao.obtenerTodas();
    }

    @Transactional(readOnly = false)
    public void guardarCategoriaProducto(CategoriaProducto cp) {
        categoriaProductoDao.guardar(cp);
    }

    @Transactional(readOnly = false)
    public void eliminarCategoriaProducto(CategoriaProducto cp) {
        categoriaProductoDao.eliminarCategoriaProducto(cp);
    }

    @Transactional(readOnly = true)
    public boolean existePorNombre(String categoriaProducto) {
        return categoriaProductoDao.existePorNombre(categoriaProducto);
    }

    @Transactional(readOnly = true)
    public List<CategoriaProducto> obtenerCategoriasPadres() {
        return categoriaProductoDao.obtenerCategoriasPadres();
    }

    @Transactional(readOnly = true)
    public CategoriaProducto obtenerPorId(Integer idCategoriaProducto) {
        return categoriaProductoDao.obtenerPorId(idCategoriaProducto);
    }

    @Transactional(readOnly = true)
    public List<CategoriaProducto> obtenerPorPadre(CategoriaProducto categoria) {
        return categoriaProductoDao.obtenerPorPadre(categoria);
    }

    @Transactional(readOnly = true)
    public CategoriaProducto obtenerPadre(CategoriaProducto categoria) {
        return categoriaProductoDao.obtenerPadre(categoria);
    }
}
