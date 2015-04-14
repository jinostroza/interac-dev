package cl.apptec.presentacion;

import cl.apptec.entidades.CategoriaProducto;
import cl.apptec.negocio.LogicaCategoriaProducto;
import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import cl.apptec.util.components.LanguagePropertiesReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorCategoriaProducto implements Serializable{
    private List<CategoriaProducto> categoriasProductos;
    private CategoriaProducto cpSeleccionado;
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaCategoriaProducto logicaCategoriaProducto;

    @Autowired
    private LanguagePropertiesReader propertiesReader;
    
    public void inicio() {
        categoriasProductos = logicaCategoriaProducto.obtenerTodas();
    }

    // flows
    public String irAgregar() {
        cpSeleccionado = new CategoriaProducto();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(CategoriaProducto cp) {
        cpSeleccionado = cp;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarCategoriaProducto() {
        categoriasProductos.remove(cpSeleccionado);   
        logicaCategoriaProducto.eliminarCategoriaProducto(cpSeleccionado);
    }

    public void guardarCategoriaProducto() {
        if (operacion == TipoOperacion.INGRESAR) {
            if (!logicaCategoriaProducto.existePorNombre(cpSeleccionado.getNombreCategoriaProducto())) {
                logicaCategoriaProducto.guardarCategoriaProducto(cpSeleccionado);
                FacesUtil.mostrarMensajeInformativo(propertiesReader.get("comunes.accionExitosa"),
                        propertiesReader.get("categoriaProducto.agregar.accionExitosa", cpSeleccionado.getIdCategoriaProducto().toString()));
                categoriasProductos.add(cpSeleccionado);
                cpSeleccionado = new CategoriaProducto();
            } else {
                FacesUtil.mostrarMensajeError(propertiesReader.get("comunes.accionFallida"),
                        propertiesReader.get("categoriaProducto.agregar.tipoExiste", cpSeleccionado.getNombreCategoriaProducto()));
            }
        }else{
            logicaCategoriaProducto.guardarCategoriaProducto(cpSeleccionado);
            FacesUtil.mostrarMensajeInformativo(propertiesReader.get("comunes.accionExitosa"),
                    propertiesReader.get("categoriaProducto.editar.accionExitosa", cpSeleccionado.getIdCategoriaProducto().toString()));
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<CategoriaProducto> getCategoriaProducto() {
        return categoriasProductos;
    }

    public void setCategoriaProducto(List<CategoriaProducto> categoriasProductos) {
        this.categoriasProductos = categoriasProductos;
    }

    public CategoriaProducto getCategoriaProductoSeleccionado() {
        return cpSeleccionado;
    }

    public void setCategoriaProductoSeleccionado(CategoriaProducto cpSeleccionado) {
        this.cpSeleccionado = cpSeleccionado;
    }

    public List<CategoriaProducto> getCategoriasProductos() {
        return categoriasProductos;
    }

    public void setCategoriasProductos(List<CategoriaProducto> categoriasProductos) {
        this.categoriasProductos = categoriasProductos;
    }
}
