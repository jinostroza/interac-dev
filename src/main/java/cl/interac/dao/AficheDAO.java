package cl.interac.dao;

import cl.interac.entidades.Afiche;
import cl.interac.entidades.Anuncio;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by luis on 27-05-2015.
 */
@Repository
public class AficheDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Afiche> obtenerTodos(){ return em.createNamedQuery("afiche.findAll").getResultList();}
    public List<Afiche> obtenerPorId(){return em.createNamedQuery("afiche.findById").getResultList();}

    public void  guardar(Afiche afiche){
        if(afiche.getIdAfiche() == null )  em.persist(afiche);
        else em.merge(afiche);

    }
    public void eliminar(Afiche afiche){
        Afiche afiches = em.find(Afiche.class,afiche.getIdAfiche());
                em.remove(afiches);

    }
}
