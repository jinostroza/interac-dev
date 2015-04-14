/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "categoria_producto")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CategoriaProducto.findAll", query = "SELECT c FROM CategoriaProducto c"),
        @NamedQuery(name = "CategoriaProducto.findByIdCategoriaProducto", query = "SELECT c FROM CategoriaProducto c WHERE c.idCategoriaProducto = :idCategoriaProducto"),
        @NamedQuery(name = "CategoriaProducto.findByNombreCategoriaProducto", query = "SELECT c FROM CategoriaProducto c WHERE upper(c.nombreCategoriaProducto) = upper(:nombreCategoriaProducto)"),
        @NamedQuery(name = "CategoriaProducto.findByDescripcionCategoriaProducto", query = "SELECT c FROM CategoriaProducto c WHERE c.descripcionCategoriaProducto = :descripcionCategoriaProducto"),
        @NamedQuery(name = "CategoriaProducto.findByActivoCategoriaProducto", query = "SELECT c FROM CategoriaProducto c WHERE c.activoCategoriaProducto = :activoCategoriaProducto"),
        @NamedQuery(name = "CategoriaProducto.findPadres", query = "SELECT c FROM CategoriaProducto c WHERE c.padre is null and c.activoCategoriaProducto = true "),
        @NamedQuery(name = "CategoriaProducto.findByPadre", query = "SELECT c FROM CategoriaProducto c WHERE c.padre = :padre and c.activoCategoriaProducto = true "),
        @NamedQuery(name = "CategoriaProducto.findPadreCategoria", query = "SELECT DISTINCT p FROM CategoriaProducto c INNER JOIN c.padre p")
})

public class CategoriaProducto implements Serializable {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "categoriaProducto1")
    private CategoriaProducto categoriaProducto;
    @JoinColumn(name = "id_categoria_producto", referencedColumnName = "id_categoria_producto")
    @OneToOne(optional = false)
    private CategoriaProducto categoriaProducto1;
    @OneToMany(mappedBy = "categoriaProducto")
    private Collection<Producto> productoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_producto")
    private Integer idCategoriaProducto;
    @Size(max = 50)
    @Column(name = "nombre_categoria_producto")
    private String nombreCategoriaProducto;
    @Size(max = 100)
    @Column(name = "descripcion_categoria_producto")
    private String descripcionCategoriaProducto;
    @Column(name = "activo_categoria_producto")
    private Boolean activoCategoriaProducto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "padre")
    private List<CategoriaProducto> subcategorias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre", referencedColumnName = "id_categoria_producto")
    private CategoriaProducto padre;

    public CategoriaProducto() {
    }

    public CategoriaProducto(Integer idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    public Integer getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(Integer idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    public String getNombreCategoriaProducto() {
        return nombreCategoriaProducto;
    }

    public void setNombreCategoriaProducto(String nombreCategoriaProducto) {
        this.nombreCategoriaProducto = nombreCategoriaProducto;
    }

    public String getDescripcionCategoriaProducto() {
        return descripcionCategoriaProducto;
    }

    public void setDescripcionCategoriaProducto(String descripcionCategoriaProducto) {
        this.descripcionCategoriaProducto = descripcionCategoriaProducto;
    }

    public Boolean getActivoCategoriaProducto() {
        return activoCategoriaProducto;
    }

    public void setActivoCategoriaProducto(Boolean activoCategoriaProducto) {
        this.activoCategoriaProducto = activoCategoriaProducto;
    }

    public List<CategoriaProducto> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<CategoriaProducto> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public CategoriaProducto getPadre() {
        return padre;
    }

    public void setPadre(CategoriaProducto padre) {
        this.padre = padre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaProducto != null ? idCategoriaProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CategoriaProducto)) {
            return false;
        }
        CategoriaProducto other = (CategoriaProducto) object;
        if (this.getIdCategoriaProducto() == null || other.getIdCategoriaProducto() == null) return false;
        else if (this.getIdCategoriaProducto().intValue() != other.getIdCategoriaProducto().intValue()) return false;
        else return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.CategoriaProducto[ idCategoriaProducto=" + idCategoriaProducto + " ]";
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public CategoriaProducto getCategoriaProducto1() {
        return categoriaProducto1;
    }

    public void setCategoriaProducto1(CategoriaProducto categoriaProducto1) {
        this.categoriaProducto1 = categoriaProducto1;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

}
