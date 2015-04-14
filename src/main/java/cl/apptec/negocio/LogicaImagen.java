/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.ImagenDao;
import cl.apptec.entidades.Imagen;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matias Harding
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaImagen {
    @Autowired
     private ImagenDao imagenDao;
     
     @Transactional(readOnly = true)
     public List<Imagen> obtenerTodos() {
        return imagenDao.obtenerTodos();
    }
     @Transactional(readOnly = false)
    public void guardarImagen(Imagen im) {
        imagenDao.guardar(im);
    }

    @Transactional(readOnly = false)
    public void eliminarImagen(Imagen im) {
        imagenDao.eliminarImagen(im);
    }
}
