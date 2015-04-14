/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "venta_medio_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaMedioPago.findAll", query = "SELECT v FROM VentaMedioPago v"),
    @NamedQuery(name = "VentaMedioPago.findByIdVentaMedioDePago", query = "SELECT v FROM VentaMedioPago v WHERE v.idVentaMedioDePago = :idVentaMedioDePago"),
    @NamedQuery(name = "VentaMedioPago.findByMontoVentaMedioPago", query = "SELECT v FROM VentaMedioPago v WHERE v.montoVentaMedioPago = :montoVentaMedioPago")})
public class VentaMedioPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_venta_medio_de_pago")
    private Integer idVentaMedioDePago;
    @Column(name = "monto_venta_medio_pago")
    private Integer montoVentaMedioPago;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false)
    private Venta idVenta;
    @JoinColumn(name = "id_mediopago", referencedColumnName = "id_mediopago")
    @ManyToOne(optional = false)
    private MedioPago idMediopago;

    public VentaMedioPago() {
    }

    public VentaMedioPago(Integer idVentaMedioDePago) {
        this.idVentaMedioDePago = idVentaMedioDePago;
    }

    public Integer getIdVentaMedioDePago() {
        return idVentaMedioDePago;
    }

    public void setIdVentaMedioDePago(Integer idVentaMedioDePago) {
        this.idVentaMedioDePago = idVentaMedioDePago;
    }

    public Integer getMontoVentaMedioPago() {
        return montoVentaMedioPago;
    }

    public void setMontoVentaMedioPago(Integer montoVentaMedioPago) {
        this.montoVentaMedioPago = montoVentaMedioPago;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }

    public MedioPago getIdMediopago() {
        return idMediopago;
    }

    public void setIdMediopago(MedioPago idMediopago) {
        this.idMediopago = idMediopago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVentaMedioDePago != null ? idVentaMedioDePago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaMedioPago)) {
            return false;
        }
        VentaMedioPago other = (VentaMedioPago) object;
        if ((this.idVentaMedioDePago == null && other.idVentaMedioDePago != null) || (this.idVentaMedioDePago != null && !this.idVentaMedioDePago.equals(other.idVentaMedioDePago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.VentaMedioPago[ idVentaMedioDePago=" + idVentaMedioDePago + " ]";
    }
    
}
