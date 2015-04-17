package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Producto;
import cl.apptec.entidades.Sucursal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class ProductoDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Producto> obtenerTodos() {
        return em.createNamedQuery("Producto.findAll").getResultList();
    }
    
    public void guardar(Producto p) {
        if (p.getIdProducto() == null) em.persist(p);
        else em.merge(p);
    }

    public void eliminarProducto(Producto p) {
        
        Producto producto = em.find(Producto.class, p.getIdProducto());
        em.remove(producto);
    }
    
    public List<Producto> obtenerProductosSucursal(Sucursal s) {
        StringBuilder nativeSQL = new StringBuilder();
        nativeSQL.append("select p.* from producto p ");
        nativeSQL.append("inner join stock s on (p.id_producto = s.id_producto and s.id_sucursal = :sucursal) ");
        nativeSQL.append("where p.activo_producto = true ");
        return em.createNativeQuery(nativeSQL.toString(), Producto.class)
                .setParameter("sucursal", s.getIdSucursal()).getResultList(); // eso seria en cuanto a implementacion ams y...
    }

    public Producto obtenerPorId(Integer id) {
        return em.find(Producto.class, id);
    }

    public Producto obtenerConRelaciones(Producto p) {
        try {
            return (Producto) em.createNamedQuery("Producto.findWithRelationship").setParameter("producto", p).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
