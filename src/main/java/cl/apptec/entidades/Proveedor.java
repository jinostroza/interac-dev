/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author apptec
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
        @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor"),
        @NamedQuery(name = "Proveedor.findByRazonSocialProveedor", query = "SELECT p FROM Proveedor p WHERE p.razonSocialProveedor = :razonSocialProveedor"),
        @NamedQuery(name = "Proveedor.findByDireccionProveedor", query = "SELECT p FROM Proveedor p WHERE p.direccionProveedor = :direccionProveedor"),
        @NamedQuery(name = "Proveedor.findByActivoProveedor", query = "SELECT p FROM Proveedor p WHERE p.activoProveedor = :activoProveedor")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Size(max = 50)
    @Column(name = "razon_social__proveedor")
    private String razonSocialProveedor;
    @Size(max = 100)
    @Column(name = "direccion_proveedor")
    private String direccionProveedor;
    @Column(name = "activo_proveedor")
    private Integer activoProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private List<ProductoProveedor> productoProveedorList;
    @JoinColumn(name = "id_comuna", referencedColumnName = "id_comuna")
    @ManyToOne(optional = false)
    private Comuna idComuna;
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<Compra> compraList;
    @Basic
    @Column(name = "rut_proveedor")
    private String rut;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public Integer getActivoProveedor() {
        return activoProveedor;
    }

    public void setActivoProveedor(Integer activoProveedor) {
        this.activoProveedor = activoProveedor;
    }

    @XmlTransient
    public List<ProductoProveedor> getProductoProveedorList() {
        return productoProveedorList;
    }

    public void setProductoProveedorList(List<ProductoProveedor> productoProveedorList) {
        this.productoProveedorList = productoProveedorList;
    }

    public Comuna getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Comuna idComuna) {
        this.idComuna = idComuna;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Proveedor[ idProveedor=" + idProveedor + " ]";
    }

}
