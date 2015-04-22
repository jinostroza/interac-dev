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

    public List<Usuario> obtenerTodos() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    public void guardar(Usuario u) {
        if (u.getIdUsuario() == null) em.persist(u);
        else em.merge(u);
    }

    public void eliminarUsuario(Usuario u) {
        Usuario usuario = em.find(Usuario.class, u.getIdUsuario());
        em.remove(usuario);
    }

    public Usuario obtenerUsuario(String username) {
        try {
            return (Usuario) em.createNamedQuery("Usuario.findByUsernameUsuario")
                    .setParameter("USER", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario obtenerUsuario(String username, String password) {
        try {
            return (Usuario) em.createNamedQuery("Usuario.findByUsernameAndPassword")
                    .setParameter("USER", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
