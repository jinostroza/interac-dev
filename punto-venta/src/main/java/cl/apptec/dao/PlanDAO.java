/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Plan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class PlanDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Plan> obtenerTodos() {
        return em.createNamedQuery("Plan.findAll").getResultList();
    }
    public void guardar(Plan p){
        if (p.getIdPlan()== null) em.persist(p);
        else em.merge(p);
    }
    public void eliminarPlan(Plan p){
        Plan plan = em.find(Plan.class, p.getIdPlan());
        plan.setActivoPlan(false);
        //em.remove(plan);
    }
    
}
