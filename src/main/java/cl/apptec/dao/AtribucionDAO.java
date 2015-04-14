/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Atribucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class AtribucionDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Atribucion> obtenerTodos() {
        return em.createNamedQuery("Atribucion.findAll").getResultList();
    }
    public void guardar(Atribucion a){
        if (a.getIdAtribucion()== null) em.persist(a);
        else em.merge(a);
    }
    public void eliminarAtribucion(Atribucion a){
        Atribucion atribucion = em.find(Atribucion.class,a.getIdAtribucion());
        em.remove(atribucion);
    }
    
}
