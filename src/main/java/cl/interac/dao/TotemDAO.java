/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Totem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edggar
 */
@Repository
public class TotemDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Totem> obtenerTodos() {
        return em.createNamedQuery("Totem.findAll").getResultList();
    }

    public void guardar(Totem t){
       if (t.getIdtotem()== null) em.persist(t);
       else
           em.merge(t);
    }

    public void eliminarTotem(Totem t){
        Totem totem = em.find(Totem.class, t.getIdtotem());
        em.remove(totem);
    }

    public List<Totem> obtenerConCampana(String user){
        try{
         return em.createNamedQuery("Totem.findByIdWithTotem").setParameter("username",user).getResultList();
        }catch (Exception e){
            return null;
        }
    }

    public List<Totem> obtenerConRelacion(){
       return em.createNamedQuery("Totem.findWithRelationship").getResultList();
    }
    public List<Totem> obtenertodos(){
        return em.createNamedQuery("Totem.findAll").getResultList();
    }
    public List<Totem> obtenerConUsuario(String username){
        return em.createNamedQuery("Totem.findbyUsuario").setParameter("username", username).getResultList();
    }

    public long obtenerNumero(Integer establecimiento){
        return (Long) em.createNamedQuery("Totem.count").setParameter("establecimiento",establecimiento).getSingleResult();
    }
    public List<Totem> obtenerPorEstablecimiento(Integer establecimiento){
        return em.createNamedQuery("Totem.findByEstablecimiento").setParameter("establecimiento", establecimiento).getResultList();
    }

    public List<Totem> obtenerPorEstado(){
        return em.createNamedQuery("Totem.findByEstado").getResultList();
    }
}
