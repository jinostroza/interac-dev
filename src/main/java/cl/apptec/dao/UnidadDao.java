package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Unidad;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class UnidadDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Unidad> obtenerTodos() {
        return em.createNamedQuery("Unidad.findAll").getResultList();
    }
    public void guardar(Unidad u) {
        if (u.getIdUnidad() == null) em.persist(u);
        else em.merge(u);
    }

    public void eliminarUnidad(Unidad u) {
       
        Unidad unidad = em.find(Unidad.class, u.getIdUnidad());
        em.remove(unidad);
    }
    
}
