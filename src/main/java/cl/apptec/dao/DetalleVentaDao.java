/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.DetalleVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 */
@Repository
public class DetalleVentaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<DetalleVenta> obtenerTodos() {
        return em.createNamedQuery("DetalleVenta.findAll").getResultList();
    }
    public void guardar(DetalleVenta v){
        if(v.getIdDetalleVenta() == null) em.persist(v);
        else em.merge(v);
    }
    
}
