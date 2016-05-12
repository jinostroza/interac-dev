/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.ContratoDAO;
import cl.interac.entidades.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaContrato {

    @Autowired
    private ContratoDAO contratoDAO;

    @Transactional(readOnly = true)
    public List<Contrato> obtenerTodos() {
        return contratoDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardar(Contrato contrato) {
        contratoDAO.guardar(contrato);
    }

    @Transactional(readOnly = false)
    public void eliminar(Contrato contrato) {
        contratoDAO.eliminar(contrato);
    }


}