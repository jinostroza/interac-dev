/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.*;
import cl.interac.negocio.LogicaCampana;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edggar
 */
@Repository
public class CampanaDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Campana> obtenerTodos() {
        return em.createNamedQuery("Campana.findAll").getResultList();
    }

    public void guardar(Campana c){
        if (c.getIdcampana()== null) em.persist(c);
        else em.merge(c);
    }

    public void eliminarCampana(Campana c){
        Campana campana = em.find(Campana.class, c.getIdcampana());
        em.remove(campana);
    }

    public Campana obtenerPorId(Integer id){
        return em.find(Campana.class , id);
    }


    public List<Campana> obtenerTodasLosContenidos(){
        return em.createNamedQuery("Campana.findBycontenido").getResultList();
    }

    public List<Campana> obtenerCampanaPorUsuario(String user){
        return em.createNamedQuery("Campana.findByUsuario").setParameter("username", user).getResultList();
    }


    public Campana obtenerPorIdConTotems(Integer id) {
        try {
            return (Campana) em.createNamedQuery("Campana.findByIdWithTotems").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Campana> obtenerLasCampanasDelosTotems(String user){
        try{
            return em.createNamedQuery("Campana.findByTotem").setParameter("username",user).getResultList();
        }catch (Exception e){
            return null;
        }

    }
    public List<Campana> obtenerPorEstado(String user){
        return em.createNamedQuery("Campana.findByEstado").setParameter("username",user).getResultList();

    }

    public long obtenerNumeroNuevas(String user){
        return (Long) em.createNamedQuery("Campana.count").setParameter("username",user).getSingleResult();
    }

}
