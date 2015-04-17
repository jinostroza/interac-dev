/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.TipoFono;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class TipoFonoDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<TipoFono> obtenerTodos() {
        return em.createNamedQuery("TipoFono.findAll").getResultList();
    }
    public void guardar( TipoFono f){
        if (f.getIdTipoFono()== null) em.persist(f);
        else em.merge(f);
    }
    public void eliminarTipoFono(TipoFono f){
        TipoFono tipoFono = em.find(TipoFono.class, f.getIdTipoFono());
        em.remove(tipoFono);
    }
    
}
