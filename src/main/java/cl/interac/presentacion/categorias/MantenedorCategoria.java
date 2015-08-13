package cl.interac.presentacion.categorias;

import cl.interac.entidades.Categoria;
import cl.interac.negocio.LogicaCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cl.interac.util.components.FacesUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("prototype")
public class MantenedorCategoria implements Serializable
{
    public enum TipoOperacion {
        INSERTAR,
        EDITAR,
    }
    //manejo manual
    private TipoOperacion operacion;
    public Categoria categoria;
    @Autowired
    private LogicaCategoria logicaCategoria;

    private List<Categoria> categorias;

    public boolean esEditar() {
        return operacion == TipoOperacion.EDITAR;
    }

    public boolean esAgregar() {
        return operacion == TipoOperacion.INSERTAR;
    }


    public void inicio() {
        categorias = logicaCategoria.obtenerTodos();
        categoria = new Categoria();
    }
    public void eliminar(Categoria categoria){
        logicaCategoria.eliminar(categoria);
    }

    public void agregaCategoria() {
        operacion = TipoOperacion.INSERTAR;
        logicaCategoria.guardar(categoria);
        categorias = logicaCategoria.obtenerTodos();

        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado la Categoria [" + categoria.getDesccategoria() + "]");
    }

    public void editarCategoria(Categoria c) {
        operacion = TipoOperacion.EDITAR;
        categoria = c;
        logicaCategoria.guardar(categoria);
        categorias = logicaCategoria.obtenerTodos();

        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado la Categoria [" + categoria.getDesccategoria() + "]");
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
    public TipoOperacion getOperacion() {
        return operacion;
    }

    public void setOperacion(TipoOperacion Operacion) {
        operacion = Operacion;
    }

}
