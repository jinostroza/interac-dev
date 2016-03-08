package cl.interac.dao;

import cl.interac.entidades.Empresa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by PPablo on 30-11-2015.
 */
@Repository
public class EmpresaDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Empresa> obtenerTodos(){ return em.createNamedQuery("empresa.findAll").getResultList(); }



    public void guardarEmpresa(Empresa emp){
        if(emp.getIdEmpresa() == null){
            em.persist(emp);
        }
        else{
            em.merge(emp);
        }
    }

    public void eliminarEmpresa(Empresa emp){
        Empresa empresa = em.find(Empresa.class, emp.getIdEmpresa());
        em.remove(empresa);
    }
    public Empresa obtenerNombres(Integer empresa){
        return (Empresa ) em.createNamedQuery("Empresa.findnames").setParameter("empresa",empresa).getSingleResult();
    }
}
