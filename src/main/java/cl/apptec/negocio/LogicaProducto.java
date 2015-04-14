/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.ProductoDao;
import cl.apptec.entidades.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import cl.apptec.util.components.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Matias Harding
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaProducto {
    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private Constantes constantes;

    @Transactional(readOnly = true)
    public List<Producto> obtenerTodos() {
        return productoDao.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarProducto(Producto producto) {
        productoDao.guardar(producto);
    }

    @Transactional(readOnly = false)
    public void guardarProducto(Producto producto, Proveedor proveedor, List<String> pathImagenes) {
        productoDao.guardar(producto);
        boolean existeProveedor = false;
        if (producto.getProductoProveedorList() != null) {
            for (ProductoProveedor pp : producto.getProductoProveedorList()) {
                if (pp.getIdProveedor().equals(proveedor)) {
                    existeProveedor = true;
                    break;
                }
            }
        }
        if (!existeProveedor) {
            if (producto.getProductoProveedorList() == null) producto.setProductoProveedorList(new ArrayList<ProductoProveedor>());
            ProductoProveedor pp = new ProductoProveedor();
            pp.setIdProducto(producto);
            pp.setIdProveedor(proveedor);
            producto.getProductoProveedorList().add(pp);
        }

        if (producto.getImagenes() == null) producto.setImagenes(new ArrayList<Imagen>());
        // movemos de tmp a producto
        for (String pathTmp : pathImagenes) {
            String path = constantes.getPathArchivos() + "/producto/" + producto.getIdProducto() + "/" + pathTmp.substring(pathTmp.lastIndexOf('/') + 1);
            try {
                Files.move(Paths.get(pathTmp), Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Imagen i = new Imagen();
            i.setImagenActiva(true);
            i.setProducto(producto);
            i.setUrlImagen(path);
            producto.getImagenes().add(i);
        }
        productoDao.guardar(producto);
    }

    @Transactional(readOnly = false)
    public void eliminarProducto(Producto p) {
        productoDao.eliminarProducto(p);
    }

    @Transactional(readOnly = true)
    public List<Producto> obtenerPorSucursal(Sucursal idSucursal) {
        return productoDao.obtenerProductosSucursal(idSucursal);
    }

    @Transactional(readOnly = true)
    public Producto obtenerPorId(Integer id) {
        return productoDao.obtenerPorId(id);
    }

    @Transactional(readOnly = true)
    public Producto obtenerConRelaciones(Producto p) {
        return productoDao.obtenerConRelaciones(p);
    }
}
