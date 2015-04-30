package cl.interac.negocio;

import cl.interac.dao.CategoriaDAO;
import cl.interac.entidades.Campana;
import cl.interac.entidades.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luis on 29-04-2015.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaCatergoria {

    private List<Categoria> categoria;

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Transactional(readOnly = true)
    public List<Categoria> obtenerTodos() {return categoriaDAO.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarCategoria(Categoria g) {
        categoriaDAO.guardar(g);
    }

    @Transactional(readOnly = false)
    public void eliminarCategoria(Categoria g) { categoriaDAO.eliminarCategoria(g);}

}
