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
@Table(name = "contacto_cli_apptec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactoCliApptec.findAll", query = "SELECT c FROM ContactoCliApptec c"),
    @NamedQuery(name = "ContactoCliApptec.findByIdContactoCliApptec", query = "SELECT c FROM ContactoCliApptec c WHERE c.idContactoCliApptec = :idContactoCliApptec"),
    @NamedQuery(name = "ContactoCliApptec.findByNombreCca", query = "SELECT c FROM ContactoCliApptec c WHERE c.nombreCca = :nombreCca"),
    @NamedQuery(name = "ContactoCliApptec.findByApellidoCca", query = "SELECT c FROM ContactoCliApptec c WHERE c.apellidoCca = :apellidoCca"),
    @NamedQuery(name = "ContactoCliApptec.findByEmailCca", query = "SELECT c FROM ContactoCliApptec c WHERE c.emailCca = :emailCca"),
    @NamedQuery(name = "ContactoCliApptec.findByFonoCca", query = "SELECT c FROM ContactoCliApptec c WHERE c.fonoCca = :fonoCca")})
public class ContactoCliApptec implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto_cli_apptec")
    private Integer idContactoCliApptec;
    @Size(max = 2147483647)
    @Column(name = "nombre_cca")
    private String nombreCca;
    @Size(max = 2147483647)
    @Column(name = "apellido_cca")
    private String apellidoCca;
    @Size(max = 2147483647)
    @Column(name = "email_cca")
    private String emailCca;
    @Size(max = 2147483647)
    @Column(name = "fono_cca")
    private String fonoCca;
    @JoinColumn(name = "id_estado_contacto", referencedColumnName = "id_estado_contacto")
    @ManyToOne(optional = false)
    private EstadoContacto idEstadoContacto;
    @JoinColumn(name = "id_cliente_apptec", referencedColumnName = "id_cliente_apptec")
    @ManyToOne
    private ClienteApptec idClienteApptec;

    public ContactoCliApptec() {
    }

    public ContactoCliApptec(Integer idContactoCliApptec) {
        this.idContactoCliApptec = idContactoCliApptec;
    }

    public Integer getIdContactoCliApptec() {
        return idContactoCliApptec;
    }

    public void setIdContactoCliApptec(Integer idContactoCliApptec) {
        this.idContactoCliApptec = idContactoCliApptec;
    }

    public String getNombreCca() {
        return nombreCca;
    }

    public void setNombreCca(String nombreCca) {
        this.nombreCca = nombreCca;
    }

    public String getApellidoCca() {
        return apellidoCca;
    }

    public void setApellidoCca(String apellidoCca) {
        this.apellidoCca = apellidoCca;
    }

    public String getEmailCca() {
        return emailCca;
    }

    public void setEmailCca(String emailCca) {
        this.emailCca = emailCca;
    }

    public String getFonoCca() {
        return fonoCca;
    }

    public void setFonoCca(String fonoCca) {
        this.fonoCca = fonoCca;
    }

    public EstadoContacto getIdEstadoContacto() {
        return idEstadoContacto;
    }

    public void setIdEstadoContacto(EstadoContacto idEstadoContacto) {
        this.idEstadoContacto = idEstadoContacto;
    }

    public ClienteApptec getIdClienteApptec() {
        return idClienteApptec;
    }

    public void setIdClienteApptec(ClienteApptec idClienteApptec) {
        this.idClienteApptec = idClienteApptec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContactoCliApptec != null ? idContactoCliApptec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactoCliApptec)) {
            return false;
        }
        ContactoCliApptec other = (ContactoCliApptec) object;
        if ((this.idContactoCliApptec == null && other.idContactoCliApptec != null) || (this.idContactoCliApptec != null && !this.idContactoCliApptec.equals(other.idContactoCliApptec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.ContactoCliApptec[ idContactoCliApptec=" + idContactoCliApptec + " ]";
    }
    
}
