/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "estado_cliente_apptec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoClienteApptec.findAll", query = "SELECT e FROM EstadoClienteApptec e"),
    @NamedQuery(name = "EstadoClienteApptec.findByIdEstadoClienteApptec", query = "SELECT e FROM EstadoClienteApptec e WHERE e.idEstadoClienteApptec = :idEstadoClienteApptec"),
    @NamedQuery(name = "EstadoClienteApptec.findByNombre", query = "SELECT e FROM EstadoClienteApptec e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoClienteApptec.findByDescripcion", query = "SELECT e FROM EstadoClienteApptec e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EstadoClienteApptec.findByActivo", query = "SELECT e FROM EstadoClienteApptec e WHERE e.activo = :activo")})
public class EstadoClienteApptec implements Serializable {
    @OneToMany(mappedBy = "idEstadoClienteApptec")
    private Collection<ClienteApptec> clienteApptecCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_cliente_apptec")
    private Integer idEstadoClienteApptec;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "activo")
    private Boolean activo;

    public EstadoClienteApptec() {
    }

    public EstadoClienteApptec(Integer idEstadoClienteApptec) {
        this.idEstadoClienteApptec = idEstadoClienteApptec;
    }

    public Integer getIdEstadoClienteApptec() {
        return idEstadoClienteApptec;
    }

    public void setIdEstadoClienteApptec(Integer idEstadoClienteApptec) {
        this.idEstadoClienteApptec = idEstadoClienteApptec;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoClienteApptec != null ? idEstadoClienteApptec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoClienteApptec)) {
            return false;
        }
        EstadoClienteApptec other = (EstadoClienteApptec) object;
        if ((this.idEstadoClienteApptec == null && other.idEstadoClienteApptec != null) || (this.idEstadoClienteApptec != null && !this.idEstadoClienteApptec.equals(other.idEstadoClienteApptec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.EstadoClienteApptec[ idEstadoClienteApptec=" + idEstadoClienteApptec + " ]";
    }

    @XmlTransient
    public Collection<ClienteApptec> getClienteApptecCollection() {
        return clienteApptecCollection;
    }

    public void setClienteApptecCollection(Collection<ClienteApptec> clienteApptecCollection) {
        this.clienteApptecCollection = clienteApptecCollection;
    }
    
}
