/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Proyecto;
import cl.apptec.entidades.Pais;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 * @modificado Matias Harding
 */
@Repository
public class ProyectoDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Proyecto> obtenerTodos() {
        return em.createNamedQuery("Proyecto.findAll").getResultList();
    }
        public void guardar(Proyecto p) {
        if (p.getIdProyecto() == null) em.persist(p);
        else em.merge(p);
    }

    public void eliminarProyecto(Proyecto p) {
        
        Proyecto proyecto = em.find(Proyecto.class, p.getIdProyecto());
        proyecto.setActivoProyecto(false);
        //em.remove(proyecto);
    }
    
    public boolean existeProyecto(String nombreProyecto) {
        try {
            Proyecto p = (Proyecto) em.createNamedQuery("Proyecto.findByNombreProyecto")
                    .setParameter("nombreProyecto", nombreProyecto).getSingleResult();
            return p != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<Pais> obtenerPaises(Pais pais){
        return em.createNamedQuery("Pais.findByPais").setParameter("pais", pais).getResultList();
    }
    
            
    public List<Proyecto> obtenerPorPais(Pais pais) {
        return em.createNamedQuery("Proyecto.findByPais").setParameter("pais", pais).getResultList();
    }
}