package cl.interac.negocio;

import cl.interac.dao.MesesDAO;
import cl.interac.dao.RolDAO;
import cl.interac.entidades.Meses;
import cl.interac.entidades.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Joaco on 14-05-2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaMeses {

    @Autowired
    private MesesDAO mesesDAO;

    @Transactional(readOnly = true)
   public List<Meses> obtenerTodos() {
        return mesesDAO.obtenerTodos();
    }
}
