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

    public List<Contenido> obtenerConRelacion(){ return em.createNamedQuery("Contenido.findWith").getResultList();}
    public void  guardar(Contenido c){
        if(c.getIdcontenido() == null )  em.persist(c);
        else em.merge(c);

    }
    public void eliminar(Contenido c){
        Contenido contenidos = em.find(Contenido.class,c.getIdcontenido());
        em.remove(contenidos);

    }


    public List<Contenido> obtenContenido(String user){
        return em.createNamedQuery("Contenido.findByUsuario").setParameter("user",user).getResultList();
    }
}
