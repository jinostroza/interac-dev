/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.CategoriaVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 */
@Repository
public class CategoriaVentaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<CategoriaVenta> obtenerTodos() {
        return em.createNamedQuery("CategoriaVenta.findAll").getResultList();
    }
    public void guardar (CategoriaVenta c){
        if(c.getIdCategoriaventa() == null) em.persist(c);
        else em.merge(c);
    }
    public void eliminarCV(CategoriaVenta c){
        CategoriaVenta cVenta = em.find(CategoriaVenta.class, c.getIdCategoriaventa());
        em.remove(cVenta);
    }
}
