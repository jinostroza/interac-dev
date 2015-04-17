/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
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
@Table(name = "tipo_fono")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFono.findAll", query = "SELECT t FROM TipoFono t"),
    @NamedQuery(name = "TipoFono.findByIdTipoFono", query = "SELECT t FROM TipoFono t WHERE t.idTipoFono = :idTipoFono"),
    @NamedQuery(name = "TipoFono.findByNombresTipoFono", query = "SELECT t FROM TipoFono t WHERE t.nombresTipoFono = :nombresTipoFono"),
    @NamedQuery(name = "TipoFono.findByDescripcionTipoFono", query = "SELECT t FROM TipoFono t WHERE t.descripcionTipoFono = :descripcionTipoFono"),
    @NamedQuery(name = "TipoFono.findByActivoTipoFono", query = "SELECT t FROM TipoFono t WHERE t.activoTipoFono = :activoTipoFono")})
public class TipoFono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_fono")
    private Integer idTipoFono;
    @Size(max = 50)
    @Column(name = "nombres_tipo_fono")
    private String nombresTipoFono;
    @Size(max = 100)
    @Column(name = "descripcion_tipo_fono")
    private String descripcionTipoFono;
    @Column(name = "activo_tipo_fono")
    private Integer activoTipoFono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoFono")
    private List<Cliente> clienteList;

    public TipoFono() {
    }

    public TipoFono(Integer idTipoFono) {
        this.idTipoFono = idTipoFono;
    }

    public Integer getIdTipoFono() {
        return idTipoFono;
    }

    public void setIdTipoFono(Integer idTipoFono) {
        this.idTipoFono = idTipoFono;
    }

    public String getNombresTipoFono() {
        return nombresTipoFono;
    }

    public void setNombresTipoFono(String nombresTipoFono) {
        this.nombresTipoFono = nombresTipoFono;
    }

    public String getDescripcionTipoFono() {
        return descripcionTipoFono;
    }

    public void setDescripcionTipoFono(String descripcionTipoFono) {
        this.descripcionTipoFono = descripcionTipoFono;
    }

    public Integer getActivoTipoFono() {
        return activoTipoFono;
    }

    public void setActivoTipoFono(Integer activoTipoFono) {
        this.activoTipoFono = activoTipoFono;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoFono != null ? idTipoFono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFono)) {
            return false;
        }
        TipoFono other = (TipoFono) object;
        if ((this.idTipoFono == null && other.idTipoFono != null) || (this.idTipoFono != null && !this.idTipoFono.equals(other.idTipoFono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.TipoFono[ idTipoFono=" + idTipoFono + " ]";
    }
    
}
