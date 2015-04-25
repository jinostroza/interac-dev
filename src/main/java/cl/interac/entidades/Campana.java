/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author edggar
 */
@Entity
@Table(name = "campana")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Campana.findAll", query = "SELECT c FROM Campana c"),
        @NamedQuery(name = "Campana.findByIdCampana", query = "SELECT c FROM Campana c WHERE c.idCampana = :idcampana"),
        @NamedQuery(name = "Campana.findByDescCampana", query = "SELECT c FROM Campana c WHERE c.descCampana = :desccampana"),

})
public class Campana implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idcampana")
    private Integer idCampana;
    @Size(max = 45)
    @Column(name = "desccampana")
    private String descCampana;
    private int idcampana;
    private int idcliente;
    private String desccampana;
    private int idtotem;

    public Campana() {
    }

    public Campana(Integer idCampana) {
        this.idCampana = idCampana;
    }



    //@XmlTransient
    //public List<Sucursal> getSucursalList() {
      //  return sucursalList;
    //}

   // public void setSucursalList(List<Sucursal> sucursalList) {
     //   this.sucursalList = sucursalList;
    //}

   // public Ciudad getIdCiudad() {
      //  return idCiudad;
   // }

    //public void setIdCiudad(Ciudad idCiudad) {
      //  this.idCiudad = idCiudad;
    //}

    //@XmlTransient
    //public List<ClienteApptec> getClienteApptecList() {
     //   return clienteApptecList;
   // }

   // public void setClienteApptecList(List<ClienteApptec> clienteApptecList) {
     //   this.clienteApptecList = clienteApptecList;
   // }

    //@XmlTransient
    //public List<Proveedor> getProveedorList() {
      //  return proveedorList;
   // }

    //public void setProveedorList(List<Proveedor> proveedorList) {
      //  this.proveedorList = proveedorList;
    //}

    public Integer getIdCampana() {
        return idCampana;
    }

    public void setIdCampana(Integer idCampana) {
        this.idCampana = idCampana;
    }

    public String getDescCampana() {
        return descCampana;
    }

    //@XmlTransient
    //public Collection<CliEmpresa> getCliEmpresaCollection() {
      //  return cliEmpresaCollection;
    //}

    //public void setCliEmpresaCollection(Collection<CliEmpresa> cliEmpresaCollection) {
      //  this.cliEmpresaCollection = cliEmpresaCollection;
    //}

    //@XmlTransient
   // public Collection<CliPersona> getCliPersonaCollection() {
     //   return cliPersonaCollection;
    //}

   // public void setCliPersonaCollection(Collection<CliPersona> cliPersonaCollection) {
     //   this.cliPersonaCollection = cliPersonaCollection;
    //}

    public void setDescCampana(String descCampana) {
        this.descCampana = descCampana;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampana != null ? idCampana.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campana)) {
            return false;
        }
        Campana other = (Campana) object;
        if ((this.idCampana == null && other.idCampana != null) || (this.idCampana != null && !this.idCampana.equals(other.idCampana))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.interac.entidades.Campana[ idCampana=" + idCampana + " ]";
    }

    @Id
    @Column(name = "idcampana")
    public int getIdcampana() {
        return idcampana;
    }

    public void setIdcampana(int idcampana) {
        this.idcampana = idcampana;
    }

    @Basic
    @Column(name = "idcliente")
    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    @Basic
    @Column(name = "desccampana")
    public String getDesccampana() {
        return desccampana;
    }

    public void setDesccampana(String desccampana) {
        this.desccampana = desccampana;
    }

    @Basic
    @Column(name = "idtotem")
    public int getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(int idtotem) {
        this.idtotem = idtotem;
    }
}
