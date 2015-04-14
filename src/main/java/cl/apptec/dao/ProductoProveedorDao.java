package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.ProductoProveedor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class ProductoProveedorDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<ProductoProveedor> obtenerTodos() {
        return em.createNamedQuery("ProductoProveedor.findAll").getResultList();
    }
    public void guardar(ProductoProveedor p) {
        if (p.getIdProductoProveedor() == null) em.persist(p);
        else em.merge(p);
    }

    public void eliminarProductoProveedor(ProductoProveedor p) {
        
        ProductoProveedor productoProveedor = em.find(ProductoProveedor.class, p.getIdProductoProveedor());
        em.remove(productoProveedor);
    }
}
