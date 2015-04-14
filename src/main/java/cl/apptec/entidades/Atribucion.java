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
@Table(name = "atribucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atribucion.findAll", query = "SELECT a FROM Atribucion a"),
    @NamedQuery(name = "Atribucion.findByIdAtribucion", query = "SELECT a FROM Atribucion a WHERE a.idAtribucion = :idAtribucion"),
    @NamedQuery(name = "Atribucion.findByNombreAtribucion", query = "SELECT a FROM Atribucion a WHERE a.nombreAtribucion = :nombreAtribucion"),
    @NamedQuery(name = "Atribucion.findByDescripcionAtribucion", query = "SELECT a FROM Atribucion a WHERE a.descripcionAtribucion = :descripcionAtribucion"),
    @NamedQuery(name = "Atribucion.findByActivoAtribucion", query = "SELECT a FROM Atribucion a WHERE a.activoAtribucion = :activoAtribucion")})
public class Atribucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_atribucion")
    private Integer idAtribucion;
    @Size(max = 50)
    @Column(name = "nombre_atribucion")
    private String nombreAtribucion;
    @Size(max = 100)
    @Column(name = "descripcion_atribucion")
    private String descripcionAtribucion;
    @Column(name = "activo_atribucion")
    private Boolean activoAtribucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAtribucion")
    private List<RolAtribucion> rolAtribucionList;

    public Atribucion() {
    }

    public Atribucion(Integer idAtribucion) {
        this.idAtribucion = idAtribucion;
    }

    public Integer getIdAtribucion() {
        return idAtribucion;
    }

    public void setIdAtribucion(Integer idAtribucion) {
        this.idAtribucion = idAtribucion;
    }

    public String getNombreAtribucion() {
        return nombreAtribucion;
    }

    public void setNombreAtribucion(String nombreAtribucion) {
        this.nombreAtribucion = nombreAtribucion;
    }

    public String getDescripcionAtribucion() {
        return descripcionAtribucion;
    }

    public void setDescripcionAtribucion(String descripcionAtribucion) {
        this.descripcionAtribucion = descripcionAtribucion;
    }

    public Boolean getActivoAtribucion() {
        return activoAtribucion;
    }

    public void setActivoAtribucion(Boolean activoAtribucion) {
        this.activoAtribucion = activoAtribucion;
    }

    @XmlTransient
    public List<RolAtribucion> getRolAtribucionList() {
        return rolAtribucionList;
    }

    public void setRolAtribucionList(List<RolAtribucion> rolAtribucionList) {
        this.rolAtribucionList = rolAtribucionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtribucion != null ? idAtribucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atribucion)) {
            return false;
        }
        Atribucion other = (Atribucion) object;
        if ((this.idAtribucion == null && other.idAtribucion != null) || (this.idAtribucion != null && !this.idAtribucion.equals(other.idAtribucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Atribucion[ idAtribucion=" + idAtribucion + " ]";
    }
    
}
