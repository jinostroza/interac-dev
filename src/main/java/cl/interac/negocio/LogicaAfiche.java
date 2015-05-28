package cl.interac.negocio;

import cl.interac.dao.AficheDAO;

import cl.interac.entidades.Afiche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.transaction.*;
import java.util.List;

/**
 * Created by luis on 27-05-2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaAfiche  {
    @Autowired
    private AficheDAO aficheDAO;

    @Transactional(readOnly = true)
    public List<Afiche> obtenerTodos(){ return aficheDAO.obtenerTodos();}

    @Transactional(readOnly = false)
    public void guardarCampana(Afiche a) {
        aficheDAO.guardar(a);
    }

    @Transactional(readOnly = false)
    public void eliminarCampana(Afiche a){
        aficheDAO.eliminar(a);
    }
}
