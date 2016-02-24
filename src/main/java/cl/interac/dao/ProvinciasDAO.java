package cl.interac.dao;

import cl.interac.entidades.Provincias;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by luis on 27-04-2015.
 */
@Repository
public class ProvinciasDAO {
  @PersistenceContext
    private EntityManager em;
    public List<Provincias> ObtenerTodos(){return em.createNamedQuery("Provincias.findAll").getResultList();}
    public List<Provincias> obtenerConRelacion(Integer region_id){
        return em.createNamedQuery("Provincias.findAllWithRelationships").setParameter("region_id", region_id).getResultList();}
    public void guardar (Provincias b){
        if(b.getProvincia_id()== null) em.persist(b);

        else em.merge(b);
    }

   public void eliminar(Provincias b){
       Provincias provincias = em.find(Provincias.class, b.getProvincia_id());
       em.remove(provincias);

   }




}
