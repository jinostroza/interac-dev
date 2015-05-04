/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Categoria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edggar
 */
@Repository
public class CategoriaDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Categoria> obtenerTodos() {
        return em.createNamedQuery("Categoria.findAll").getResultList();
    }

    public void guardar(Categoria g){
       if (g.getIdcategoria()== null) em.persist(g);
       else em.merge(g);
    }

    public void eliminarCategoria(Categoria g){
        Categoria categoria = em.find(Categoria.class, g.getIdcategoria());
        em.remove(categoria);
    }
}
