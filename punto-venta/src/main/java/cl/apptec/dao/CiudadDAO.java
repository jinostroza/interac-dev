/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Ciudad;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Comuna;
import cl.apptec.entidades.Pais;
import org.springframework.stereotype.Repository;

/**
 * @author Yesenia Doria L.
 */
@Repository
public class CiudadDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Ciudad> obtenerTodas() {
        return em.createNamedQuery("Ciudad.findAll").getResultList();
    }

    public void guardar(Ciudad c) {
        if (c.getIdCiudad() == null) em.persist(c);
        else em.merge(c);
    }

    public void eliminarCiudad(Ciudad c) {
        Ciudad ciudad = em.find(Ciudad.class, c.getIdCiudad());
        em.remove(ciudad);
    }

    public List<Comuna> obtenerComunas(Ciudad ciudad) {
        return em.createNamedQuery("Comuna.findByCiudad").setParameter("ciudad", ciudad).getResultList();
    }

    public List<Ciudad> obtenerPorPais(Pais pais) {
        return em.createNamedQuery("Ciudad.findByPais").setParameter("pais", pais).getResultList();
    }
}
