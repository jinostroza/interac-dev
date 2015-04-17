package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.EntradaSalida;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class EntradaSalidaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<EntradaSalida> obtenerTodos() {
        return em.createNamedQuery("EntradaSalida.findAll").getResultList();
    }
    
    public void guardar(EntradaSalida es) {
        if (es.getIdEntradaSalida()== null) em.persist(es);
        else em.merge(es);
    }
    
     public void eliminarEntradaSalida(EntradaSalida es) {
        EntradaSalida entradaSalida = em.find(EntradaSalida.class, es.getIdEntradaSalida());
        em.remove(entradaSalida);
    }
    
}
