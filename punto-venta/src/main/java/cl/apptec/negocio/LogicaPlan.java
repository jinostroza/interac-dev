/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;


import cl.apptec.dao.PlanDAO;
import cl.apptec.entidades.Plan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Yesenia Doria L.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaPlan {
    @Autowired
     private PlanDAO planDAO;
     
     @Transactional(readOnly = true)
     public List<Plan> obtenerTodos() {
        return planDAO.obtenerTodos();
    }
    @Transactional(readOnly = false)
    public void guardarPlan(Plan p){
        planDAO.guardar(p);
    }
    @Transactional(readOnly = false)
    public void eliminarPlan(Plan p){
        planDAO.eliminarPlan(p);
    }
}
