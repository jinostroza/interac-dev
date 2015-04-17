/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.RolAtribucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class RolAtribucionDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<RolAtribucion> obtenerTodos() {
        return em.createNamedQuery("RolAtribucion.findAll").getResultList();
    }

    public void guardar(RolAtribucion r) {
        if (r.getIdAtribucion()== null) em.persist(r);
        else em.merge(r);
    }
    
    
}
