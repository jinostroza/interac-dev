package cl.interac.negocio;

import cl.interac.dao.TallerDAO;
import cl.interac.entidades.Taller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luis on 14-05-2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaTaller {

    @Autowired
    private TallerDAO tallerDAO;

    @Transactional(readOnly = false)
    public void guardar(Taller taller) {
        tallerDAO.guardar(taller);
    }

    @Transactional(readOnly = false)
    public void eliminar(Taller taller) {
        tallerDAO.eliminar(taller);
    }

    @Transactional(readOnly = true)
   public List<Taller> obtenerTodos() {
        return tallerDAO.obtenerTodos();
    }
}
