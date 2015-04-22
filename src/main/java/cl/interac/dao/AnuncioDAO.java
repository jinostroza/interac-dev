/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Anuncio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class AnuncioDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Anuncio> obtenerTodos() {
        return em.createNamedQuery("Anuncio.findAll").getResultList();
    }
    public void guardar(Anuncio a){
        if (a.getIdAnuncio()== null) em.persist(a);
        else em.merge(a);
    }
    public void eliminarAnuncio(Anuncio a) {
        Anuncio Anuncio = em.find(Anuncio.class, a.getIdAnuncio());
        em.remove(Anuncio);
    }
    
}
