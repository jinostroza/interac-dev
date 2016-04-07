package cl.interac.dao;

import cl.interac.entidades.Taller;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by luis on 14-05-2015.
 */

@Repository
public class TallerDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Taller> obtenerTodos(){return em.createNamedQuery("Taller.findAll").getResultList();}

    public void guardar(Taller t){
        if(t.getIdtaller()== null )  em.persist(t);
        else em.merge(t);
    }


    public void eliminar(Taller t){
        Taller taller = em.find(Taller.class,t.getIdtaller());
        em.remove(taller);
    }

}
