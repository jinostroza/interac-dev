package cl.interac.dao;

import cl.interac.entidades.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    public Usuario obtenerPorUsuarioContrasenna(String user, String password) {
        Query q = em.createNamedQuery("Usuario.findByUserAndPassword");
        q.setParameter("username", user);
        q.setParameter("password", password);

        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario obtenerPorUsuario(String username) {
        Query q = em.createNamedQuery("Usuario.findByUser");
        q.setParameter("username", username);

        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;

        }




    }
}
