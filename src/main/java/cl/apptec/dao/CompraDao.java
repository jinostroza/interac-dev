package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Compra;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 *
 */
@Repository
public class CompraDao {

    @PersistenceContext
    private EntityManager em;

    public List<Compra> obtenerTodos() {
        return em.createNamedQuery("Compra.findAll").getResultList();
    }

    public void guardar(Compra c) {
        if (c.getId()== null) {
            em.persist(c);
        } else {
            em.merge(c);
        }
    }

    public void eliminarCompra(Compra c) {
        Compra pais = em.find(Compra.class, c.getId());
        em.remove(pais);
    }

    
    public boolean existeCompra(Compra c) {
        /* try {
            Compra p = (Compra) em.createNamedQuery("Compra.findByNombreCompra")
                    .setParameter("nombreCompra", nombreCompra).getSingleResult();
            return p != null;
        } catch (Exception e) {
            return false;
        } */
        return true;
    }
}
