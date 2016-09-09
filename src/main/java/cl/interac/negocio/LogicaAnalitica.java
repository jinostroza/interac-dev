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

import java.util.Date;
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
    public List<Analitica> totemA(Integer idtotem, Date fec_ini,Date fec_end) {
        return analiticaDAO.totemA(idtotem,fec_ini,fec_end);
    }
    @Transactional(readOnly = true)
    public List<Analitica> contenidoA(String path, Date fec_ini,Date fec_end) {
        return analiticaDAO.contenidoA(path,fec_ini,fec_end);
    }
    @Transactional(readOnly = true)
    public List<Analitica> contotem(Integer idtotem,String path, Date fec_ini,Date fec_end) {
        return analiticaDAO.conTotem(idtotem,path,fec_ini,fec_end);
    }






}