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
import java.util.List;

/**
 *
 * @author Joaco
 */
@Repository
public class AnaliticaDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Analitica> obtenerTodos() {
        return em.createNamedQuery("Analitica.findAll").getResultList();
    }
    public List<Analitica> obtenerTodosGenero() {
        return em.createNamedQuery("Analitica.findAllS").getResultList();
    }
    public Long audiencia() {
        return(Long) em.createNamedQuery("Analitica.audiencia").getSingleResult();
    }

    public Long countHombres() {
        return(Long) em.createNamedQuery("Analitica.countHombres").getSingleResult();
    }

    public Long countMujeres() {
        return (Long) em.createNamedQuery("Analitica.countMujeres").getSingleResult();
    }

    public Long seg1() {
        return (Long) em.createNamedQuery("Analitica.countSeg1").getSingleResult();
    }
    public Long seg2() {
        return (Long) em.createNamedQuery("Analitica.countSeg2").getSingleResult();
    }
    public Long seg3() {
        return (Long) em.createNamedQuery("Analitica.countSeg3").getSingleResult();
    }
    public Long seg4() {
        return (Long) em.createNamedQuery("Analitica.countSeg4").getSingleResult();
    }
    public Long seg5() {
        return (Long) em.createNamedQuery("Analitica.countSeg5").getSingleResult();
    }
    public Long seg6() {
        return (Long) em.createNamedQuery("Analitica.countSeg6").getSingleResult();
    }
    public Long seg7() {
        return (Long) em.createNamedQuery("Analitica.countSeg7").getSingleResult();
    }




}
