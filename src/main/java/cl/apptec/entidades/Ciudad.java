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
 * @author apptec
 */
@Entity
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c"),
        @NamedQuery(name = "Ciudad.findByIdCiudad", query = "SELECT c FROM Ciudad c WHERE c.idCiudad = :idCiudad"),
        @NamedQuery(name = "Ciudad.findByNombreCiudad", query = "SELECT c FROM Ciudad c WHERE c.nombreCiudad = :nombreCiudad"),
        @NamedQuery(name = "Ciudad.findByActivoCiudad", query = "SELECT c FROM Ciudad c WHERE c.activoCiudad = :activoCiudad"),
        @NamedQuery(name = "Ciudad.findByPais", query = "SELECT c FROM Ciudad c WHERE c.idPais = :pais"),
})
public class Ciudad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Integer idCiudad;
    @Size(max = 50)
    @Column(name = "nombre_ciudad")
    private String nombreCiudad;
    @Column(name = "activo_ciudad")
    private Integer activoCiudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiudad", fetch = FetchType.LAZY)
    private List<Comuna> comunaList;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private Pais idPais;

    public Ciudad() {
    }

    public Ciudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Integer getActivoCiudad() {
        return activoCiudad;
    }

    public void setActivoCiudad(Integer activoCiudad) {
        this.activoCiudad = activoCiudad;
    }

    @XmlTransient
    public List<Comuna> getComunaList() {
        return comunaList;
    }

    public void setComunaList(List<Comuna> comunaList) {
        this.comunaList = comunaList;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudad != null ? idCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if (this.getIdCiudad() == null || other.getIdCiudad() == null) return false;
        else if (this.getIdCiudad().intValue() != other.getIdCiudad().intValue()) return false;
        else return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Ciudad[ idCiudad=" + idCiudad + " ]";
    }

}
