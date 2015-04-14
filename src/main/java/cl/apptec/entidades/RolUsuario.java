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
@Table(name = "rol_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolUsuario.findAll", query = "SELECT r FROM RolUsuario r"),
    @NamedQuery(name = "RolUsuario.findByIdRolUsuario", query = "SELECT r FROM RolUsuario r WHERE r.idRolUsuario = :idRolUsuario"),
    @NamedQuery(name = "RolUsuario.findByNombreRolUsuario", query = "SELECT r FROM RolUsuario r WHERE r.nombreRolUsuario = :nombreRolUsuario"),
    @NamedQuery(name = "RolUsuario.findByDescripcionRolUsuario", query = "SELECT r FROM RolUsuario r WHERE r.descripcionRolUsuario = :descripcionRolUsuario"),
    @NamedQuery(name = "RolUsuario.findByActivoRolUsuario", query = "SELECT r FROM RolUsuario r WHERE r.activoRolUsuario = :activoRolUsuario")})
public class RolUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol_usuario")
    private Integer idRolUsuario;
    @Size(max = 50)
    @Column(name = "nombre_rol_usuario")
    private String nombreRolUsuario;
    @Size(max = 100)
    @Column(name = "descripcion_rol_usuario")
    private String descripcionRolUsuario;
    @Column(name = "activo_rol_usuario")
    private Boolean activoRolUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRolUsuario")
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRolUsuario")
    private List<RolAtribucion> rolAtribucionList;

    public RolUsuario() {
    }

    public RolUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public Integer getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getNombreRolUsuario() {
        return nombreRolUsuario;
    }

    public void setNombreRolUsuario(String nombreRolUsuario) {
        this.nombreRolUsuario = nombreRolUsuario;
    }

    public String getDescripcionRolUsuario() {
        return descripcionRolUsuario;
    }

    public void setDescripcionRolUsuario(String descripcionRolUsuario) {
        this.descripcionRolUsuario = descripcionRolUsuario;
    }

    public Boolean getActivoRolUsuario() {
        return activoRolUsuario;
    }

    public void setActivoRolUsuario(Boolean activoRolUsuario) {
        this.activoRolUsuario = activoRolUsuario;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
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
        hash += (idRolUsuario != null ? idRolUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuario)) {
            return false;
        }
        RolUsuario other = (RolUsuario) object;
        if ((this.idRolUsuario == null && other.idRolUsuario != null) || (this.idRolUsuario != null && !this.idRolUsuario.equals(other.idRolUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.RolUsuario[ idRolUsuario=" + idRolUsuario + " ]";
    }
    
}
