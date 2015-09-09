/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Campanun;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edggar
 */
@Repository
public class CampanunDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Campanun> obtenerTodos() {
        return em.createNamedQuery("Campanun.findAll").getResultList();
    }

    public void guardar(List<Campanun> ca){
           if (ca.get(0)!= null) em.persist(ca);
       else em.merge(ca);
    }


}
