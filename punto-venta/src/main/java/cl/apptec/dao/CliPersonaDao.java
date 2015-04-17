/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.CliPersona;
import cl.apptec.entidades.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Matias Harding
 */
@Repository
public class CliPersonaDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<CliPersona> obtenerTodos() {
        return em.createNamedQuery("CliPersona.findAll").getResultList();
    }
    
    public List<CliPersona> obtenerPorProyecto(Proyecto proyecto) {
        try{
            return em.createNamedQuery("CliPersona.findByProyecto").setParameter("proyecto",proyecto).getResultList();
        } catch(Exception e ){
            return null;
        }
    }
    
    public void guardar(CliPersona cp){
        if (cp.getIdCliPersona()== null) em.persist(cp);
        else em.merge(cp);
    }
    public void eliminarCliPersona(CliPersona cp){
        CliPersona cliPersona = em.find(CliPersona.class, cp.getIdCliPersona());
        em.remove(cliPersona);
    }
    
    public CliPersona obtenerPorRut(Integer rut) {
        try {
            return (CliPersona) em.createNamedQuery("CliPersona.findByRut")
                    .setParameter("rut", rut)
                    .getSingleResult();
        } catch(Exception e ){
            return null;
        }
    }
        
}
