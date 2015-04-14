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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "roles_atribuciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolAtribucion.findAll", query = "SELECT r FROM RolAtribucion r"),
    @NamedQuery(name = "RolAtribucion.findByIdRolesAtribuciones", query = "SELECT r FROM RolAtribucion r WHERE r.idRolesAtribuciones = :idRolesAtribuciones"),
    @NamedQuery(name = "RolAtribucion.findByActivoRolesAtribuciones", query = "SELECT r FROM RolAtribucion r WHERE r.activoRolesAtribuciones = :activoRolesAtribuciones")})
public class RolAtribucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_roles_atribuciones")
    private Integer idRolesAtribuciones;
    @Column(name = "activo_roles_atribuciones")
    private Boolean activoRolesAtribuciones;
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol_usuario")
    @ManyToOne(optional = false)
    private RolUsuario idRolUsuario;
    @JoinColumn(name = "id_atribucion", referencedColumnName = "id_atribucion")
    @ManyToOne(optional = false)
    private Atribucion idAtribucion;

    public RolAtribucion() {
    }

    public RolAtribucion(Integer idRolesAtribuciones) {
        this.idRolesAtribuciones = idRolesAtribuciones;
    }

    public Integer getIdRolesAtribuciones() {
        return idRolesAtribuciones;
    }

    public void setIdRolesAtribuciones(Integer idRolesAtribuciones) {
        this.idRolesAtribuciones = idRolesAtribuciones;
    }

    public Boolean getActivoRolesAtribuciones() {
        return activoRolesAtribuciones;
    }

    public void setActivoRolesAtribuciones(Boolean activoRolesAtribuciones) {
        this.activoRolesAtribuciones = activoRolesAtribuciones;
    }

    public RolUsuario getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(RolUsuario idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public Atribucion getIdAtribucion() {
        return idAtribucion;
    }

    public void setIdAtribucion(Atribucion idAtribucion) {
        this.idAtribucion = idAtribucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolesAtribuciones != null ? idRolesAtribuciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolAtribucion)) {
            return false;
        }
        RolAtribucion other = (RolAtribucion) object;
        if ((this.idRolesAtribuciones == null && other.idRolesAtribuciones != null) || (this.idRolesAtribuciones != null && !this.idRolesAtribuciones.equals(other.idRolesAtribuciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.RolAtribucion[ idRolesAtribuciones=" + idRolesAtribuciones + " ]";
    }
    
}
