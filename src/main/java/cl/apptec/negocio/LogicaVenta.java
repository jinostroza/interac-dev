/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.StockDao;
import cl.apptec.dao.VentaDao;
import cl.apptec.entidades.DetalleVenta;
import cl.apptec.entidades.Producto;
import cl.apptec.entidades.Stock;
import cl.apptec.entidades.Sucursal;
import cl.apptec.entidades.Venta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author secabezas
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogicaVenta {

    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private StockDao stockDao;
    private List<Stock> stockPorProducto;

    @Transactional(readOnly = true)
    public List<Venta> obtenerTodos() {
        return ventaDao.obtenerTodos();
    }

    @Transactional(readOnly = false)
    public void guardarVenta(Venta v) {
        
        Sucursal s = v.getIdSucursal();
        for (DetalleVenta dv : v.getDetalleVentaList()) {
            Producto p = dv.getIdProducto();
            stockPorProducto = stockDao.obtenerStockPorProductoSucursal(p, s);
            int comprados = dv.getCantidadDetalleVenta();
            List<Stock> stockPorLimpiar =null;

            for (Stock sp : stockPorProducto) {
                if (sp.getCantidadStock() < comprados) {
                    comprados -= sp.getCantidadStock();
                    stockPorLimpiar.add(sp);
                }
                if (sp.getCantidadStock() > comprados) {
                    int restante = sp.getCantidadStock()-comprados;
                    sp.setCantidadStock(restante);
                    comprados = 0;
                }
                if (sp.getCantidadStock() == comprados) {
                    stockPorLimpiar.add(sp);
                    comprados = 0;
                }
                if (comprados == 0) {
                    break;
                }
            }
            
        }
        ventaDao.guardar(v);

    }

    @Transactional(readOnly = false)
    public void eliminarVenta(Venta v) {
        ventaDao.eliminarVenta(v);
    }
}
