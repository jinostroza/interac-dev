package cl.interac.dao;

import cl.interac.entidades.Anuncio;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
/**
 * Created by luis on 21-04-2015.
 */
@Repository

public class AnuncioDAO {


    private EntityManager em;

    public List<Anuncio> obtenerTodos() {  return em.createNamedQuery("Anuncio.findAll").getResultList();    }



    public void guardar(Anuncio n) {
        if (n.getIdAnuncio() == null) em.persist(n);
        else em.merge(n);
    }
    public void eliminarAnuncio(Anuncio n) {
     Anuncio nun = em.find(Anuncio.class, n.getIdAnuncio());
        em.remove(nun);
    }


}