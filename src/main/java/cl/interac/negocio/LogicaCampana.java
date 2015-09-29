/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.negocio;


import cl.interac.dao.CampanaDAO;
import cl.interac.entidades.Campana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaCampana {
    @Autowired
    private CampanaDAO campanaDAO;


    @Transactional(readOnly = true)
    public List<Campana> obtenerTodos() {return campanaDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarCampana(Campana campana) {
        campanaDAO.guardar(campana);
    }

   @Transactional(readOnly = false)
    public void eliminarCampana(Campana campana){
       campanaDAO.eliminarCampana(campana);
   }

   @Transactional(readOnly = true)
    public List<Campana> obtenerPorUsuario(String user){
       return  campanaDAO.obtenerCampanaPorUsuario(user);
   }
    @Transactional(readOnly = true)
    public List<Campana> obtenerTodoLosContenidos(){
        return campanaDAO.obtenerTodasLosContenidos();
    }

    @Transactional(readOnly = true)
    public List<Campana> obtenerCampanaPorTotem(int totem){
        return campanaDAO.obtenerCampanaPorTotem(totem);}
}