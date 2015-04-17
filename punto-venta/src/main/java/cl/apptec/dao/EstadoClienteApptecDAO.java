/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.EstadoClienteApptec;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class EstadoClienteApptecDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<EstadoClienteApptec> obtenerTodos() {
        return em.createNamedQuery("EstadoClienteApptec.findAll").getResultList();
    }
    
    public void guardar(EstadoClienteApptec e){
        if (e.getIdEstadoClienteApptec()== null) em.persist(e);
        else em.merge(e);
    }
    public void eliminarClienteApptec(EstadoClienteApptec c){
        EstadoClienteApptec eCA = em.find(EstadoClienteApptec.class, c.getIdEstadoClienteApptec());
        em.remove(eCA);
    }
}
