package cl.apptec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.PlanCliente;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 *
 */
@Repository
public class PlanClienteDao {

    @PersistenceContext
    private EntityManager em;

    public List<PlanCliente> obtenerTodos() {
        return em.createNamedQuery("PlanCliente.findAll").getResultList();
    }

    public void guardar(PlanCliente pc) {
        if (pc.getIdPlanCliente() == null) {
            em.persist(pc);
        } else {
            em.merge(pc);
        }
    }

    public void eliminarPlanCliente(PlanCliente p) {
        PlanCliente planCliente = em.find(PlanCliente.class, p.getIdPlanCliente());
        em.remove(planCliente);
    }

    public boolean existePlanCliente(String nombrePlanCliente) {
        try {
            PlanCliente pc = (PlanCliente) em.createNamedQuery("PlanCliente.findByNombrePlanCliente")
                    .setParameter("nombrePlanCliente", nombrePlanCliente).getSingleResult();
            return pc != null;
        } catch (Exception e) {
            return false;
        }
    }
}
