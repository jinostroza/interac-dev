/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "anuncio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anuncio.findAll", query = "SELECT a FROM Anuncio a"),
    @NamedQuery(name = "Anuncio.findByIdAnuncio", query = "SELECT a FROM Anuncio a WHERE a.idAnuncio = :idanuncio"),
    @NamedQuery(name = "Anuncio.findByDescAnuncio", query = "SELECT a FROM Anuncio a WHERE a.descAnuncio = :descanuncio"),
    @NamedQuery(name = "Anuncio.findByMedia", query = "SELECT a FROM Anuncio a WHERE a.Media = :media"),
    @NamedQuery(name = "Anuncio.findByRubro", query = "SELECT a FROM Anuncio a WHERE a.Rubro = :rubro")})
public class Anuncio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anuncio")
    private Integer idAnuncio;
    @Size(max = 45)
    @Column(name = "desc_anuncio")
    private String descAnuncio;
    @Size(max = 100)
    @Column(name = "media")
    private String Media;
    @Size(max = 40)
    @Column(name = "rubro")
    private String Rubro;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan")
    //private List<ClienteApptec> clienteApptecList;

    public Anuncio() {
    }

    public Anuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Integer getIdAnuncio() {return idAnuncio;}

    public void setDescAnuncio(String descAnuncio) {
        this.descAnuncio = descAnuncio;
    }

    public String getDescAnuncio() {
        return descAnuncio;
    }

    public void setMedia(String Media) {
        this.Media = Media;
    }

    public String getMedia() {
        return Media;
    }

    public void setRubro(String Rubro) {this.Rubro =Rubro;}

    public String getRubro() {return Rubro;}

   // @XmlTransient
    //public List<ClienteApptec> getClienteApptecList() {
      //  return clienteApptecList;
   // }

    //public void setClienteApptecList(List<ClienteApptec> clienteApptecList) {
      //  this.clienteApptecList = clienteApptecList;
    //}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnuncio != null ? idAnuncio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anuncio)) {
            return false;
        }
        Anuncio other = (Anuncio) object;
        if ((this.idAnuncio == null && other.idAnuncio != null) || (this.idAnuncio != null && !this.idAnuncio.equals(other.idAnuncio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.interac.entidades.Anuncio[ idAnuncio=" + idAnuncio + " ]";
    }
    
}
