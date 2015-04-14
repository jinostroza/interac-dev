/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author apptec
 */
@Entity
@Table(name = "comuna")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Comuna.findAll", query = "SELECT c FROM Comuna c"),
        @NamedQuery(name = "Comuna.findByIdComuna", query = "SELECT c FROM Comuna c WHERE c.idComuna = :idComuna"),
        @NamedQuery(name = "Comuna.findByNombreComuna", query = "SELECT c FROM Comuna c WHERE c.nombreComuna = :nombreComuna"),
        @NamedQuery(name = "Comuna.findByActivoComuna", query = "SELECT c FROM Comuna c WHERE c.activoComuna = :activoComuna"),
        @NamedQuery(name = "Comuna.findByCiudad", query = "SELECT c FROM Comuna c WHERE c.idCiudad = :ciudad")
})
public class Comuna implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComuna")
    private Collection<CliEmpresa> cliEmpresaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComuna")
    private Collection<CliPersona> cliPersonaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comuna")
    private Integer idComuna;
    @Size(max = 50)
    @Column(name = "nombre_comuna")
    private String nombreComuna;
    @Column(name = "activo_comuna")
    private Integer activoComuna;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComuna")
    private List<Sucursal> sucursalList;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne(optional = false)
    private Ciudad idCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComuna")
    private List<ClienteApptec> clienteApptecList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComuna")
    private List<Proveedor> proveedorList;

    public Comuna() {
    }

    public Comuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public Integer getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public Integer getActivoComuna() {
        return activoComuna;
    }

    public void setActivoComuna(Integer activoComuna) {
        this.activoComuna = activoComuna;
    }

    @XmlTransient
    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    @XmlTransient
    public List<ClienteApptec> getClienteApptecList() {
        return clienteApptecList;
    }

    public void setClienteApptecList(List<ClienteApptec> clienteApptecList) {
        this.clienteApptecList = clienteApptecList;
    }

    @XmlTransient
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComuna != null ? idComuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comuna)) {
            return false;
        }
        Comuna other = (Comuna) object;
        if ((this.idComuna == null && other.idComuna != null) || (this.idComuna != null && !this.idComuna.equals(other.idComuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Comuna[ idComuna=" + idComuna + " ]";
    }

    @XmlTransient
    public Collection<CliEmpresa> getCliEmpresaCollection() {
        return cliEmpresaCollection;
    }

    public void setCliEmpresaCollection(Collection<CliEmpresa> cliEmpresaCollection) {
        this.cliEmpresaCollection = cliEmpresaCollection;
    }

    @XmlTransient
    public Collection<CliPersona> getCliPersonaCollection() {
        return cliPersonaCollection;
    }

    public void setCliPersonaCollection(Collection<CliPersona> cliPersonaCollection) {
        this.cliPersonaCollection = cliPersonaCollection;
    }

}
