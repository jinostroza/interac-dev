package cl.interac.negocio;

import cl.interac.dao.EstablecimientoDAO;
import cl.interac.entidades.Establecimiento;
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

    @Transactional(readOnly = false)
    public void guardar(Establecimiento e) {
        establecimientoDAO.guardar(e);
    }

    @Transactional(readOnly = false)
    public void eliminar(Establecimiento e) {
        establecimientoDAO.eliminar(e);
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerPorUsuario(String username) {
        return establecimientoDAO.obtenerPorUsuario(username);
    }

    @Transactional(readOnly = true)
    public Establecimiento obtenerPorId(Integer id) {
        return establecimientoDAO.obtenerPorId(id);
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> obtenerConRelacion() {
        return establecimientoDAO.obtenerConRelacion();
    }
}