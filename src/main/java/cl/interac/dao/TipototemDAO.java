package cl.interac.dao;

import cl.interac.entidades.Tipototem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by luisPc on 15-08-2015.
 */
@Repository
public class TipototemDAO {


    @PersistenceContext
    private EntityManager em;

    public List<Tipototem> obtenerTodos(){return em.createNamedQuery("Tipototem.findAll").getResultList();}

    public void guardar(Tipototem t){
        if(t.getIdtipo()== null )  em.persist(t);
        else em.merge(t);
    }


    public void eliminar(Tipototem t){
        Tipototem tipototem = em.find(Tipototem.class,t.getIdtipo());
        em.remove(t);
    }
    public List<Tipototem> obtenerConRelacion(){return em.createNamedQuery("Tipototem.findWithRelationship").getResultList();}
}
