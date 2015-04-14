package cl.apptec.presentacion;

import cl.apptec.entidades.Stock;
import cl.apptec.negocio.LogicaStock;
import java.io.Serializable;
import java.util.List;

import cl.apptec.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author Matias Harding
 */
@Component
@Scope("flow")
public class MantenedorStock implements Serializable {
    private List<Stock> stocks;
    private Stock stockSeleccionado;
    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    private TipoOperacion operacion;
    
    @Autowired
    private LogicaStock logicaStock;
    
    public void inicio() {
        stocks = logicaStock.obtenerTodos();
    }

    // flows
    public String irAgregar() {
        stockSeleccionado = new Stock();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }
    
    public String irEditar(Stock s) {
        stockSeleccionado = s;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    // logica vista
    public void eliminarStock() {
        logicaStock.eliminarStock(stockSeleccionado);
    }

    public void guardarStock() {
        logicaStock.guardarStock(stockSeleccionado);
        FacesUtil.mostrarMensajeInformativo("Operación exitosa","Se ha creado el nuevo stock [ID: "+stockSeleccionado.getIdStock()+"]");
        stockSeleccionado = new Stock();
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    // getters and setters
    public List<Stock> getStock() {
        return stocks;
    }

    public void setStock(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Stock getStockSeleccionado() {
        return stockSeleccionado;
    }

    public void setStockSeleccionado(Stock stockSeleccionado) {
        this.stockSeleccionado = stockSeleccionado;
    }
}
