package cl.interac.dao;

import cl.interac.entidades.*;
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
        return em.createNamedQuery("establecimiento.findbyUser").setParameter("username", usuario).getResultList();
    }
    public List<Establecimiento> obtenerPorIDUsuario(Integer usuario,Integer establecimiento){
        return  em.createNamedQuery("establecimiento.findbyIDUser").setParameter("idUsuario", usuario).setParameter("idEstablecimiento",establecimiento).getResultList();
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
    public List<Establecimiento> obtenerFiltro(String orienta,Empresa empresa,Comunas comunas,Regiones regiones,Provincias provincias,Categoria categoria) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append("e.* ");
        sb.append("from establecimiento e ");
        sb.append("left join empresa em on (em.idempresa = e.empresa) " );
        sb.append("left join usuario us on (us.idusuario = e.idusuario) " );
        sb.append("left join comunas c on (c.comuna_id = e.idubicacion) " );
        sb.append("left join provincias p on (p.provincia_id = e.idprovincia) " );
        sb.append("left join regiones r on (r.region_id = e.idregion) " );
        sb.append("left join categoria ca on (ca.idcategoria = e.fk_rubro) " );
        sb.append("where e.estado='Activado' " );
        if (orienta != null){
            sb.append("and e.orientacion=:orienta ");
        }
        if (empresa != null){
            sb.append("and e.empresa=:empresa ");
        }
        if (comunas != null){
            sb.append("and e.idubicacion=:comunas ");
        }
        if (provincias != null){
            sb.append("and e.idprovincia=:provincias ");
        }
        if (regiones != null){
            sb.append("and e.idregion=:regiones ");
        }
        if (categoria != null){
            sb.append("and e.fk_rubro=:categoria ");
        }
        Query q = em.createNativeQuery(sb.toString(), Establecimiento.class);

        if (orienta != null){
            q.setParameter("orienta", orienta);
        }
        if (empresa != null){
            q.setParameter("empresa", empresa.getIdEmpresa());
        }
        if (comunas != null){
            q.setParameter("comunas", comunas.getComuna_id());
        }
        if (provincias != null){
            q.setParameter("provincias", provincias.getProvincia_id());
        }
        if (regiones != null){
            q.setParameter("regiones",regiones.getRegion_id());
        }
        if (categoria != null){
            q.setParameter("categoria", categoria.getIdcategoria());
        }
        return q.getResultList();
    }
}