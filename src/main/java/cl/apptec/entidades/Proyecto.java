/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matias Harding
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto"),
    @NamedQuery(name = "Proyecto.findByNombreProyecto", query = "SELECT p FROM Proyecto p WHERE p.nombreProyecto = :nombreProyecto"),
    @NamedQuery(name = "Proyecto.findByActivoProyecto", query = "SELECT p FROM Proyecto p WHERE p.activoProyecto = :activoProyecto")})

public class Proyecto implements Serializable {
    @OneToMany(mappedBy = "idProyecto")
    private Collection<CliEmpresa> cliEmpresaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Size(max = 50)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    //@Column(name = "activo_proyecto")
    //@Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "activo_proyecto", columnDefinition = "BIT", length=1)
    private Boolean activoProyecto;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private Pais idPais;
    @JoinColumn(name = "id_moneda", referencedColumnName = "id_moneda")
    @ManyToOne
    private Moneda idMoneda;
    @JoinColumn(name = "id_cliente_apptec", referencedColumnName = "id_cliente_apptec")
    @ManyToOne(optional = false)
    private ClienteApptec idClienteApptec;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Boolean getActivoProyecto() {
        return activoProyecto;
    }

    public void setActivoProyecto(Boolean activoProyecto) {
        this.activoProyecto = activoProyecto;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public Moneda getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Moneda idMoneda) {
        this.idMoneda = idMoneda;
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
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Proyecto[ idProyecto=" + idProyecto + " ]";
    }

    @XmlTransient
    public Collection<CliEmpresa> getCliEmpresaCollection() {
        return cliEmpresaCollection;
    }

    public void setCliEmpresaCollection(Collection<CliEmpresa> cliEmpresaCollection) {
        this.cliEmpresaCollection = cliEmpresaCollection;
    }
    
}
