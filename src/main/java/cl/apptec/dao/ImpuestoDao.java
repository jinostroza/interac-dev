/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Impuesto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 * @modificado Matias Harding
 */
@Repository
public class ImpuestoDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Impuesto> obtenerTodos() {
        return em.createNamedQuery("Impuesto.findAll").getResultList();
    }
     public void guardar(Impuesto im) {
        if (im.getIdImpuesto() == null) em.persist(im);
        else em.merge(im);
    }

    public void eliminarImpuesto(Impuesto im) {
        
        Impuesto impuesto = em.find(Impuesto.class, im.getIdImpuesto());
        em.remove(impuesto);
    }
}