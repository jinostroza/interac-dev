/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByIdVenta", query = "SELECT v FROM Venta v WHERE v.idVenta = :idVenta"),
    @NamedQuery(name = "Venta.findByFactorCambioMoneda", query = "SELECT v FROM Venta v WHERE v.factorCambioMoneda = :factorCambioMoneda"),
    @NamedQuery(name = "Venta.findByFechaVenta", query = "SELECT v FROM Venta v WHERE v.fechaVenta = :fechaVenta"),
    @NamedQuery(name = "Venta.findByValorVentaMonedaLocal", query = "SELECT v FROM Venta v WHERE v.valorVentaMonedaLocal = :valorVentaMonedaLocal"),
    @NamedQuery(name = "Venta.findByValorVentaMonedaExterior", query = "SELECT v FROM Venta v WHERE v.valorVentaMonedaExterior = :valorVentaMonedaExterior")})
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_venta")
    private Integer idVenta;
    @Column(name = "factor_cambio_moneda")
    private BigInteger factorCambioMoneda;
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    @Column(name = "valor_venta_moneda_local")
    private BigInteger valorVentaMonedaLocal;
    @Column(name = "valor_venta_moneda_exterior")
    private BigInteger valorVentaMonedaExterior;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private List<DetalleVenta> detalleVentaList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private Sucursal idSucursal;
    @JoinColumn(name = "id_moneda", referencedColumnName = "id_moneda")
    @ManyToOne(optional = false)
    private Moneda idMoneda;
    @JoinColumn(name = "id_estadoventa", referencedColumnName = "id_estadoventa")
    @ManyToOne(optional = false)
    private EstadoVenta idEstadoventa;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "id_categoriaventa", referencedColumnName = "id_categoriaventa")
    @ManyToOne(optional = false)
    private CategoriaVenta idCategoriaventa;
    @JoinColumn(name = "id_cambiomoneda", referencedColumnName = "id_cambiomoneda")
    @ManyToOne(optional = false)
    private CambioMoneda idCambiomoneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private List<VentaMedioPago> ventaMedioPagoList;

    public Venta() {
    }

    public Venta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public BigInteger getFactorCambioMoneda() {
        return factorCambioMoneda;
    }

    public void setFactorCambioMoneda(BigInteger factorCambioMoneda) {
        this.factorCambioMoneda = factorCambioMoneda;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigInteger getValorVentaMonedaLocal() {
        return valorVentaMonedaLocal;
    }

    public void setValorVentaMonedaLocal(BigInteger valorVentaMonedaLocal) {
        this.valorVentaMonedaLocal = valorVentaMonedaLocal;
    }

    public BigInteger getValorVentaMonedaExterior() {
        return valorVentaMonedaExterior;
    }

    public void setValorVentaMonedaExterior(BigInteger valorVentaMonedaExterior) {
        this.valorVentaMonedaExterior = valorVentaMonedaExterior;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Moneda getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Moneda idMoneda) {
        this.idMoneda = idMoneda;
    }

    public EstadoVenta getIdEstadoventa() {
        return idEstadoventa;
    }

    public void setIdEstadoventa(EstadoVenta idEstadoventa) {
        this.idEstadoventa = idEstadoventa;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public CategoriaVenta getIdCategoriaventa() {
        return idCategoriaventa;
    }

    public void setIdCategoriaventa(CategoriaVenta idCategoriaventa) {
        this.idCategoriaventa = idCategoriaventa;
    }

    public CambioMoneda getIdCambiomoneda() {
        return idCambiomoneda;
    }

    public void setIdCambiomoneda(CambioMoneda idCambiomoneda) {
        this.idCambiomoneda = idCambiomoneda;
    }

    @XmlTransient
    public List<VentaMedioPago> getVentaMedioPagoList() {
        return ventaMedioPagoList;
    }

    public void setVentaMedioPagoList(List<VentaMedioPago> ventaMedioPagoList) {
        this.ventaMedioPagoList = ventaMedioPagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Venta[ idVenta=" + idVenta + " ]";
    }
    
}
