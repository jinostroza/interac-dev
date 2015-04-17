/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Ciudad;
import cl.apptec.entidades.Comuna;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class ComunaDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Comuna> obtenerTodos() {
        return em.createNamedQuery("Comuna.findAll").getResultList();
    }
    public void guardar(Comuna c){
       if (c.getIdComuna()== null) em.persist(c);
       else em.merge(c);
    }
    public void eliminarComuna(Comuna c){
        Comuna comuna = em.find(Comuna.class, c.getIdComuna());
        em.remove(comuna);
    }

    public List<Comuna> obtenerPorCiudad(Ciudad ciudad) {
        return em.createNamedQuery("Comuna.findByCiudad").setParameter("ciudad", ciudad).getResultList();
    }
}
