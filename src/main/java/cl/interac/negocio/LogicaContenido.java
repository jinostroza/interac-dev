package cl.interac.negocio;
import cl.interac.entidades.Contenido;
import cl.interac.dao.ContenidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.transaction.*;
import java.util.List;
/**
 * Created by Joaco on 17/08/2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaContenido {
    @Autowired
    private ContenidoDAO contenidoDAO;

    @Transactional(readOnly = true)
    public List<Contenido> obtenerTodos(){return contenidoDAO.obtenerTodos();}
    @Transactional(readOnly = true)
    public List<Contenido> obtenerConRelacion(){return contenidoDAO.obtenerTodos();}
    @Transactional(readOnly = true)
    public List<Contenido> obtenerConUsuarios(){return contenidoDAO.obtenerConUsuarios();}
    @Transactional(readOnly = false)
    public void guardar(Contenido c) {
        contenidoDAO.guardar(c);
    }

    @Transactional(readOnly = false)
    public void eliminarContenido(Contenido c){
        contenidoDAO.eliminar(c);
    }

    @Transactional(readOnly = false)
    public List<Contenido> obtenContenido(String user){
        return contenidoDAO.obtenContenido(user);
    }

    /*@Transactional(readOnly = true)
    public List<Contenido> obtenerEstadoYCampana(){
        return contenidoDAO.obtenerEstadoYCampana();
    }*/

}
