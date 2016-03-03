package cl.interac.dao;

import cl.interac.entidades.Comunas;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by luis on 27-04-2015.
 */
@Repository
public class ComunasDAO {
  @PersistenceContext
    private EntityManager em;
    public List<Comunas> ObtenerTodos(){return em.createNamedQuery("Comunas.findAll").getResultList();}
    public List<Comunas> obtenerConRelacion(Integer proviancia_id){return em.createNamedQuery("Comunas.findAllWithRelationships").setParameter("provincias_id", proviancia_id).getResultList();}
    public void guardar (Comunas b){
        if(b.getComuna_id()== null) em.persist(b);

        else em.merge(b);
    }

   public void eliminar(Comunas b){
       Comunas comunas = em.find(Comunas.class, b.getComuna_id());
       em.remove(comunas);

   }
    public Comunas obtenerNombres(Integer comuna){
        return (Comunas ) em.createNamedQuery("Comunas.findnames").setParameter("comuna",comuna).getSingleResult();
    }




}
