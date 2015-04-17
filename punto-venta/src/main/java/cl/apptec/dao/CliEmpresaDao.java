/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.CliEmpresa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Proyecto;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Matias Harding
 */
@Repository
public class CliEmpresaDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<CliEmpresa> obtenerTodos() {
        return em.createNamedQuery("CliEmpresa.findAll").getResultList();
    }
    
    public List<CliEmpresa> obtenerPorProyecto(Proyecto proyecto) {
        try{
            return em.createNamedQuery("CliEmpresa.findByProyecto").setParameter("proyecto",proyecto).getResultList();
        } catch(Exception e ){
            return null;
        }
    }
    
    public void guardar(CliEmpresa ce){
        if (ce.getIdCliEmpresa()== null) em.persist(ce);
        else em.merge(ce);
    }
    public void eliminarCliEmpresa(CliEmpresa ce){
        CliEmpresa cliEmpresa = em.find(CliEmpresa.class, ce.getIdCliEmpresa());
        em.remove(cliEmpresa);
    }
    
    public CliEmpresa obtenerPorRut(Integer rut) {
        try {
            return (CliEmpresa) em.createNamedQuery("CliEmpresa.findByRut")
                    .setParameter("rut", rut)
                    .getSingleResult();
        } catch(Exception e ){
            return null;
        }
    }
        
}
