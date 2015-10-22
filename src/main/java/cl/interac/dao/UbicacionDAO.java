package cl.interac.dao;

import cl.interac.entidades.Ubicacion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 27-04-2015.
 */
@Repository
public class UbicacionDAO  {
  @PersistenceContext
    private EntityManager em;
    public List<Ubicacion> ObtenerTodos(){return em.createNamedQuery("Ubicacion.findAll").getResultList();}
    public List<Ubicacion> obtenerConRelacion(){return em.createNamedQuery("Ubicacion.findAllWithRelationships").getResultList();}
    public void guardar (Ubicacion b){
        if(b.getIdubicacion()== null) em.persist(b);

        else em.merge(b);
    }

   public void eliminarUbicacion(Ubicacion b){
       Ubicacion ubicacion = em.find(Ubicacion.class, b.getIdubicacion());
       em.remove(ubicacion);

   }




}
