package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.TipoEntradaSalida;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class TipoEntradaSalidaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<TipoEntradaSalida> obtenerTodos() {
        return em.createNamedQuery("TipoEntradaSalida.findAll").getResultList();
    }
    
    public void guardar(TipoEntradaSalida tes) {
        if (tes.getIdTipoEntradaSalida() == null) em.persist(tes);
        else em.merge(tes);
    }
    
    public void eliminar(TipoEntradaSalida tes) {
       
        TipoEntradaSalida tipoes = em.find(TipoEntradaSalida.class, tes.getIdTipoEntradaSalida());
        em.remove(tipoes);
    }
}
