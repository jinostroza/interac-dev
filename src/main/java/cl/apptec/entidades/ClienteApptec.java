/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apptec
 */
@Entity
@Table(name = "cliente_apptec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteApptec.findAll", query = "SELECT c FROM ClienteApptec c"),
    @NamedQuery(name = "ClienteApptec.findByIdClienteApptec", query = "SELECT c FROM ClienteApptec c WHERE c.idClienteApptec = :idClienteApptec"),
    @NamedQuery(name = "ClienteApptec.findByRut", query = "SELECT c FROM ClienteApptec c WHERE c.rut = :rut"),
    @NamedQuery(name = "ClienteApptec.findByRazonSocialClienteApptec", query = "SELECT c FROM ClienteApptec c WHERE c.razonSocialClienteApptec = :razonSocialClienteApptec"),
    @NamedQuery(name = "ClienteApptec.findByDireccionClienteApptec", query = "SELECT c FROM ClienteApptec c WHERE c.direccionClienteApptec = :direccionClienteApptec")})
public class ClienteApptec implements Serializable {
    @OneToMany(mappedBy = "idClienteApptec")
    private Collection<ContactoCliApptec> contactoCliApptecCollection;
    @Column(name = "id_giro")
    private Integer idGiro;
    @Column(name = "fecha_cad_plan")
    @Temporal(TemporalType.DATE)
    private Date fechaCadPlan;
    @JoinColumn(name = "id_estado_cliente_apptec", referencedColumnName = "id_estado_cliente_apptec")
    @ManyToOne
    private EstadoClienteApptec idEstadoClienteApptec;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente_apptec")
    private Integer idClienteApptec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "rut")
    private String rut;
    @Size(max = 50)
    @Column(name = "razon_social_cliente_apptec")
    private String razonSocialClienteApptec;
    @Size(max = 100)
    @Column(name = "direccion_cliente_apptec")
    private String direccionClienteApptec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClienteApptec")
    private List<Proyecto> proyectoList;
    @JoinColumn(name = "id_plan", referencedColumnName = "id_plan")
    @ManyToOne(optional = false)
    private Plan idPlan;
    @JoinColumn(name = "id_comuna", referencedColumnName = "id_comuna")
    @ManyToOne(optional = false)
    private Comuna idComuna;

    public ClienteApptec() {
    }

    public ClienteApptec(Integer idClienteApptec) {
        this.idClienteApptec = idClienteApptec;
    }

    public ClienteApptec(Integer idClienteApptec, String rut) {
        this.idClienteApptec = idClienteApptec;
        this.rut = rut;
    }

    public Integer getIdClienteApptec() {
        return idClienteApptec;
    }

    public void setIdClienteApptec(Integer idClienteApptec) {
        this.idClienteApptec = idClienteApptec;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocialClienteApptec() {
        return razonSocialClienteApptec;
    }

    public void setRazonSocialClienteApptec(String razonSocialClienteApptec) {
        this.razonSocialClienteApptec = razonSocialClienteApptec;
    }

    public String getDireccionClienteApptec() {
        return direccionClienteApptec;
    }

    public void setDireccionClienteApptec(String direccionClienteApptec) {
        this.direccionClienteApptec = direccionClienteApptec;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }


    public Comuna getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Comuna idComuna) {
        this.idComuna = idComuna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClienteApptec != null ? idClienteApptec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteApptec)) {
            return false;
        }
        ClienteApptec other = (ClienteApptec) object;
        if ((this.idClienteApptec == null && other.idClienteApptec != null) || (this.idClienteApptec != null && !this.idClienteApptec.equals(other.idClienteApptec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.ClienteApptec[ idClienteApptec=" + idClienteApptec + " ]";
    }

    public Integer getIdGiro() {
        return idGiro;
    }

    public void setIdGiro(Integer idGiro) {
        this.idGiro = idGiro;
    }

    public Date getFechaCadPlan() {
        return fechaCadPlan;
    }

    public void setFechaCadPlan(Date fechaCadPlan) {
        this.fechaCadPlan = fechaCadPlan;
    }

    public EstadoClienteApptec getIdEstadoClienteApptec() {
        return idEstadoClienteApptec;
    }

    public void setIdEstadoClienteApptec(EstadoClienteApptec idEstadoClienteApptec) {
        this.idEstadoClienteApptec = idEstadoClienteApptec;
    }

    @XmlTransient
    public Collection<ContactoCliApptec> getContactoCliApptecCollection() {
        return contactoCliApptecCollection;
    }

    public void setContactoCliApptecCollection(Collection<ContactoCliApptec> contactoCliApptecCollection) {
        this.contactoCliApptecCollection = contactoCliApptecCollection;
    }
    
}
