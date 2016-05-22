/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.AnaliticaDAO;
import cl.interac.entidades.Analitica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaAnalitica {

    @Autowired
    private AnaliticaDAO analiticaDAO;

    @Transactional(readOnly = true)
    public List<Analitica> obtenerTodos() {
        return analiticaDAO.obtenerTodos();
    }




}