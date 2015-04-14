package cl.apptec.negocio;

import cl.apptec.dao.PaisDao;

import java.util.List;

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
public class LogicaPais {
    @Autowired
    private PaisDao paisDao;
    
    @Transactional(readOnly = true)
    public List<Pais> obtenerTodos() {
        return paisDao.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarPais(Pais p) {
        paisDao.guardar(p);
    }

    @Transactional(readOnly = false)
    public void eliminarPais(Pais p) {
        paisDao.eliminarPais(p);
    }

    @Transactional(readOnly = true)
    public boolean existePais(String nombrePais) {
        return paisDao.existePais(nombrePais); // data access object
    }
}
