/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class ClienteDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<Cliente> obtenerTodos() {
        return em.createNamedQuery("Cliente.findAll").getResultList();
    }
    public void guardar(Cliente c){
        if (c.getIdCliente()== null) em.persist(c);
        else em.merge(c);
    }
    public void eliminarCliente(Cliente c){
        Cliente cliente = em.find(Cliente.class, c.getIdCliente());
        em.remove(cliente);
    }

    public Cliente obtenerPorRut(Integer rut) {
        try {
            return (Cliente) em.createNamedQuery("Cliente.findByRut")
                    .setParameter("rut", rut)
                    .getSingleResult();
        } catch(Exception e ){
            return null;
        }
    }
}
