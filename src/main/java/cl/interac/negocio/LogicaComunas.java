package cl.interac.negocio;

import cl.interac.dao.ComunasDAO;
import cl.interac.entidades.Comunas;
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
public class LogicaComunas {
   @Autowired
    private ComunasDAO comunasDAO;

    @Transactional(readOnly = false)
    public void guardar(Comunas comunas){comunasDAO.guardar(comunas);}

    @Transactional(readOnly = true)
    public List<Comunas> obtenerTodas(){return comunasDAO.ObtenerTodos();}

 @Transactional(readOnly = true)
 public List<Comunas> obtenerConRealacion(Integer provincia_id){return comunasDAO.obtenerConRelacion(provincia_id);}





}
