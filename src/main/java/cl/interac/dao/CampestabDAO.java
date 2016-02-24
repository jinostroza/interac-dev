/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Campestab;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edggar
 */
@Repository
public class CampestabDAO {
    @PersistenceContext
    private EntityManager em;

    public void guardar(Campestab ce) {
        if (ce.getIdcam_est() == null) em.persist(ce);
        else em.merge(ce);
    }

    public void eliminar(Campestab ce) {
        Campestab campestab = em.find(Campestab.class, ce.getIdcam_est());
        em.remove(campestab);
    }
    public List<Campestab> obtenerTodos() {
        return em.createNamedQuery("campestab.findAll").getResultList();
    }
    public List<Campestab> obtenerPorEstado(String user){
        return em.createNamedQuery("campestab.findbyEstablecimiento").setParameter("username", user).getResultList();
    }
    public List<Campestab> obtenerAprobados(String user){
        return em.createNamedQuery("campestab.findbyAprobado").setParameter("username", user).getResultList();
    }
    public long obtenerNumeroNuevas(String user){
        return (Long) em.createNamedQuery("campestab.count").setParameter("username",user).getSingleResult();
    }
}
