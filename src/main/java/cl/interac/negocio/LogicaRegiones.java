package cl.interac.negocio;

import cl.interac.dao.RegionesDAO;
import cl.interac.entidades.Regiones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luis on 27-04-2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaRegiones {
   @Autowired
    private RegionesDAO regionesDAO;

    @Transactional(readOnly = false)
    public void guardar(Regiones regiones){regionesDAO.guardar(regiones);}

    @Transactional(readOnly = true)
    public List<Regiones> obtenerTodas(){return regionesDAO.ObtenerTodos();}







}
