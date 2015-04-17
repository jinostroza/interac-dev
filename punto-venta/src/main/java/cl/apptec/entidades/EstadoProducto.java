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
@Table(name = "estado_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoProducto.findAll", query = "SELECT e FROM EstadoProducto e"),
    @NamedQuery(name = "EstadoProducto.findByIdEstadoProducto", query = "SELECT e FROM EstadoProducto e WHERE e.idEstadoProducto = :idEstadoProducto"),
    @NamedQuery(name = "EstadoProducto.findByNombreEstadoProducto", query = "SELECT e FROM EstadoProducto e WHERE e.nombreEstadoProducto = :nombreEstadoProducto"),
    @NamedQuery(name = "EstadoProducto.findByDescripcionEstadoProducto", query = "SELECT e FROM EstadoProducto e WHERE e.descripcionEstadoProducto = :descripcionEstadoProducto"),
    @NamedQuery(name = "EstadoProducto.findByActivoEstadoProducto", query = "SELECT e FROM EstadoProducto e WHERE e.activoEstadoProducto = :activoEstadoProducto")})
public class EstadoProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_producto")
    private Integer idEstadoProducto;
    @Size(max = 50)
    @Column(name = "nombre_estado_producto")
    private String nombreEstadoProducto;
    @Size(max = 100)
    @Column(name = "descripcion_estado_producto")
    private String descripcionEstadoProducto;
    @Column(name = "activo_estado_producto")
    private Integer activoEstadoProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoProducto")
    private List<Producto> productoList;

    public EstadoProducto() {
    }

    public EstadoProducto(Integer idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public Integer getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(Integer idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public String getNombreEstadoProducto() {
        return nombreEstadoProducto;
    }

    public void setNombreEstadoProducto(String nombreEstadoProducto) {
        this.nombreEstadoProducto = nombreEstadoProducto;
    }

    public String getDescripcionEstadoProducto() {
        return descripcionEstadoProducto;
    }

    public void setDescripcionEstadoProducto(String descripcionEstadoProducto) {
        this.descripcionEstadoProducto = descripcionEstadoProducto;
    }

    public Integer getActivoEstadoProducto() {
        return activoEstadoProducto;
    }

    public void setActivoEstadoProducto(Integer activoEstadoProducto) {
        this.activoEstadoProducto = activoEstadoProducto;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoProducto != null ? idEstadoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProducto)) {
            return false;
        }
        EstadoProducto other = (EstadoProducto) object;
        if ((this.idEstadoProducto == null && other.idEstadoProducto != null) || (this.idEstadoProducto != null && !this.idEstadoProducto.equals(other.idEstadoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.EstadoProducto[ idEstadoProducto=" + idEstadoProducto + " ]";
    }
    
}
