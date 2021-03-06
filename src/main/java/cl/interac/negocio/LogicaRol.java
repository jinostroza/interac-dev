package cl.interac.negocio;

import cl.interac.dao.RolDAO;
import cl.interac.entidades.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.*;
import java.util.List;

/**
 * Created by luis on 14-05-2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaRol {

    @Autowired
    private RolDAO rolDAO;

    @Transactional(readOnly = true)
   public List<Rol> obtenerTodos() {
        return rolDAO.obtenerTodos();
    }
}
