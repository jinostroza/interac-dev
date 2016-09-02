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
   /* @Transactional(readOnly = true)
    public Long audiencia() {
        return analiticaDAO.audiencia();
    }*/

    @Transactional(readOnly = false)
    public void guardar(Analitica analitica) {
        analiticaDAO.guardar(analitica);
    }

    @Transactional(readOnly = true)
    public Long countHombres(Integer idtotem) {
        return analiticaDAO.countHombres(idtotem);
    }

    @Transactional(readOnly = true)
    public Long countMujeres(Integer idtotem) {
        return analiticaDAO.countMujeres(idtotem);
    }
    @Transactional(readOnly = true)
    public Long seg1(Integer idtotem) {
        return analiticaDAO.seg1(idtotem);
    }
    @Transactional(readOnly = true)
    public Long seg2(Integer idtotem) {
        return analiticaDAO.seg2(idtotem);
    }
    @Transactional(readOnly = true)
    public Long seg3(Integer idtotem) {
        return analiticaDAO.seg3(idtotem);
    }
    @Transactional(readOnly = true)
    public Long seg4(Integer idtotem) {
        return analiticaDAO.seg4(idtotem);
    }
    @Transactional(readOnly = true)
    public Long seg5(Integer idtotem) {
        return analiticaDAO.seg5(idtotem);
    }
    @Transactional(readOnly = true)
    public Long seg6(Integer idtotem) {
        return analiticaDAO.seg6(idtotem);
    }
    @Transactional(readOnly = true)
    public Long seg7(Integer idtotem) {
        return analiticaDAO.seg7(idtotem);
    }
    @Transactional(readOnly = true)
    public Long feliz(Integer idtotem) {
        return analiticaDAO.feliz(idtotem);
    }
    @Transactional(readOnly = true)
    public Long triste(Integer idtotem) {
        return analiticaDAO.triste(idtotem);
    }
    @Transactional(readOnly = true)
    public Long neutral(Integer idtotem) {
        return analiticaDAO.neutral(idtotem);
    }
    @Transactional(readOnly = true)
    public Long enojado(Integer idtotem) {
        return analiticaDAO.enojado(idtotem);
    }
    @Transactional(readOnly = true)
    public Long sorpresa(Integer idtotem) {
        return analiticaDAO.sorpresa(idtotem);
    }






}