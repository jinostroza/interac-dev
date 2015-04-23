/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Campana;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edggar
 */
@Repository
public class CampanaDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Campana> obtenerTodos() {
        return em.createNamedQuery("Campana.findAll").getResultList();
    }
    public void guardar(Campana c){
       if (c.getIdCampana()== null) em.persist(c);
       else em.merge(c);
    }
    public void eliminarCampana(Campana c){
        Campana campana = em.find(Campana.class, c.getIdCampana());
        em.remove(campana);
    }

  //  public List<Campana> obtenerPorCampana(Campana campana) {
    //    return em.createNamedQuery("Campana.findByIdCampana").setParameter("Campana", campana).getResultList();
   // }
}
