/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Sucursal;
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
public class SucursalDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Sucursal> obtenerTodos() {
        return em.createNamedQuery("Sucursal.findAll").getResultList();
    }
        public void guardar(Sucursal s) {
        if (s.getIdSucursal() == null) em.persist(s);
        else em.merge(s);
    }

    public void eliminarSucursal(Sucursal s) {
        
        Sucursal sucursal = em.find(Sucursal.class, s.getIdSucursal());
        em.remove(sucursal);
    }

    public Sucursal obtenerPorId(Integer idSucursal) { 
        return em.find(Sucursal.class, idSucursal); 
    }
    
    public Sucursal obtenerPorIdStock(Integer idStock) { 
        return em.find(Sucursal.class, idStock); 
    }
}
