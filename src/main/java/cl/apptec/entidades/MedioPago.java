/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "medio_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedioPago.findAll", query = "SELECT m FROM MedioPago m"),
    @NamedQuery(name = "MedioPago.findByIdMediopago", query = "SELECT m FROM MedioPago m WHERE m.idMediopago = :idMediopago"),
    @NamedQuery(name = "MedioPago.findByNombreMediopago", query = "SELECT m FROM MedioPago m WHERE m.nombreMediopago = :nombreMediopago"),
    @NamedQuery(name = "MedioPago.findByDescripcionMediopago", query = "SELECT m FROM MedioPago m WHERE m.descripcionMediopago = :descripcionMediopago"),
    @NamedQuery(name = "MedioPago.findByActivoMediopago", query = "SELECT m FROM MedioPago m WHERE m.activoMediopago = :activoMediopago")})
public class MedioPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mediopago")
    private Integer idMediopago;
    @Size(max = 50)
    @Column(name = "nombre_mediopago")
    private String nombreMediopago;
    @Size(max = 100)
    @Column(name = "descripcion_mediopago")
    private String descripcionMediopago;
    @Column(name = "activo_mediopago")
    private Integer activoMediopago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMediopago")
    private List<VentaMedioPago> ventaMedioPagoList;
    @OneToMany(mappedBy = "medioPago")
    private List<Compra> compraList;

    public MedioPago() {
    }

    public MedioPago(Integer idMediopago) {
        this.idMediopago = idMediopago;
    }

    public Integer getIdMediopago() {
        return idMediopago;
    }

    public void setIdMediopago(Integer idMediopago) {
        this.idMediopago = idMediopago;
    }

    public String getNombreMediopago() {
        return nombreMediopago;
    }

    public void setNombreMediopago(String nombreMediopago) {
        this.nombreMediopago = nombreMediopago;
    }

    public String getDescripcionMediopago() {
        return descripcionMediopago;
    }

    public void setDescripcionMediopago(String descripcionMediopago) {
        this.descripcionMediopago = descripcionMediopago;
    }

    public Integer getActivoMediopago() {
        return activoMediopago;
    }

    public void setActivoMediopago(Integer activoMediopago) {
        this.activoMediopago = activoMediopago;
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
        hash += (idMediopago != null ? idMediopago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedioPago)) {
            return false;
        }
        MedioPago other = (MedioPago) object;
        if ((this.idMediopago == null && other.idMediopago != null) || (this.idMediopago != null && !this.idMediopago.equals(other.idMediopago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.MedioPago[ idMediopago=" + idMediopago + " ]";
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }
    
}
