package cl.interac.dao;

import cl.interac.entidades.Categoria;
import cl.interac.entidades.Empresa;
import cl.interac.entidades.Establecimiento;
import cl.interac.entidades.Ubicacion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pedro Pablo on 20-05-2015.
 */
@Repository
public class EstablecimientoDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Establecimiento> obtenerTodos() {
        return em.createNamedQuery("establecimiento.findAll").getResultList();
    }

    public List<Establecimiento> obtenerPorTotem() {
        return em.createNamedQuery("establecimiento.findtotem").getResultList();
    }
        public void guardar(Establecimiento es) {
        if (es.getIdEstablecimiento() == null) em.persist(es);
        else em.merge(es);
    }

    public void eliminar(Establecimiento es) {
        Establecimiento establecimiento = em.find(Establecimiento.class, es.getIdEstablecimiento());
        em.remove(establecimiento);
    }

    public List<Establecimiento> obtenerConRelacion() {
        return em.createNamedQuery("estabecimiento.findAllByusuario").getResultList();
    }

    public List<Establecimiento> obtenerPorUsuario(String usuario){
        return em.createNamedQuery("Campana.findByUsuario").setParameter("username", usuario).getResultList();
    }

    public Establecimiento obtenerPorId(Integer id) {
        return em.find(Establecimiento.class, id);
    }

    public List<Establecimiento> obtenerPorEstado(){
        return em.createNamedQuery("establecimiento.findByEstado").getResultList();
    }

    public List<Establecimiento> obtenerPorEmpresa(){
        return em.createNamedQuery("establecimiento.findByEmpresa").getResultList();
    }
    public List<Establecimiento> filtrar(String orienta,String nombre,Integer empresa,Integer ubicacion,Integer rubro){
        return em.createNamedQuery("establecimiento.findFilter").setParameter("orientacion", orienta).setParameter("nombre",nombre).setParameter("idempresa",empresa).setParameter("idubicacion",ubicacion).setParameter("idcategoria", rubro).getResultList();
    }
    public List<Establecimiento> obtenerFiltro(String orienta,String nombre,Empresa empresa,Ubicacion ubicacion,Categoria categoria) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append("    e.* ");
        sb.append("from establecimiento e ");
        sb.append("inner join empresa em on (em.idempresa = e.empresa) " );
        sb.append("inner join ubicacion ub on (ub.idubicacion = e.idubicacion) " );
        sb.append("inner join categoria ca on (ca.idcategoria = e.fk_rubro) " );
        sb.append("where e.estado='Activado' " );
        if (orienta != null){
            sb.append("and e.orientacion=:orienta ");
        }
        if (nombre != null){
            sb.append("and e.desestablecimiento=:nombre ");
        }
        if (empresa != null){
            sb.append("and e.empresa=:empresa ");
        }
        if (ubicacion != null){
            sb.append("and e.idubicacion=:ubicacion ");
        }
        if (categoria != null){
            sb.append("and e.fk_rubro=:categoria ");
        }
        Query q = em.createNativeQuery(sb.toString(), Establecimiento.class);

        if (orienta != null){
            q.setParameter("orienta", orienta);
        }
        if (nombre != null){
            q.setParameter("nombre", nombre);
        }
        if (empresa != null){
            q.setParameter("empresa", empresa.getIdEmpresa());
        }
        if (ubicacion != null){
            q.setParameter("ubicacion", ubicacion.getIdubicacion());
        }
        if (categoria != null){
            q.setParameter("categoria", categoria.getIdcategoria());
        }
        return q.getResultList();
    }
}