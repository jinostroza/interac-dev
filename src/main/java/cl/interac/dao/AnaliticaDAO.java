/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Analitica;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joaco
 */
@Repository
public class AnaliticaDAO {
    @PersistenceContext
    private EntityManager em;

    public void guardar(Analitica a){
        if (a.getId_analitica()== null) em.persist(a);
        else em.merge(a);
    }

    public List<Analitica> obtenerTodos() {
        return em.createNamedQuery("Analitica.findAll").getResultList();
    }
    public List<Analitica> obtenerTodosGenero() {
        return em.createNamedQuery("Analitica.findAllS").getResultList();
    }
    /*public Long audiencia() {
        return(Long) em.createNamedQuery("Analitica.audiencia").getSingleResult();
    }*/

    public List<Analitica> totemA(Integer idtotem , Date fec_ini,Date fec_end) {
        return em.createNamedQuery("Analitica.totem").setParameter("idtotem", idtotem).setParameter("fec_ini", fec_ini).setParameter("fec_end", fec_end).getResultList();
    }

    public List<Analitica> contenidoA(String path, Date fec_ini,Date fec_end) {
        return  em.createNamedQuery("Analitica.contenido").setParameter("path", path).setParameter("fec_ini", fec_ini).setParameter("fec_end", fec_end).getResultList();
    }

    public List<Analitica> conTotem(Integer idtotem,String path, Date fec_ini,Date fec_end) {
        return em.createNamedQuery("Analitica.conTotem").setParameter("idtotem", idtotem).setParameter("path", path).setParameter("fec_ini", fec_ini).setParameter("fec_end", fec_end).getResultList();
    }






}
