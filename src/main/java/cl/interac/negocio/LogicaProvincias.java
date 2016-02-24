package cl.interac.negocio;

import cl.interac.dao.ProvinciasDAO;
import cl.interac.entidades.Provincias;
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
public class LogicaProvincias {
   @Autowired
    private ProvinciasDAO provinciasDAO;

    @Transactional(readOnly = false)
    public void guardar(Provincias provincias){provinciasDAO.guardar(provincias);}

    @Transactional(readOnly = true)
    public List<Provincias> obtenerTodas(){return provinciasDAO.ObtenerTodos();}

 @Transactional(readOnly = true)
 public List<Provincias> obtenerConRealacion(Integer region_id){return provinciasDAO.obtenerConRelacion(region_id);}





}
