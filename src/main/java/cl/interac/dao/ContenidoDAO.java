package cl.interac.dao;

import cl.interac.entidades.Contenido;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
/**
 * Created by joaco on 17/08/2015.
 */
@Repository
public class ContenidoDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Contenido> obtenerTodos(){ return em.createNamedQuery("Contenido.findAll").getResultList();}


    public void  guardar(Contenido contenido){
        if(contenido.getIdcontenido()== null )  em.persist(contenido);
        else em.merge(contenido);

    }
    public void eliminar(Contenido contenido){
        Contenido contenidos = em.find(Contenido.class,contenido.getIdcontenido());
        em.remove(contenidos);

    }
}
