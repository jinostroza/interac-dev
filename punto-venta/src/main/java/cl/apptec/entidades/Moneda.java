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
 *
 * @author apptec
 */
@Entity
@Table(name = "moneda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m"),
    @NamedQuery(name = "Moneda.findByIdMoneda", query = "SELECT m FROM Moneda m WHERE m.idMoneda = :idMoneda"),
    @NamedQuery(name = "Moneda.findByNombreMoneda", query = "SELECT m FROM Moneda m WHERE m.nombreMoneda = :nombreMoneda"),
    @NamedQuery(name = "Moneda.findBySimboloMoneda", query = "SELECT m FROM Moneda m WHERE m.simboloMoneda = :simboloMoneda"),
    @NamedQuery(name = "Moneda.findByActivoMoneda", query = "SELECT m FROM Moneda m WHERE m.activoMoneda = :activoMoneda")})
public class Moneda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moneda")
    private Integer idMoneda;
    @Size(max = 50)
    @Column(name = "nombre_moneda")
    private String nombreMoneda;
    @Size(max = 50)
    @Column(name = "simbolo_moneda")
    private String simboloMoneda;
    @Column(name = "activo_moneda")
    private Integer activoMoneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
    private List<Proyecto> proyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
    private List<Venta> ventaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
    private List<CambioMoneda> cambioMonedaList;
    @OneToMany(mappedBy = "moneda", fetch = FetchType.LAZY)
    private List<Compra> compraList;

    public Moneda() {
    }

    public Moneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public Integer getActivoMoneda() {
        return activoMoneda;
    }

    public void setActivoMoneda(Integer activoMoneda) {
        this.activoMoneda = activoMoneda;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<CambioMoneda> getCambioMonedaList() {
        return cambioMonedaList;
    }

    public void setCambioMonedaList(List<CambioMoneda> cambioMonedaList) {
        this.cambioMonedaList = cambioMonedaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMoneda != null ? idMoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if ((this.idMoneda == null && other.idMoneda != null) || (this.idMoneda != null && !this.idMoneda.equals(other.idMoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Moneda[ idMoneda=" + idMoneda + " ]";
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }
    
}
