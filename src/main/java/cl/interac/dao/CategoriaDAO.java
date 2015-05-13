package cl.interac.dao;

import cl.interac.entidades.Categoria;
import cl.interac.entidades.Totem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by luis on 13-05-2015.
 */
@Repository
public class CategoriaDAO {
    @PersistenceContext
    private EntityManager em ;



    public List<Categoria> obtenerTodos() {
        return em.createNamedQuery("Categoria.findAll").getResultList();
    }

    public void guardar(Categoria g){
        if (g.getIdcategoria()== null) em.persist(g);
        else em.merge(g);
    }

    public void eliminar(Categoria g){
        Categoria categoria= em.find(Categoria.class, g.getIdcategoria());
        em.remove(categoria);
    }
}
