/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.dao;

import cl.interac.entidades.Contrato;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Joaco
 */
@Repository
public class ContratoDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Contrato> obtenerTodos() {
        return em.createNamedQuery("Contrato.findAll").getResultList();
    }

    public void guardar(Contrato c){
        if (c.getIdcontrato()== null) em.persist(c);
        else em.merge(c);
    }

    public void eliminar(Contrato c){
        Contrato contrato = em.find(Contrato.class, c.getIdcontrato());
        em.remove(contrato);
    }


}
