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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matias Harding
 */
@Entity
@Table(name = "cli_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CliPersona.findAll", query = "SELECT cp FROM CliPersona cp"),
    @NamedQuery(name = "CliPersona.findByIdCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.idCliPersona = :idCliPersona"),
    @NamedQuery(name = "CliPersona.findByRutCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.rutCliPersona = :rutCliPersona"),
    @NamedQuery(name = "CliPersona.findByExtranjeroCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.extranjeroCliPersona = :extranjeroCliPersona"),
    @NamedQuery(name = "CliPersona.findByNombreCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.nombreCliPersona = :nombreCliPersona"),
    @NamedQuery(name = "CliPersona.findByApellidoCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.apellidoCliPersona = :apellidoCliPersona"),
    @NamedQuery(name = "CliPersona.findByDireccionCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.direccionCliPersona = :direccionCliPersona"),
    @NamedQuery(name = "CliPersona.findByFonoCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.fonoCliPersona = :fonoCliPersona"),
    @NamedQuery(name = "CliPersona.findByEmailCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.emailCliPersona = :emailCliPersona"),
    @NamedQuery(name = "CliPersona.findByNotaCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.notaCliPersona = :notaCliPersona"),
    @NamedQuery(name = "CliPersona.findByFacebookCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.facebookCliPersona = :facebookCliPersona"),
    @NamedQuery(name = "CliPersona.findByTwitterCliPersona", query = "SELECT cp FROM CliPersona cp WHERE cp.twitterCliPersona = :twitterCliPersona"),
    @NamedQuery(name = "CliPersona.findByFechaCadPlan", query = "SELECT cp FROM CliPersona cp WHERE cp.fechaCadPlan = :fechaCadPlan"),
    @NamedQuery(name = "CliPersona.findByProyecto", query = "SELECT cp FROM CliPersona cp WHERE cp.idProyecto = :proyecto")
})
public class CliPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cli_persona")
    private Integer idCliPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "rut_cli_persona")
    private String rutCliPersona;
    @Column(name = "extranjero_cli_persona")
    private Integer extranjeroCliPersona;
    @Size(max = 2147483647)
    @Column(name = "nombre_cli_persona")
    private String nombreCliPersona;
    @Size(max = 2147483647)
    @Column(name = "apellido_cli_persona")
    private String apellidoCliPersona;
    @Size(max = 2147483647)
    @Column(name = "direccion_cli_persona")
    private String direccionCliPersona;
    @Size(max = 2147483647)
    @Column(name = "fono_cli_persona")
    private String fonoCliPersona;
    @Size(max = 2147483647)
    @Column(name = "email_cli_persona")
    private String emailCliPersona;
    @Size(max = 2147483647)
    @Column(name = "nota_cli_persona")
    private String notaCliPersona;
    @Size(max = 2147483647)
    @Column(name = "facebook_cli_persona")
    private String facebookCliPersona;
    @Size(max = 2147483647)
    @Column(name = "twitter_cli_persona")
    private String twitterCliPersona;
    @Column(name = "fecha_cad_plan")
    @Temporal(TemporalType.DATE)
    private Date fechaCadPlan;
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

    public CliPersona() {
    }

    public CliPersona(Integer idCliPersona) {
        this.idCliPersona = idCliPersona;
    }

    public CliPersona(Integer idCliPersona, String rutCliPersona) {
        this.idCliPersona = idCliPersona;
        this.rutCliPersona = rutCliPersona;
    }

    public Integer getIdCliPersona() {
        return idCliPersona;
    }

    public void setIdCliPersona(Integer idCliPersona) {
        this.idCliPersona = idCliPersona;
    }

    public String getRutCliPersona() {
        return rutCliPersona;
    }

    public void setRutCliPersona(String rutCliPersona) {
        this.rutCliPersona = rutCliPersona;
    }

    public Integer getExtranjeroCliPersona() {
        return extranjeroCliPersona;
    }

    public void setExtranjeroCliPersona(Integer extranjeroCliPersona) {
        this.extranjeroCliPersona = extranjeroCliPersona;
    }

    public String getNombreCliPersona() {
        return nombreCliPersona;
    }

    public void setNombreCliPersona(String nombreCliPersona) {
        this.nombreCliPersona = nombreCliPersona;
    }

    public String getApellidoCliPersona() {
        return apellidoCliPersona;
    }

    public void setApellidoCliPersona(String apellidoCliPersona) {
        this.apellidoCliPersona = apellidoCliPersona;
    }

    public String getDireccionCliPersona() {
        return direccionCliPersona;
    }

    public void setDireccionCliPersona(String direccionCliPersona) {
        this.direccionCliPersona = direccionCliPersona;
    }

    public String getFonoCliPersona() {
        return fonoCliPersona;
    }

    public void setFonoCliPersona(String fonoCliPersona) {
        this.fonoCliPersona = fonoCliPersona;
    }

    public String getEmailCliPersona() {
        return emailCliPersona;
    }

    public void setEmailCliPersona(String emailCliPersona) {
        this.emailCliPersona = emailCliPersona;
    }

    public String getNotaCliPersona() {
        return notaCliPersona;
    }

    public void setNotaCliPersona(String notaCliPersona) {
        this.notaCliPersona = notaCliPersona;
    }

    public String getFacebookCliPersona() {
        return facebookCliPersona;
    }

    public void setFacebookCliPersona(String facebookCliPersona) {
        this.facebookCliPersona = facebookCliPersona;
    }

    public String getTwitterCliPersona() {
        return twitterCliPersona;
    }

    public void setTwitterCliPersona(String twitterCliPersona) {
        this.twitterCliPersona = twitterCliPersona;
    }

    public Date getFechaCadPlan() {
        return fechaCadPlan;
    }

    public void setFechaCadPlan(Date fechaCadPlan) {
        this.fechaCadPlan = fechaCadPlan;
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
        hash += (idCliPersona != null ? idCliPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliPersona)) {
            return false;
        }
        CliPersona other = (CliPersona) object;
        if ((this.idCliPersona == null && other.idCliPersona != null) || (this.idCliPersona != null && !this.idCliPersona.equals(other.idCliPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.CliPersona[ idCliPersona=" + idCliPersona + " ]";
    }
    
}
