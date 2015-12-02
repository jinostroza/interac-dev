package cl.interac.negocio;

import cl.interac.dao.EmpresaDAO;
import cl.interac.entidades.Empresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by PPablo on 01-12-2015.
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaEmpresa {
    @Autowired
    private EmpresaDAO empresaDAO;

    @Transactional(readOnly = true)
    public List<Empresa> obtenerTodos() { return empresaDAO.obtenerTodos(); }

    @Transactional(readOnly = false)
    public void guardar(Empresa emp) { empresaDAO.guardarEmpresa(emp); }

    @Transactional(readOnly = false)
    public void eliminar(Empresa emp) { empresaDAO.eliminarEmpresa(emp); }
}
