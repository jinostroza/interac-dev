package cl.interac.dao;

import cl.interac.entidades.Marcapantalla;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

/**
 * Created by PPablo on 09-11-2015.
 */
@Repository
public class MarcapantallaDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Marcapantalla> obtenerTodos(){
        return em.createNamedQuery("Marcapantalla.findAll").getResultList();
    }

    public void guardar(Marcapantalla mp){
        if (mp.getIdmarca() == null) em.persist(mp);
        else em.merge(mp);
    }
}
