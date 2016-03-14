package cl.interac.negocio;

import cl.interac.dao.EstablecimientoDAO;
import cl.interac.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pedro Pablo on 20-05-2015.
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaEstablecimiento {
    @Autowired
    private EstablecimientoDAO establecimientoDAO;

    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerTodos() {
        return establecimientoDAO.obtenerTodos();
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> obbtenerPorTotem() {
        return establecimientoDAO.obtenerPorTotem();
    }

    @Transactional(readOnly = true)
    public Establecimiento obtenerPorId(Integer id) {
        return establecimientoDAO.obtenerPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerConRelacion() {
        return establecimientoDAO.obtenerConRelacion();
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerPorEstado() { return establecimientoDAO.obtenerPorEstado(); }

    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerPorEmpresa() { return establecimientoDAO.obtenerPorEmpresa(); }

    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerPorUsuario(String username) {
        return establecimientoDAO.obtenerPorUsuario(username);
    }
    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerPorIDUsuario(Integer user,Integer estab) {
        return establecimientoDAO.obtenerPorIDUsuario(user,estab);
    }
    @Transactional(readOnly = true)
    public List<Establecimiento> filtrar(String orienta,String nombre,Integer empresa,Integer ubicacion,Integer rubro) {
        return establecimientoDAO.filtrar(orienta, nombre, empresa, ubicacion, rubro);
    }
    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerFiltro(String orienta,Empresa empresa,Comunas comunas,Provincias provincias,Regiones regiones,Categoria categoria) {
        return establecimientoDAO.obtenerFiltro(orienta,empresa,comunas,regiones,provincias,categoria);
    }

    @Transactional(readOnly = false)
    public void guardar(Establecimiento e) {
        establecimientoDAO.guardar(e);
    }

    @Transactional(readOnly = false)
    public void eliminar(Establecimiento e) {
        establecimientoDAO.eliminar(e);
    }
}