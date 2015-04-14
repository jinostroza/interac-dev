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
@Table(name = "estado_contacto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoContacto.findAll", query = "SELECT e FROM EstadoContacto e"),
    @NamedQuery(name = "EstadoContacto.findByIdEstadoContacto", query = "SELECT e FROM EstadoContacto e WHERE e.idEstadoContacto = :idEstadoContacto"),
    @NamedQuery(name = "EstadoContacto.findByNombreEc", query = "SELECT e FROM EstadoContacto e WHERE e.nombreEc = :nombreEc"),
    @NamedQuery(name = "EstadoContacto.findByActivo", query = "SELECT e FROM EstadoContacto e WHERE e.activo = :activo")})
public class EstadoContacto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_contacto")
    private Integer idEstadoContacto;
    @Size(max = 2147483647)
    @Column(name = "nombre_ec")
    private String nombreEc;
    @Column(name = "activo")
    private Integer activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoContacto")
    private Collection<ContactoCliApptec> contactoCliApptecCollection;

    public EstadoContacto() {
    }

    public EstadoContacto(Integer idEstadoContacto) {
        this.idEstadoContacto = idEstadoContacto;
    }

    public Integer getIdEstadoContacto() {
        return idEstadoContacto;
    }

    public void setIdEstadoContacto(Integer idEstadoContacto) {
        this.idEstadoContacto = idEstadoContacto;
    }

    public String getNombreEc() {
        return nombreEc;
    }

    public void setNombreEc(String nombreEc) {
        this.nombreEc = nombreEc;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<ContactoCliApptec> getContactoCliApptecCollection() {
        return contactoCliApptecCollection;
    }

    public void setContactoCliApptecCollection(Collection<ContactoCliApptec> contactoCliApptecCollection) {
        this.contactoCliApptecCollection = contactoCliApptecCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoContacto != null ? idEstadoContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoContacto)) {
            return false;
        }
        EstadoContacto other = (EstadoContacto) object;
        if ((this.idEstadoContacto == null && other.idEstadoContacto != null) || (this.idEstadoContacto != null && !this.idEstadoContacto.equals(other.idEstadoContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.EstadoContacto[ idEstadoContacto=" + idEstadoContacto + " ]";
    }
    
}
