package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.EstadoProducto;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class EstadoProductoDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<EstadoProducto> obtenerTodos() {
        return em.createNamedQuery("EstadoProducto.findAll").getResultList();
    }
    
    public void guardar(EstadoProducto ep) {
        if (ep.getIdEstadoProducto() == null) em.persist(ep);
        else em.merge(ep);
    }

    public void eliminarEstadoProducto(EstadoProducto ep) {
        
        EstadoProducto estadoProducto = em.find(EstadoProducto.class, ep.getIdEstadoProducto());
        em.remove(estadoProducto);
    }
    
    public boolean existeEstadoProducto(String nombreEstadoProducto) {
        try {
            EstadoProducto ep = (EstadoProducto) em.createNamedQuery("EstadoProducto.findByNombreEstadoProducto")
                    .setParameter("nombreEstadoProducto", nombreEstadoProducto).getSingleResult();
            return ep != null;
        } catch (Exception e) {
            return false;
        }
    }
}
