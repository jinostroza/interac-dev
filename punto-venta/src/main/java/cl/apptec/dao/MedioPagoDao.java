/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.MedioPago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 */
@Repository
public class MedioPagoDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<MedioPago> obtenerTodos() {
        return em.createNamedQuery("MedioPago.findAll").getResultList();
    }
    public void guardar(MedioPago m){
        if (m.getIdMediopago() == null) em.persist(m);
        else em.merge(m);
    }
    public void eliminarMP(MedioPago m){
        MedioPago medioP = em.find(MedioPago.class, m.getIdMediopago());
        em.remove(medioP);
    }
}