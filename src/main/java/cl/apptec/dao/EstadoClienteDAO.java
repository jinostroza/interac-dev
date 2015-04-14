/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.EstadoCliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class EstadoClienteDAO {
     @PersistenceContext
    private EntityManager em;
     
    public List<EstadoCliente> obtenerTodos() {
        return em.createNamedQuery("EstadoCliente.findAll").getResultList();
    }
    public void guardar(EstadoCliente e){
         if (e.getIdEstadoCliente()== null) em.persist(e);
        else em.merge(e);
    }
    public void eliminarEstadoCliente(EstadoCliente e){
        EstadoCliente estadoCliente = em.find(EstadoCliente.class,e.getIdEstadoCliente());
        em.remove(estadoCliente);
    }
}
