package cl.interac.dao;

import cl.interac.entidades.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Jorge on 15-04-15.
 */
@Repository
public class UsuarioDAO {
    @PersistenceContext
    private EntityManager em;

    public void guardar(Usuario u) {
        if (u.getIdUsuario() == null) em.persist(u);
        else em.merge(u);
    }

    public List<Usuario> obtenerTodos() {

        System.out.println("usuarios:"+ em.createNamedQuery("Usuario.findAll").getResultList());
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
}
