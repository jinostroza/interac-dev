package cl.apptec.negocio;

import cl.apptec.dao.PlanClienteDao;

import java.util.List;

import cl.apptec.entidades.PlanCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author secabezas
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaPlanCliente {
    @Autowired
    private PlanClienteDao planClienteDao;
    
    @Transactional(readOnly = true)
    public List<PlanCliente> obtenerTodos() {
        return planClienteDao.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarPlanCliente(PlanCliente p) {
        planClienteDao.guardar(p);
    }

    @Transactional(readOnly = false)
    public void eliminarPlanCliente(PlanCliente p) {
        planClienteDao.eliminarPlanCliente(p);
    }

    @Transactional(readOnly = true)
    public boolean existePlanCliente(String nombrePlanCliente) {
        return planClienteDao.existePlanCliente(nombrePlanCliente); // data access object
    }
}
