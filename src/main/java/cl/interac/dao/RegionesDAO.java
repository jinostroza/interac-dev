package cl.interac.dao;

import cl.interac.entidades.Regiones;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Joaco on 27-04-2015.
 */
@Repository
public class RegionesDAO {
  @PersistenceContext
    private EntityManager em;
    public List<Regiones> ObtenerTodos(){return em.createNamedQuery("Regiones.findAll").getResultList();}
    public List<Regiones> obtenerConRelacion(Integer region_id){
        return em.createNamedQuery("Regiones.findAllWithRelationships").setParameter("region_id",region_id).getResultList();}
    public void guardar (Regiones b){
        if(b.getRegion_id()== null) em.persist(b);

        else em.merge(b);
    }

   public void eliminarUbicacion(Regiones b){
       Regiones regiones = em.find(Regiones.class, b.getRegion_id());
       em.remove(regiones);

   }




}
