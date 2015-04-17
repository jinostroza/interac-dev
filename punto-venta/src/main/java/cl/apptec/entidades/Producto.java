/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author apptec
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
        @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
        @NamedQuery(name = "Producto.findByNombreProducto", query = "SELECT p FROM Producto p WHERE p.nombreProducto = :nombreProducto"),
        @NamedQuery(name = "Producto.findByPrecioProducto", query = "SELECT p FROM Producto p WHERE p.precioProducto = :precioProducto"),
        @NamedQuery(name = "Producto.findByActivoProducto", query = "SELECT p FROM Producto p WHERE p.activoProducto = :activoProducto"),
        @NamedQuery(
                name = "Producto.findWithRelationship",
                query = "SELECT p FROM Producto p " +
                        "INNER JOIN FETCH p.categoriaProducto c " +
                        "INNER JOIN FETCH p.idEstadoProducto " +
                        "INNER JOIN FETCH p.idUnidad " +
                        "LEFT JOIN FETCH p.imagenes " +
                        "LEFT JOIN FETCH c.padre " +
                        "WHERE p = :producto"),
})
public class Producto implements Serializable {
    @Size(max = 2147483647)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne
    private Marca idMarca;
    @Size(max = 100)
    @Column(name = "cod_bar")
    private String codBar;
    
    @OneToMany(mappedBy = "producto")  
    private Collection<DetalleCompra> detalleCompraCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Size(max = 50)
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "precio_producto")
    private BigInteger precioProducto;

    @Column(name = "activo_producto")
    private Boolean activoProducto;

    @Size(max = 100)
    @Column(name = "sku")
    private String sku;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<ProductoProveedor> productoProveedorList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentaList;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", referencedColumnName = "id_unidad")
    private Unidad idUnidad;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<Stock> stockList;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_producto", referencedColumnName = "id_estado_producto")
    private EstadoProducto idEstadoProducto;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "producto",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            }
    )
    private List<Imagen> imagenes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_producto", referencedColumnName = "id_categoria_producto")
    private CategoriaProducto categoriaProducto;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigInteger getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigInteger precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Boolean getActivoProducto() {
        return activoProducto;
    }

    public void setActivoProducto(Boolean activoProducto) {
        this.activoProducto = activoProducto;
    }

    @XmlTransient
    public List<ProductoProveedor> getProductoProveedorList() {
        return productoProveedorList;
    }

    public void setProductoProveedorList(List<ProductoProveedor> productoProveedorList) {
        this.productoProveedorList = productoProveedorList;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public Unidad getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Unidad idUnidad) {
        this.idUnidad = idUnidad;
    }

    public EstadoProducto getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(EstadoProducto idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Producto[ idProducto=" + idProducto + " ]";
    }

    @XmlTransient
    public Collection<DetalleCompra> getDetalleCompraCollection() {
        return detalleCompraCollection;
    }

    public void setDetalleCompraCollection(Collection<DetalleCompra> detalleCompraCollection) {
        this.detalleCompraCollection = detalleCompraCollection;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCodBar() {
        return codBar;
    }

    public void setCodBar(String codBar) {
        this.codBar = codBar;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }
}
