package cl.interac.negocio;

import cl.interac.dao.UbicacionDAO;
import cl.interac.entidades.Ubicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import javax.transaction.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by luis on 27-04-2015.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaUbicacion {
   @Autowired
    private UbicacionDAO ubicacionDAO;

    @org.springframework.transaction.annotation.Transactional(readOnly = false)
    public void guardar(Ubicacion ubicacion){ubicacionDAO.guardar(ubicacion);}

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Ubicacion> obtenerTodas(){return ubicacionDAO.ObtenerTodos();}





}
