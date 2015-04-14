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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matias Harding
 */
@Entity
@Table(name = "estado_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoVenta.findAll", query = "SELECT e FROM EstadoVenta e"),
    @NamedQuery(name = "EstadoVenta.findByIdEstadoventa", query = "SELECT e FROM EstadoVenta e WHERE e.idEstadoventa = :idEstadoventa"),
    @NamedQuery(name = "EstadoVenta.findByNombreEstadoventa", query = "SELECT e FROM EstadoVenta e WHERE e.nombreEstadoventa = :nombreEstadoventa"),
    @NamedQuery(name = "EstadoVenta.findByDescripcionEstadoventa", query = "SELECT e FROM EstadoVenta e WHERE e.descripcionEstadoventa = :descripcionEstadoventa"),
    @NamedQuery(name = "EstadoVenta.findByActivoEstadoventa", query = "SELECT e FROM EstadoVenta e WHERE e.activoEstadoventa = :activoEstadoventa")})
public class EstadoVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estadoventa")
    private Integer idEstadoventa;
    @Size(max = 50)
    @Column(name = "nombre_estadoventa")
    private String nombreEstadoventa;
    @Size(max = 100)
    @Column(name = "descripcion_estadoventa")
    private String descripcionEstadoventa;
    @Column(name = "activo_estadoventa")
    private Integer activoEstadoventa;

    public EstadoVenta() {
    }

    public EstadoVenta(Integer idEstadoventa) {
        this.idEstadoventa = idEstadoventa;
    }

    public Integer getIdEstadoventa() {
        return idEstadoventa;
    }

    public void setIdEstadoventa(Integer idEstadoventa) {
        this.idEstadoventa = idEstadoventa;
    }

    public String getNombreEstadoventa() {
        return nombreEstadoventa;
    }

    public void setNombreEstadoventa(String nombreEstadoventa) {
        this.nombreEstadoventa = nombreEstadoventa;
    }

    public String getDescripcionEstadoventa() {
        return descripcionEstadoventa;
    }

    public void setDescripcionEstadoventa(String descripcionEstadoventa) {
        this.descripcionEstadoventa = descripcionEstadoventa;
    }

    public Integer getActivoEstadoventa() {
        return activoEstadoventa;
    }

    public void setActivoEstadoventa(Integer activoEstadoventa) {
        this.activoEstadoventa = activoEstadoventa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoventa != null ? idEstadoventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoVenta)) {
            return false;
        }
        EstadoVenta other = (EstadoVenta) object;
        if ((this.idEstadoventa == null && other.idEstadoventa != null) || (this.idEstadoventa != null && !this.idEstadoventa.equals(other.idEstadoventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.EstadoVenta[ idEstadoventa=" + idEstadoventa + " ]";
    }
    
}
