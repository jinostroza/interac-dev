package cl.interac.presentacion.categorias;

import cl.interac.entidades.Categoria;
import cl.interac.negocio.LogicaCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorCategoria implements Serializable
{
    public Categoria categoria;
    @Autowired
    private LogicaCategoria logicaCategoria;

    private List<Categoria> categorias;

    public void inicio() {
        categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LogicaCategoria getLogicaCategoria() {
        return logicaCategoria;
    }

    public void setLogicaCategoria(LogicaCategoria logicaCategoria) {
        this.logicaCategoria = logicaCategoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

}
