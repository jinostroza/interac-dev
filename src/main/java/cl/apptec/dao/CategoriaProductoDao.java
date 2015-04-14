package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.CategoriaProducto;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class CategoriaProductoDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<CategoriaProducto> obtenerTodas() {
        return em.createNamedQuery("CategoriaProducto.findAll").getResultList();
    }
    
    public void guardar(CategoriaProducto cp) {
        if (cp.getIdCategoriaProducto() == null) em.persist(cp);
        else em.merge(cp);
    }

    public void eliminarCategoriaProducto(CategoriaProducto cp) {
        
        CategoriaProducto categoriaProducto = em.find(CategoriaProducto.class, cp.getIdCategoriaProducto());
        em.remove(categoriaProducto);
    }

    public boolean existePorNombre(String categoriaProducto) {
        try {
            CategoriaProducto c = (CategoriaProducto) em.createNamedQuery("CategoriaProducto.findByNombreCategoriaProducto")
                    .setParameter("nombreCategoriaProducto", categoriaProducto).getSingleResult();
            return c != null ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CategoriaProducto> obtenerCategoriasPadres() {
        return em.createNamedQuery("CategoriaProducto.findPadres").getResultList();
    }

    public CategoriaProducto obtenerPorId(Integer idCategoriaProducto) {
        return em.find(CategoriaProducto.class, idCategoriaProducto);
    }

    public List<CategoriaProducto> obtenerPorPadre(CategoriaProducto categoria) {
        return em.createNamedQuery("CategoriaProducto.findByPadre")
                .setParameter("padre", categoria).getResultList();
    }

    public CategoriaProducto obtenerPadre(CategoriaProducto categoria) {
        try {
            return (CategoriaProducto) em.createNamedQuery("CategoriaProducto.findPadreCategoria")
                    .setParameter("hijo", categoria).getSingleResult();
        } catch (Exception e ) { return null; }
    }
}
