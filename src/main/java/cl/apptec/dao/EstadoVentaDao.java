/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.EstadoVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 */
@Repository
public class EstadoVentaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<EstadoVenta> obtenerTodos() {
        return em.createNamedQuery("EstadoVenta.findAll").getResultList();
    }
    public void guardar(EstadoVenta e){
        if(e.getIdEstadoventa() == null) em.persist(e);
        else em.merge(e);
    }
    public void eliminarEV(EstadoVenta e){
        EstadoVenta estadoV = em.find(EstadoVenta.class, e.getIdEstadoventa());
        em.remove(estadoV);
    }
}
