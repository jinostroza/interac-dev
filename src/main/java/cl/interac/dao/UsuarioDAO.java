package cl.interac.dao;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.security.SHA512;
import cl.interac.util.dto.UsuarioDto;
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
        if (u.getIdUsuario() == null) {

            em.persist(u);
        } else em.merge(u);
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


    public void cambiarClave(String usuario, String clave) {
        Usuario u = (Usuario) em.createNamedQuery("Usuario.findByUser")
                .setParameter("username", usuario).getSingleResult();

        u.setPassword(clave);
        em.merge(u);
    }

    public void editarPerfil(String usuario, String correo, String empresa) {
        Usuario u = (Usuario) em.createNamedQuery("Usuario.findByUser")
                .setParameter("username", usuario).getSingleResult();
        u.setCorreo(correo);
        u.setEmpresa(empresa);
        em.merge(u);


    }

    public Rol obtenerRol(Usuario usuario) {
        Query q = em.createNamedQuery("Rol.findByUser");
        q.setParameter("user", usuario);

        try {
            return (Rol) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    public UsuarioDto obtenerUsuario(String usuario, String password) {
        UsuarioDto u = null;
        try {
            Usuario ue = (Usuario) em.createNamedQuery("Usuario.findByUserAndPassword")
                    .setParameter("username", usuario)
                    .setParameter("password", password).getSingleResult();
            u = new UsuarioDto();
            u.setUsername(ue.getUsername());


        } catch (Exception e) {
        }
        return u;


    }



}
