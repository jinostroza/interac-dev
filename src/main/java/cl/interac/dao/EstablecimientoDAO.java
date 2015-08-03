package cl.interac.dao;

import cl.interac.entidades.Establecimiento;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pedro Pablo on 20-05-2015.
 */
@Repository
public class EstablecimientoDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Establecimiento> ObtenerTodos(){return em.createNamedQuery("establecimiento.findAll").getResultList();}

    public void guardar(Establecimiento es){
        if (es.getIdEstablecimiento() == null) em.persist(es);
        else em.merge(es);
    }

    public void eliminar(Establecimiento es){
        Establecimiento establecimiento = em.find(Establecimiento.class, es.getIdEstablecimiento());
        em.remove(establecimiento);
    }


}