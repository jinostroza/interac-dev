package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Imagen;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
// los dao siempre seran repositorios de informacion, en particular del contexto
// de persistencia (BD)
@Repository
public class ImagenDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Imagen> obtenerTodos() {
        return em.createNamedQuery("Imagen.findAll").getResultList();
    }
    
    public void guardar(Imagen im) {
        if (im.getIdImagen() == null) em.persist(im);
        else em.merge(im);
    }

    public void eliminarImagen(Imagen im) {
        
        Imagen imagen = em.find(Imagen.class, im.getIdImagen());
        em.remove(imagen);
    }
}
