package cl.interac.dao.usuarios;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Jorge on 15-04-15.
 */
@Repository
public class UsuarioDAO {
    @PersistenceContext
    private EntityManager em;
}
