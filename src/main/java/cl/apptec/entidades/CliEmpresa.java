/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matias Harding
 */
@Entity
@Table(name = "cli_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CliEmpresa.findAll", query = "SELECT ce FROM CliEmpresa ce"),
    @NamedQuery(name = "CliEmpresa.findByIdCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.idCliEmpresa = :idCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByRutCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.rutCliEmpresa = :rutCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByExtranjeroCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.extranjeroCliEmpresa = :extranjeroCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByRazonSocialCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.razonSocialCliEmpresa = :razonSocialCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByDireccionCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.direccionCliEmpresa = :direccionCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByNotaCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.notaCliEmpresa = :notaCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByFacebookCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.facebookCliEmpresa = :facebookCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByTwitterCliEmpresa", query = "SELECT ce FROM CliEmpresa ce WHERE ce.twitterCliEmpresa = :twitterCliEmpresa"),
    @NamedQuery(name = "CliEmpresa.findByFechaCadPlan", query = "SELECT ce FROM CliEmpresa ce WHERE ce.fechaCadPlan = :fechaCadPlan"),
    @NamedQuery(name = "CliEmpresa.findByEmail", query = "SELECT ce FROM CliEmpresa ce WHERE ce.email = :email"),
    @NamedQuery(name = "CliEmpresa.findByProyecto", query = "SELECT ce FROM CliEmpresa ce WHERE ce.idProyecto = :proyecto")
})
public class CliEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cli_empresa")
    private Integer idCliEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "rut_cli_empresa")
    private String rutCliEmpresa;
    @Column(name = "extranjero_cli_empresa")
    private Integer extranjeroCliEmpresa;
    @Size(max = 2147483647)
    @Column(name = "razon_social_cli_empresa")
    private String razonSocialCliEmpresa;
    @Size(max = 2147483647)
    @Column(name = "direccion_cli_empresa")
    private String direccionCliEmpresa;
    @Size(max = 100)
    @Column(name = "nota_cli_empresa")
    private String notaCliEmpresa;
    @Size(max = 2147483647)
    @Column(name = "facebook_cli_empresa")
    private String facebookCliEmpresa;
    @Size(max = 2147483647)
    @Column(name = "twitter_cli_empresa")
    private String twitterCliEmpresa;
    @Column(name = "fecha_cad_plan")
    @Temporal(TemporalType.DATE)
    private Date fechaCadPlan;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Ingrese un e-mail valido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne
    private Proyecto idProyecto;
    @JoinColumn(name = "id_plan_cliente", referencedColumnName = "id_plan_cliente")
    @ManyToOne(optional = false)
    private PlanCliente idPlanCliente;
    @JoinColumn(name = "id_giro", referencedColumnName = "id_giro")
    @ManyToOne
    private Giro idGiro;
    @JoinColumn(name = "id_estado_cliente", referencedColumnName = "id_estado_cliente")
    @ManyToOne(optional = false)
    private EstadoCliente idEstadoCliente;
    @JoinColumn(name = "id_comuna", referencedColumnName = "id_comuna")
    @ManyToOne(optional = false)
    private Comuna idComuna;

    public CliEmpresa() {
    }

    public CliEmpresa(Integer idCliEmpresa) {
        this.idCliEmpresa = idCliEmpresa;
    }

    public CliEmpresa(Integer idCliEmpresa, String rutCliEmpresa) {
        this.idCliEmpresa = idCliEmpresa;
        this.rutCliEmpresa = rutCliEmpresa;
    }

    
    public Integer getIdCliEmpresa() {
        return idCliEmpresa;
    }

    public void setIdCliEmpresa(Integer idCliEmpresa) {
        this.idCliEmpresa = idCliEmpresa;
    }

    public String getRutCliEmpresa() {
        return rutCliEmpresa;
    }

    public void setRutCliEmpresa(String rutCliEmpresa) {
        this.rutCliEmpresa = rutCliEmpresa;
    }

    public Integer getExtranjeroCliEmpresa() {
        return extranjeroCliEmpresa;
    }

    public void setExtranjeroCliEmpresa(Integer extranjeroCliEmpresa) {
        this.extranjeroCliEmpresa = extranjeroCliEmpresa;
    }

    public String getRazonSocialCliEmpresa() {
        return razonSocialCliEmpresa;
    }

    public void setRazonSocialCliEmpresa(String razonSocialCliEmpresa) {
        this.razonSocialCliEmpresa = razonSocialCliEmpresa;
    }

    public String getDireccionCliEmpresa() {
        return direccionCliEmpresa;
    }

    public void setDireccionCliEmpresa(String direccionCliEmpresa) {
        this.direccionCliEmpresa = direccionCliEmpresa;
    }

    public String getNotaCliEmpresa() {
        return notaCliEmpresa;
    }

    public void setNotaCliEmpresa(String notaCliEmpresa) {
        this.notaCliEmpresa = notaCliEmpresa;
    }

    public String getFacebookCliEmpresa() {
        return facebookCliEmpresa;
    }

    public void setFacebookCliEmpresa(String facebookCliEmpresa) {
        this.facebookCliEmpresa = facebookCliEmpresa;
    }

    public String getTwitterCliEmpresa() {
        return twitterCliEmpresa;
    }

    public void setTwitterCliEmpresa(String twitterCliEmpresa) {
        this.twitterCliEmpresa = twitterCliEmpresa;
    }

    public Date getFechaCadPlan() {
        return fechaCadPlan;
    }

    public void setFechaCadPlan(Date fechaCadPlan) {
        this.fechaCadPlan = fechaCadPlan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public PlanCliente getIdPlanCliente() {
        return idPlanCliente;
    }

    public void setIdPlanCliente(PlanCliente idPlanCliente) {
        this.idPlanCliente = idPlanCliente;
    }

    public Giro getIdGiro() {
        return idGiro;
    }

    public void setIdGiro(Giro idGiro) {
        this.idGiro = idGiro;
    }

    public EstadoCliente getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(EstadoCliente idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
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
        hash += (idCliEmpresa != null ? idCliEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliEmpresa)) {
            return false;
        }
        CliEmpresa other = (CliEmpresa) object;
        if ((this.idCliEmpresa == null && other.idCliEmpresa != null) || (this.idCliEmpresa != null && !this.idCliEmpresa.equals(other.idCliEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.CliEmpresa[ idCliEmpresa=" + idCliEmpresa + " ]";
    }
    
}
