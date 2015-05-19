/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.CategoriaDAO;
import cl.interac.entidades.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author edggar
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaCategoria {
   @Autowired
   private CategoriaDAO categoriaDAO;
     @Transactional(readOnly = true)
    public List<Categoria> obtenerTodos() {
       return categoriaDAO.obtenerTodos();
   }
    @Transactional(readOnly = false)
   public void guardar(Categoria g){
       categoriaDAO.guardar(g);
   }
    @Transactional(readOnly = false)
    public void eliminar(Categoria g){
       categoriaDAO.eliminar(g);
   }
}
