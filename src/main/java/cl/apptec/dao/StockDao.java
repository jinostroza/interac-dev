package cl.apptec.dao;

import cl.apptec.entidades.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.apptec.entidades.Stock;
import cl.apptec.entidades.Sucursal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matias Harding
 * 
 */
@Repository
public class StockDao {
    @PersistenceContext
    private EntityManager em;
    
    public List<Stock> obtenerTodos() {
        return em.createNamedQuery("Stock.findAll").getResultList();
    }
    public void guardar(Stock s) {
        if (s.getIdStock() == null) em.persist(s);
        else em.merge(s);
    }

    public void eliminarStock(Stock s) {
        
        Stock stock = em.find(Stock.class, s.getIdStock());
        em.remove(stock);
    }
    public List<Stock> obtenerStockPorProductoSucursal(Producto p,Sucursal s){
        em.createNamedQuery("Stock.findByProductoSucursal").setParameter("producto", p).setParameter("sucursal", s).getResultList();        
        return em.createNamedQuery("Stock.findByProductoSucursal").getResultList();
    }

   
}
