/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Usuario;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Yesenia Doria L.
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
                    .setParameter("usernameUsuario", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario obtenerUsuario(String username, String password) {
        try {
            return (Usuario) em.createNamedQuery("Usuario.findByUsernameAndPassword")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
