/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;

import cl.apptec.entidades.CategoriaVenta;
import cl.apptec.negocio.LogicaCategoriaVenta;
import cl.apptec.util.components.FacesUtil;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author apptec
 */
@Component
@Scope("flow")
public class MantenedorCategoriaVenta implements Serializable {

    private List<CategoriaVenta> categoriasVenta;
    private CategoriaVenta categoriaVentaSeleccionada;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaCategoriaVenta logicaCategoriaVenta;

    public void inicio() {
        categoriasVenta = logicaCategoriaVenta.obtenerTodos();
    }

    public String irAgregar() {
        categoriaVentaSeleccionada = new CategoriaVenta();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(CategoriaVenta c) {
        categoriaVentaSeleccionada = c;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarCV() {
        categoriasVenta.remove(categoriaVentaSeleccionada);
        logicaCategoriaVenta.eliminarCV(categoriaVentaSeleccionada);
    }

    public void guardarCV() {
        logicaCategoriaVenta.guardarCV(categoriaVentaSeleccionada);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha crado la nueva Categoria de venta [ID: " + categoriaVentaSeleccionada.getIdCategoriaventa() + "]");
            categoriasVenta.add(categoriaVentaSeleccionada);
            categoriaVentaSeleccionada = new CategoriaVenta();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ha editado la Categoria de venta [ID: " + categoriaVentaSeleccionada.getIdCategoriaventa() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<CategoriaVenta> getCategoriasVenta() {
        return categoriasVenta;
    }

    public void setCategoriasVenta(List<CategoriaVenta> categoriasVenta) {
        this.categoriasVenta = categoriasVenta;
    }

    public CategoriaVenta getCategoriaVentaSeleccionada() {
        return categoriaVentaSeleccionada;
    }

    public void setCategoriaVentaSeleccionada(CategoriaVenta categoriaVentaSeleccionada) {
        this.categoriaVentaSeleccionada = categoriaVentaSeleccionada;
    }

}
