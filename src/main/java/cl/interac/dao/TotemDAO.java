/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Totem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edggar
 */
@Repository
public class TotemDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Totem> obtenerTodos() {
        return em.createNamedQuery("Totem.findAll").getResultList();
    }

    public void guardar(Totem t){
       if (t.getIdtotem()== null) em.persist(t);
       else em.merge(t);
    }

    public void eliminarTotem(Totem t){
        Totem totem = em.find(Totem.class, t.getIdtotem());
        em.remove(totem);
    }

    public List<Totem> obtenerConRelacion(){
       return em.createNamedQuery("Totem.findWithRelationship").getResultList();
    }
}
