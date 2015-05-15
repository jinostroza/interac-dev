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
    private EntityManager em;

    public List<Rol> obtenerTodos(){return em.createNamedQuery("rol.FindAll").getResultList();}

}
