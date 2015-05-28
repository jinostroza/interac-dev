package cl.interac.dao;

import cl.interac.entidades.Rol;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

/**
 * Created by luis on 14-05-2015.
 */

@Repository
public class RolDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Rol> obtenerTodos(){return em.createNamedQuery("Rol.findAll").getResultList();}

    public void guardar(Rol r){
        if(r.getIdrol()== null )  em.persist(r);
        else em.merge(r);
    }


    public void eliminar(Rol r){
        Rol rol = em.find(Rol.class,r.getIdrol());
        em.remove(rol);
    }

}
