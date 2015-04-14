/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Venta;
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
public class VentaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Venta> obtenerTodos() {
        return em.createNamedQuery("Venta.findAll").getResultList();
    }
        public void guardar(Venta v) {
        if (v.getIdVenta() == null) em.persist(v);
        else em.merge(v);
    }

    public void eliminarVenta(Venta v) {
        
        Venta venta = em.find(Venta.class, v.getIdVenta());
        em.remove(venta);
    }
}
