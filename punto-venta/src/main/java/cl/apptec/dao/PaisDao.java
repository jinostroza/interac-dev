package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Pais;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 *
 */
@Repository
public class PaisDao {

    @PersistenceContext
    private EntityManager em;

    public List<Pais> obtenerTodos() {
        return em.createNamedQuery("Pais.findAll").getResultList();
    }

    public void guardar(Pais p) {
        if (p.getIdPais() == null) {
            em.persist(p);
        } else {
            em.merge(p);
        }
    }

    public void eliminarPais(Pais p) {
        Pais pais = em.find(Pais.class, p.getIdPais());
        em.remove(pais);
    }

    public boolean existePais(String nombrePais) {
        try {
            Pais p = (Pais) em.createNamedQuery("Pais.findByNombrePais")
                    .setParameter("nombrePais", nombrePais).getSingleResult();
            return p != null;
        } catch (Exception e) {
            return false;
        }
    }
}
