/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Moneda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 */
@Repository
public class MonedaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Moneda> obtenerTodos() {
        return em.createNamedQuery("Moneda.findAll").getResultList();
    }
    public void guardar(Moneda m){
        if (m.getIdMoneda() == null ) em.persist(m);
        else em.merge(m);
    }
    public void eliminarMoneda(Moneda m){
        Moneda moneda = em.find(Moneda.class, m.getIdMoneda());
        em.remove(moneda);
    }
}