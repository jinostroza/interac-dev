/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.ClienteApptec;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yesenia Doria L.
 */
@Repository
public class ClienteApptecDAO {
    @PersistenceContext
    private EntityManager em;
    
    public List<ClienteApptec> obtenerTodos() {
        return em.createNamedQuery("ClienteApptec.findAll").getResultList();
    }
    
   
    public void guardar(ClienteApptec c) {
        if (c.getIdClienteApptec()== null) em.persist(c);
        else em.merge(c);
    }
    public void eliminarCA(ClienteApptec c){
        ClienteApptec cliente = em.find(ClienteApptec.class, c.getIdClienteApptec());
        em.remove(cliente);
    }
}
