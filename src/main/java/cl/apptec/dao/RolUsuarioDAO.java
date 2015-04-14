/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.RolUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class RolUsuarioDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<RolUsuario> obtenerTodos() {
        return em.createNamedQuery("RolUsuario.findAll").getResultList();
    }

    public void guardar(RolUsuario r) {
        if (r.getIdRolUsuario()== null) em.persist(r);
        else em.merge(r);
    }
    public void eliminarRolUsuario(RolUsuario r){
        RolUsuario rol = em.find(RolUsuario.class,r.getIdRolUsuario());
        em.remove(rol);
    }
    
}
