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
@Table(name = "cambio_moneda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CambioMoneda.findAll", query = "SELECT c FROM CambioMoneda c"),
    @NamedQuery(name = "CambioMoneda.findByIdCambiomoneda", query = "SELECT c FROM CambioMoneda c WHERE c.idCambiomoneda = :idCambiomoneda"),
    @NamedQuery(name = "CambioMoneda.findByMonedaLocalCambiomoneda", query = "SELECT c FROM CambioMoneda c WHERE c.monedaLocalCambiomoneda = :monedaLocalCambiomoneda"),
    @NamedQuery(name = "CambioMoneda.findByFechaCambiomoneda", query = "SELECT c FROM CambioMoneda c WHERE c.fechaCambiomoneda = :fechaCambiomoneda"),
    @NamedQuery(name = "CambioMoneda.findByFactorCambiomoneda", query = "SELECT c FROM CambioMoneda c WHERE c.factorCambiomoneda = :factorCambiomoneda")})
public class CambioMoneda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cambiomoneda")
    private Integer idCambiomoneda;
    @Column(name = "moneda_local_cambiomoneda")
    private BigInteger monedaLocalCambiomoneda;
    @Column(name = "fecha_cambiomoneda")
    @Temporal(TemporalType.DATE)
    private Date fechaCambiomoneda;
    @Column(name = "factor_cambiomoneda")
    private BigInteger factorCambiomoneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCambiomoneda")
    private List<Venta> ventaList;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    @JoinColumn(name = "id_moneda", referencedColumnName = "id_moneda")
    @ManyToOne(optional = false)
    private Moneda idMoneda;

    public CambioMoneda() {
    }

    public CambioMoneda(Integer idCambiomoneda) {
        this.idCambiomoneda = idCambiomoneda;
    }

    public Integer getIdCambiomoneda() {
        return idCambiomoneda;
    }

    public void setIdCambiomoneda(Integer idCambiomoneda) {
        this.idCambiomoneda = idCambiomoneda;
    }

    public BigInteger getMonedaLocalCambiomoneda() {
        return monedaLocalCambiomoneda;
    }

    public void setMonedaLocalCambiomoneda(BigInteger monedaLocalCambiomoneda) {
        this.monedaLocalCambiomoneda = monedaLocalCambiomoneda;
    }

    public Date getFechaCambiomoneda() {
        return fechaCambiomoneda;
    }

    public void setFechaCambiomoneda(Date fechaCambiomoneda) {
        this.fechaCambiomoneda = fechaCambiomoneda;
    }

    public BigInteger getFactorCambiomoneda() {
        return factorCambiomoneda;
    }

    public void setFactorCambiomoneda(BigInteger factorCambiomoneda) {
        this.factorCambiomoneda = factorCambiomoneda;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Moneda getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Moneda idMoneda) {
        this.idMoneda = idMoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCambiomoneda != null ? idCambiomoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CambioMoneda)) {
            return false;
        }
        CambioMoneda other = (CambioMoneda) object;
        if ((this.idCambiomoneda == null && other.idCambiomoneda != null) || (this.idCambiomoneda != null && !this.idCambiomoneda.equals(other.idCambiomoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.CambioMoneda[ idCambiomoneda=" + idCambiomoneda + " ]";
    }
    
}
