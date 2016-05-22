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

}
