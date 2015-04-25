/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.entidades;

import java.io.Serializable;
import javax.persistence.*;
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
    @Column(name = "idanuncio")
    private Integer idAnuncio;
    @Size(max = 45)
    @Column(name = "descanuncio")
    private String descAnuncio;
    @Size(max = 100)
    @Column(name = "media")
    private String Media;
    @Size(max = 40)
    @Column(name = "rubro")
    private String Rubro;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan")
    //private List<ClienteApptec> clienteApptecList;
    private int idanuncio;
    private String descanuncio;
    private int idcampana;
    private int idcategoria;
    private String media;
    private String rubro;

    public Anuncio() {
    }

    public Anuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Integer getIdAnuncio() {return idAnuncio;}

   // @XmlTransient
    //public List<ClienteApptec> getClienteApptecList() {
      //  return clienteApptecList;
   // }

    //public void setClienteApptecList(List<ClienteApptec> clienteApptecList) {
      //  this.clienteApptecList = clienteApptecList;
    //}

    public String getDescAnuncio() {
        return descAnuncio;
    }

    public void setDescAnuncio(String descAnuncio) {
        this.descAnuncio = descAnuncio;
    }

    @Basic
    @Column(name = "media")
    public String getMedia() {
        return Media;
    }

    public void setMedia(String Media) {
        this.Media = Media;
    }

    @Basic
    @Column(name = "rubro")
    public String getRubro() {return Rubro;}

    public void setRubro(String Rubro) {this.Rubro =Rubro;}

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

    @Id
    @Column(name = "idanuncio")
    public int getIdanuncio() {
        return idanuncio;
    }

    public void setIdanuncio(int idanuncio) {
        this.idanuncio = idanuncio;
    }

    @Basic
    @Column(name = "descanuncio")
    public String getDescanuncio() {
        return descanuncio;
    }

    public void setDescanuncio(String descanuncio) {
        this.descanuncio = descanuncio;
    }

    @Basic
    @Column(name = "idcampana")
    public int getIdcampana() {
        return idcampana;
    }

    public void setIdcampana(int idcampana) {
        this.idcampana = idcampana;
    }

    @Basic
    @Column(name = "idcategoria")
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }
}
