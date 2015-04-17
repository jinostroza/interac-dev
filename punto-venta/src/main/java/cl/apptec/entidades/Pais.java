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
@Table(name = "pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findByIdPais", query = "SELECT p FROM Pais p WHERE p.idPais = :idPais"),
    @NamedQuery(name = "Pais.findByNombrePais", query = "SELECT p FROM Pais p WHERE upper(p.nombrePais) = upper(:nombrePais)"),
    @NamedQuery(name = "Pais.findByActivoPais", query = "SELECT p FROM Pais p WHERE p.activoPais = :activoPais")})
public class Pais implements Serializable {
    @OneToMany(mappedBy = "idPais")
    private Collection<Giro> giroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private Collection<Proyecto> proyectoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pais")
    private Integer idPais;
    @Size(max = 50)
    @Column(name = "nombre_pais")
    private String nombrePais;
    @Column(name = "activo_pais")
    private Integer activoPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais", fetch = FetchType.LAZY)
    private List<Ciudad> ciudadList;

    public Pais() {
    }

    public Pais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public Integer getActivoPais() {
        return activoPais;
    }

    public void setActivoPais(Integer activoPais) {
        this.activoPais = activoPais;
    }

    @XmlTransient
    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if (this.getIdPais() == null || other.getIdPais() == null) return false;
        else if (this.getIdPais().intValue() != other.getIdPais().intValue()) return false;
        else return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Pais[ idPais=" + idPais + " ]";
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @XmlTransient
    public Collection<Giro> getGiroCollection() {
        return giroCollection;
    }

    public void setGiroCollection(Collection<Giro> giroCollection) {
        this.giroCollection = giroCollection;
    }
    
}
