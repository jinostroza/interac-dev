package cl.interac.dao;

import cl.interac.entidades.Meses;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Joaco on 14-05-2015.
 */

@Repository
public class MesesDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Meses> obtenerTodos(){return em.createNamedQuery("Meses.findAll").getResultList();}



}
