/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Collection;
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
 * @author Matias Harding
 */
@Entity
@Table(name = "estado_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCliente.findAll", query = "SELECT e FROM EstadoCliente e"),
    @NamedQuery(name = "EstadoCliente.findByIdEstadoCliente", query = "SELECT e FROM EstadoCliente e WHERE e.idEstadoCliente = :idEstadoCliente"),
    @NamedQuery(name = "EstadoCliente.findByNombreEstadoCliente", query = "SELECT e FROM EstadoCliente e WHERE e.nombreEstadoCliente = :nombreEstadoCliente"),
    @NamedQuery(name = "EstadoCliente.findByDescripcionEstadoCliente", query = "SELECT e FROM EstadoCliente e WHERE e.descripcionEstadoCliente = :descripcionEstadoCliente"),
    @NamedQuery(name = "EstadoCliente.findByActivoEstadoCliente", query = "SELECT e FROM EstadoCliente e WHERE e.activoEstadoCliente = :activoEstadoCliente")})
public class EstadoCliente implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCliente")
    private Collection<CliEmpresa> cliEmpresaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_cliente")
    private Integer idEstadoCliente;
    @Size(max = 50)
    @Column(name = "nombre_estado_cliente")
    private String nombreEstadoCliente;
    @Size(max = 100)
    @Column(name = "descripcion_estado_cliente")
    private String descripcionEstadoCliente;
    @Column(name = "activo_estado_cliente")
    private Boolean activoEstadoCliente;

    public EstadoCliente() {
    }

    public EstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public Integer getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public String getNombreEstadoCliente() {
        return nombreEstadoCliente;
    }

    public void setNombreEstadoCliente(String nombreEstadoCliente) {
        this.nombreEstadoCliente = nombreEstadoCliente;
    }

    public String getDescripcionEstadoCliente() {
        return descripcionEstadoCliente;
    }

    public void setDescripcionEstadoCliente(String descripcionEstadoCliente) {
        this.descripcionEstadoCliente = descripcionEstadoCliente;
    }

    public Boolean getActivoEstadoCliente() {
        return activoEstadoCliente;
    }

    public void setActivoEstadoCliente(Boolean activoEstadoCliente) {
        this.activoEstadoCliente = activoEstadoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCliente != null ? idEstadoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCliente)) {
            return false;
        }
        EstadoCliente other = (EstadoCliente) object;
        if ((this.idEstadoCliente == null && other.idEstadoCliente != null) || (this.idEstadoCliente != null && !this.idEstadoCliente.equals(other.idEstadoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.EstadoCliente[ idEstadoCliente=" + idEstadoCliente + " ]";
    }

    @XmlTransient
    public Collection<CliEmpresa> getCliEmpresaCollection() {
        return cliEmpresaCollection;
    }

    public void setCliEmpresaCollection(Collection<CliEmpresa> cliEmpresaCollection) {
        this.cliEmpresaCollection = cliEmpresaCollection;
    }
    
}
