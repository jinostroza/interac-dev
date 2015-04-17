package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Proveedor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class ProveedorDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Proveedor> obtenerTodos() {
        return em.createNamedQuery("Proveedor.findAll").getResultList();
    }
    public void guardar(Proveedor p) {
        if (p.getIdProveedor() == null) em.persist(p);
        else em.merge(p);
    }

    public void eliminarProveedor(Proveedor p) {
        
        Proveedor proveedor = em.find(Proveedor.class, p.getIdProveedor());
        em.remove(proveedor);
    }
}
