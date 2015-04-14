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
@Table(name = "categoria_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaVenta.findAll", query = "SELECT c FROM CategoriaVenta c"),
    @NamedQuery(name = "CategoriaVenta.findByIdCategoriaventa", query = "SELECT c FROM CategoriaVenta c WHERE c.idCategoriaventa = :idCategoriaventa"),
    @NamedQuery(name = "CategoriaVenta.findByNombreCategoriaventa", query = "SELECT c FROM CategoriaVenta c WHERE c.nombreCategoriaventa = :nombreCategoriaventa"),
    @NamedQuery(name = "CategoriaVenta.findByDescripcionCategoriaventa", query = "SELECT c FROM CategoriaVenta c WHERE c.descripcionCategoriaventa = :descripcionCategoriaventa"),
    @NamedQuery(name = "CategoriaVenta.findByActivoCategoriaventa", query = "SELECT c FROM CategoriaVenta c WHERE c.activoCategoriaventa = :activoCategoriaventa")})
public class CategoriaVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoriaventa")
    private Integer idCategoriaventa;
    @Size(max = 50)
    @Column(name = "nombre_categoriaventa")
    private String nombreCategoriaventa;
    @Size(max = 100)
    @Column(name = "descripcion_categoriaventa")
    private String descripcionCategoriaventa;
    @Column(name = "activo_categoriaventa")
    private Integer activoCategoriaventa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaventa")
    private List<Venta> ventaList;

    public CategoriaVenta() {
    }

    public CategoriaVenta(Integer idCategoriaventa) {
        this.idCategoriaventa = idCategoriaventa;
    }

    public Integer getIdCategoriaventa() {
        return idCategoriaventa;
    }

    public void setIdCategoriaventa(Integer idCategoriaventa) {
        this.idCategoriaventa = idCategoriaventa;
    }

    public String getNombreCategoriaventa() {
        return nombreCategoriaventa;
    }

    public void setNombreCategoriaventa(String nombreCategoriaventa) {
        this.nombreCategoriaventa = nombreCategoriaventa;
    }

    public String getDescripcionCategoriaventa() {
        return descripcionCategoriaventa;
    }

    public void setDescripcionCategoriaventa(String descripcionCategoriaventa) {
        this.descripcionCategoriaventa = descripcionCategoriaventa;
    }

    public Integer getActivoCategoriaventa() {
        return activoCategoriaventa;
    }

    public void setActivoCategoriaventa(Integer activoCategoriaventa) {
        this.activoCategoriaventa = activoCategoriaventa;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaventa != null ? idCategoriaventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaVenta)) {
            return false;
        }
        CategoriaVenta other = (CategoriaVenta) object;
        if ((this.idCategoriaventa == null && other.idCategoriaventa != null) || (this.idCategoriaventa != null && !this.idCategoriaventa.equals(other.idCategoriaventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.CategoriaVenta[ idCategoriaventa=" + idCategoriaventa + " ]";
    }
    
}
