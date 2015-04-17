/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
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
@Table(name = "impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i"),
    @NamedQuery(name = "Impuesto.findByIdImpuesto", query = "SELECT i FROM Impuesto i WHERE i.idImpuesto = :idImpuesto"),
    @NamedQuery(name = "Impuesto.findByNombreImpuesto", query = "SELECT i FROM Impuesto i WHERE i.nombreImpuesto = :nombreImpuesto"),
    @NamedQuery(name = "Impuesto.findByDescripcionImpuesto", query = "SELECT i FROM Impuesto i WHERE i.descripcionImpuesto = :descripcionImpuesto"),
    @NamedQuery(name = "Impuesto.findByActivoImpuesto", query = "SELECT i FROM Impuesto i WHERE i.activoImpuesto = :activoImpuesto")})
public class Impuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_impuesto")
    private Integer idImpuesto;
    
    @OneToMany(mappedBy = "impuesto", fetch = FetchType.LAZY)
    private List<Compra> compraList;
    @Size(max = 50)
    @Column(name = "nombre_impuesto")
    private String nombreImpuesto;
    @Size(max = 100)
    @Column(name = "descripcion_impuesto")
    private String descripcionImpuesto;
    @Column(name = "activo_impuesto")
    private Integer activoImpuesto;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;

    public Impuesto() {
    }

    public Impuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public Integer getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    public void setNombreImpuesto(String nombreImpuesto) {
        this.nombreImpuesto = nombreImpuesto;
    }

    public String getDescripcionImpuesto() {
        return descripcionImpuesto;
    }

    public void setDescripcionImpuesto(String descripcionImpuesto) {
        this.descripcionImpuesto = descripcionImpuesto;
    }

    public Integer getActivoImpuesto() {
        return activoImpuesto;
    }

    public void setActivoImpuesto(Integer activoImpuesto) {
        this.activoImpuesto = activoImpuesto;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImpuesto != null ? idImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.idImpuesto == null && other.idImpuesto != null) || (this.idImpuesto != null && !this.idImpuesto.equals(other.idImpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Impuesto[ idImpuesto=" + idImpuesto + " ]";
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }
    
}
