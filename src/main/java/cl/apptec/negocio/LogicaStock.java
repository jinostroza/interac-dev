/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.negocio;

import cl.apptec.dao.StockDao;
import cl.apptec.entidades.Stock;
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
public class LogicaStock {
    @Autowired
     private StockDao stockDao;
     
     @Transactional(readOnly = true)
     public List<Stock> obtenerTodos() {
        return stockDao.obtenerTodos();
    }
    
     @Transactional(readOnly = false)
    public void guardarStock(Stock s) {
        stockDao.guardar(s);
    }

    @Transactional(readOnly = false)
    public void eliminarStock(Stock s) {
        stockDao.eliminarStock(s);
    }
}
