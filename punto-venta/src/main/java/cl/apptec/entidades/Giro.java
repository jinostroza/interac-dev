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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matias Harding
 */
@Entity
@Table(name = "giro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Giro.findAll", query = "SELECT g FROM Giro g"),
    @NamedQuery(name = "Giro.findByIdGiro", query = "SELECT g FROM Giro g WHERE g.idGiro = :idGiro"),
    @NamedQuery(name = "Giro.findByCodigoGiro", query = "SELECT g FROM Giro g WHERE g.codigoGiro = :codigoGiro"),
    @NamedQuery(name = "Giro.findByNombreGiro", query = "SELECT g FROM Giro g WHERE g.nombreGiro = :nombreGiro"),
    @NamedQuery(name = "Giro.findByActivo", query = "SELECT g FROM Giro g WHERE g.activo = :activo"),
    @NamedQuery(name = "Giro.findByPais", query = "SELECT g FROM Giro g WHERE g.idPais = :idPais")
})
public class Giro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_giro")
    private Integer idGiro;
    @Size(max = 2147483647)
    @Column(name = "codigo_giro")
    private String codigoGiro;
    @Size(max = 70)
    @Column(name = "nombre_giro")
    private String nombreGiro;
    @Column(name = "activo")
    private Integer activo;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne
    private Pais idPais;

    public Giro() {
    }

    public Giro(Integer idGiro) {
        this.idGiro = idGiro;
    }

    public Integer getIdGiro() {
        return idGiro;
    }

    public void setIdGiro(Integer idGiro) {
        this.idGiro = idGiro;
    }

    public String getCodigoGiro() {
        return codigoGiro;
    }

    public void setCodigoGiro(String codigoGiro) {
        this.codigoGiro = codigoGiro;
    }

    public String getNombreGiro() {
        return nombreGiro;
    }

    public void setNombreGiro(String nombreGiro) {
        this.nombreGiro = nombreGiro;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
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
        hash += (idGiro != null ? idGiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Giro)) {
            return false;
        }
        Giro other = (Giro) object;
        if ((this.idGiro == null && other.idGiro != null) || (this.idGiro != null && !this.idGiro.equals(other.idGiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Giro[ idGiro=" + idGiro + " ]";
    }
    
}
