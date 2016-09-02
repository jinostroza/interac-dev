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

    public Long countHombres(Integer idtotem) {
        return(Long) em.createNamedQuery("Analitica.countHombres").setParameter("idtotem", idtotem).getSingleResult();
    }

    public Long countMujeres(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countMujeres").setParameter("idtotem", idtotem).getSingleResult();
    }

    public Long seg1(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSeg1").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long seg2(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSeg2").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long seg3(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSeg3").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long seg4(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSeg4").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long seg5(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSeg5").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long seg6(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSeg6").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long seg7(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSeg7").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long feliz(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countHappy").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long triste(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSad").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long neutral(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countNeutral").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long enojado(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countAnger").setParameter("idtotem", idtotem).getSingleResult();
    }
    public Long sorpresa(Integer idtotem) {
        return (Long) em.createNamedQuery("Analitica.countSurp").setParameter("idtotem", idtotem).getSingleResult();
    }



}
