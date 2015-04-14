/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.dao;

import cl.apptec.entidades.CambioMoneda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author secabezas
 */
@Repository
public class CambioMonedaDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<CambioMoneda> obtenerTodos() {
        return em.createNamedQuery("CambioMoneda.findAll").getResultList();
    }
    public void guardar(CambioMoneda m){
        if (m.getIdCambiomoneda() == null) em.persist(m);
        else em.merge(m);
    }
    public void eliminarCambioMoneda(CambioMoneda m){
        CambioMoneda cambioMoneda = em.find(CambioMoneda.class, m.getIdCambiomoneda());
        em.remove(cambioMoneda);
    }
}