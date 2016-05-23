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

    @Transactional(readOnly = true)
    public List<Analitica> obtenerTodosGenero() {
        return analiticaDAO.obtenerTodosGenero();
    }
    @Transactional(readOnly = true)
    public Long audiencia() {
        return analiticaDAO.audiencia();
    }
    @Transactional(readOnly = true)
    public Long countHombres() {
        return analiticaDAO.countHombres();
    }

    @Transactional(readOnly = true)
    public Long countMujeres() {
        return analiticaDAO.countMujeres();
    }
    @Transactional(readOnly = true)
    public Long seg1() {
        return analiticaDAO.seg1();
    }
    @Transactional(readOnly = true)
    public Long seg2() {
        return analiticaDAO.seg2();
    }
    @Transactional(readOnly = true)
    public Long seg3() {
        return analiticaDAO.seg3();
    }
    @Transactional(readOnly = true)
    public Long seg4() {
        return analiticaDAO.seg4();
    }
    @Transactional(readOnly = true)
    public Long seg5() {
        return analiticaDAO.seg5();
    }
    @Transactional(readOnly = true)
    public Long seg6() {
        return analiticaDAO.seg6();
    }
    @Transactional(readOnly = true)
    public Long seg7() {
        return analiticaDAO.seg7();
    }





}