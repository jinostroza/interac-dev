package cl.interac.dao;

import cl.interac.entidades.Anuncio;
import cl.interac.entidades.Campana;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by luis on 21-04-2015.
 */
@Repository
public class AnuncioDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Anuncio> obtenerTodos() {
        return em.createNamedQuery("Anuncio.findAll").getResultList();
    }

    public void guardar(Anuncio anuncio) {
        if (anuncio.getIdAnuncio() == null) em.persist(anuncio);
        else em.merge(anuncio);
    }
    public void eliminarAnuncio(Anuncio anuncio) {
        Anuncio anuncio1 = em.find(Anuncio.class, anuncio.getIdAnuncio());
        em.remove(anuncio1);
    }

    public List<Anuncio> obtenerConRelaciones() {
        return em.createNamedQuery("Anuncio.findAllWithRelationships").getResultList();
    }


    public Anuncio obtenerPorId(Integer id) {
        return em.find(Anuncio.class, id);
    }

    public List<Anuncio> obtieneAnuncio(String user){
    return em.createNamedQuery("Anuncio.findByContenidoAndUsuario").setParameter("user",user).getResultList();
    }
}



