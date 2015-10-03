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
        return em.createNamedQuery("Campana.findByUsuario").setParameter("username",user).getResultList();
    }



}
