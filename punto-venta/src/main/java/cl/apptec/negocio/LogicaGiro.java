package cl.apptec.negocio;

import cl.apptec.dao.GiroDao;

import java.util.List;

import cl.apptec.entidades.Giro;
import cl.apptec.entidades.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author secabezas
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaGiro {
    @Autowired
    private GiroDao giroDao;
    
    @Transactional(readOnly = true)
    public List<Giro> obtenerTodos() {
        return giroDao.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarGiro(Giro g) {
        giroDao.guardar(g);
    }

    @Transactional(readOnly = false)
    public void eliminarPais(Giro g) {
        giroDao.eliminarGiro(g);
    }

    @Transactional(readOnly = true)
    public boolean existePais(String nombreGiro) {
        return giroDao.existeGiro(nombreGiro); // data access object
    }
    
    @Transactional(readOnly = true)
    public List<Giro> obtenerPorPais(Pais pais) {
        return giroDao.obtenerPorPais(pais);
    }
}
