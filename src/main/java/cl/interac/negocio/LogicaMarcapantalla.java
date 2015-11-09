package cl.interac.negocio;

import cl.interac.dao.MarcapantallaDAO;
import cl.interac.entidades.Marcapantalla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.*;
import java.util.List;

/**
 * Created by PPablo on 09-11-2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaMarcapantalla {
    @Autowired
    private MarcapantallaDAO marcapantallaDAO;

    @Transactional(readOnly = true)
    public List<Marcapantalla> obtenerTodos() {
        return marcapantallaDAO.obtenerTodos();
    }

}
