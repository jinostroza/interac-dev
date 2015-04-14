/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.VentaMedioPago;
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
public class VentaMedioPagoDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<VentaMedioPago> obtenerTodos() {
        return em.createNamedQuery("VentaMedioPago.findAll").getResultList();
    }
         public void guardar(VentaMedioPago vmp) {
        if (vmp.getIdVentaMedioDePago() == null) em.persist(vmp);
        else em.merge(vmp);
    }

    public void eliminarVentaMedioPago(VentaMedioPago vmp) {
        
        VentaMedioPago ventaMedioPago = em.find(VentaMedioPago.class, vmp.getIdVentaMedioDePago());
        em.remove(ventaMedioPago);
    }
}