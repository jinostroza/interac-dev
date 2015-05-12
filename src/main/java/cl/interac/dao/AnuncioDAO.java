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

    public List<Anuncio> obtenerAnuncioCampana(Campana campana){
        StringBuilder nativeSql = new StringBuilder();
        nativeSql.append("select a.* from anuncio a");
        nativeSql.append("inner join camapana c on (a.id_campana= c.id_campana and id_campana =: idcampana)");
        return em.createNamedQuery(nativeSql.toString(),Anuncio.class)
              .setParameter("campana",campana.getIdcampana()).getResultList();
    }




    public Anuncio obtenerPorId(Integer id){
        return em.find(Anuncio.class, id);

    }

    public Anuncio obtenerDesc(String Desc) {
        return em.find(Anuncio.class, Desc);

    }

     public Anuncio obtenerMedia (String media){
         return em.find(Anuncio.class, media);
     }
    public Anuncio obtenerRubro (String rubro) {
        return em.find(Anuncio.class, rubro);
    }

   }



