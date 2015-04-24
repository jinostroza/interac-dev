/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.AnuncioDAO;
import cl.interac.entidades.Anuncio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edggar
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaAnuncio {
  /* @Autowired
     private AnuncioDAO anuncioDAO;

     @Transactional(readOnly = true)
     public List<Anuncio> obtenerTodos() {
        return anuncioDAO.obtenerTodos();
    }
    @Transactional(readOnly = false)
   public void guardarAnuncio(Anuncio a){
        anuncioDAO.guardar(a);
    }
    @Transactional(readOnly = false)
   public void eliminarAnuncio(Anuncio a){
        anuncioDAO.eliminarAnuncio(a);
    }*/
}
