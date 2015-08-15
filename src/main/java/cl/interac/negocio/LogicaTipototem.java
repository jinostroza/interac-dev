package cl.interac.negocio;

import cl.interac.dao.TipototemDAO;
import cl.interac.entidades.Tipototem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luisPc on 15-08-2015.
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaTipototem {

    @Autowired
    private TipototemDAO tipototemDAO;

    @Transactional(readOnly = true)
    public List<Tipototem> obtenerTodos() {
        return tipototemDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardar(Tipototem t) {
        tipototemDAO.guardar(t);
    }

    @Transactional(readOnly = false)
    public void eliminarTotem(Tipototem t) {
        tipototemDAO.eliminar(t);
    }

    @Transactional(readOnly = true)
    public List<Tipototem> obtenerConRelacion() {
        return tipototemDAO.obtenerConRelacion();
    }
}
