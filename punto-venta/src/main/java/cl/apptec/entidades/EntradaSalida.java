/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "entrada_salida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaSalida.findAll", query = "SELECT e FROM EntradaSalida e"),
    @NamedQuery(name = "EntradaSalida.findByIdEntradaSalida", query = "SELECT e FROM EntradaSalida e WHERE e.idEntradaSalida = :idEntradaSalida"),
    @NamedQuery(name = "EntradaSalida.findByIdProducto", query = "SELECT e FROM EntradaSalida e WHERE e.idProducto = :idProducto")})
public class EntradaSalida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada_salida")
    private Integer idEntradaSalida;
    @OneToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto idProducto;
    @JoinColumn(name = "id_tipo_entrada_salida", referencedColumnName = "id_tipo_entrada_salida")
    @ManyToOne(optional = false)
    private TipoEntradaSalida idTipoEntradaSalida;
    @JoinColumn(name = "id_stock", referencedColumnName = "id_stock")
    @ManyToOne(optional = false)
    private Stock idStock;

    public EntradaSalida() {
    }

    public EntradaSalida(Integer idEntradaSalida) {
        this.idEntradaSalida = idEntradaSalida;
    }

    public Integer getIdEntradaSalida() {
        return idEntradaSalida;
    }

    public void setIdEntradaSalida(Integer idEntradaSalida) {
        this.idEntradaSalida = idEntradaSalida;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public TipoEntradaSalida getIdTipoEntradaSalida() {
        return idTipoEntradaSalida;
    }

    public void setIdTipoEntradaSalida(TipoEntradaSalida idTipoEntradaSalida) {
        this.idTipoEntradaSalida = idTipoEntradaSalida;
    }

    public Stock getIdStock() {
        return idStock;
    }

    public void setIdStock(Stock idStock) {
        this.idStock = idStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntradaSalida != null ? idEntradaSalida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaSalida)) {
            return false;
        }
        EntradaSalida other = (EntradaSalida) object;
        if ((this.idEntradaSalida == null && other.idEntradaSalida != null) || (this.idEntradaSalida != null && !this.idEntradaSalida.equals(other.idEntradaSalida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.EntradaSalida[ idEntradaSalida=" + idEntradaSalida + " ]";
    }
    
}
