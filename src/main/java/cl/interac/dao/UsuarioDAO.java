package cl.interac.dao;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
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
        }
        else em.merge(u);
    }

    public void eliminar(Usuario u){
        Usuario usuario = em.find(Usuario.class, u.getIdUsuario());
        em.remove(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    public Usuario obtenerPorUsuarioContrasenna(String correo, String password) {
        Query q = em.createNamedQuery("Usuario.findUsuarioAndContrasenna");
        q.setParameter("username", correo);
        q.setParameter("password", password);

        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario obtenerPorId(Integer id){
        Query q = em.createNamedQuery("Usuario.findByID");
        q.setParameter("idusuario", id);
        try{
            return (Usuario) q.getSingleResult();
        }
        catch(Exception e){
            return null;
        }
    }

    public Usuario obtenerPorCorreo(String correo){
        Query q = em.createNamedQuery("Usuario.findByCorreo");
        q.setParameter("correo", correo);
        try{ return (Usuario) q.getSingleResult(); }
        catch(Exception e){ return null; }
    }

    public Usuario obtenerLogin(String correo , String pass){
        try{
            return (Usuario) em.createNamedQuery("Usuario.findByUserAndPassword")
                    .setParameter("correo",correo)
                    .setParameter("password",pass).getSingleResult();
           }catch (Exception e){
            return  null;
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

    public void editarPerfil(String usuario, String correo) {
        Usuario u = (Usuario) em.createNamedQuery("Usuario.findByUser")
                .setParameter("username", usuario).getSingleResult();
        u.setCorreo(correo);
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

    public List<Usuario> obtenerMisContenidos(){
        return em.createNamedQuery("Usuario.findWithRelationship").getResultList();
    }

    public List<Usuario> obtenerPorEmpresa(){
        return em.createNamedQuery("Usuario.findByEmpresa").getResultList();
    }

    public long verificarCorreo(String correo){
        return (Long) em.createNamedQuery("Usuario.CountCorreo").setParameter("correo",correo).getSingleResult();
    }

    public UsuarioDto obtenerUsuario(String correo, String password) {
        UsuarioDto u = null;
        try {
            Usuario ue = (Usuario) em.createNamedQuery("Usuario.findByUserAndPassword")
                    .setParameter("correo", correo)
                    .setParameter("password", password).getSingleResult();
            u = new UsuarioDto();
            u.setUsername(ue.getUsername());


        } catch (Exception e) {
        }
        return u;
    }
}
