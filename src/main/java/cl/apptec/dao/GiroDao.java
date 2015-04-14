/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Giro;
import cl.apptec.entidades.Pais;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias harding
 */
@Repository
public class GiroDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Giro> obtenerTodos() {
        return em.createNamedQuery("Giro.findAll").getResultList();
    }
    public void guardar(Giro g){
        if (g.getIdGiro()== null) em.persist(g);
        else em.merge(g);
    }
    public void eliminarGiro(Giro g){
        Giro giro = em.find(Giro.class, g.getIdGiro());
        em.remove(giro);
    }
    
    public boolean existeGiro(String nombreGiro) {
        try {
            Giro p = (Giro) em.createNamedQuery("Giro.findByNombreGiro")
                    .setParameter("nombreGiro", nombreGiro).getSingleResult();
            return p != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<Giro> obtenerPorPais(Pais pais) {
        return em.createNamedQuery("Giro.findByPais").setParameter("pais", pais).getResultList();
    }
    
}
