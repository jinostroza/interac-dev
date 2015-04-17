/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "plan_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanCliente.findAll", query = "SELECT p FROM PlanCliente p"),
    @NamedQuery(name = "PlanCliente.findByIdPlanCliente", query = "SELECT p FROM PlanCliente p WHERE p.idPlanCliente = :idPlanCliente"),
    @NamedQuery(name = "PlanCliente.findByNombrePlanCliente", query = "SELECT p FROM PlanCliente p WHERE p.nombrePlanCliente = :nombrePlanCliente"),
    @NamedQuery(name = "PlanCliente.findByDescripcionPlanCliente", query = "SELECT p FROM PlanCliente p WHERE p.descripcionPlanCliente = :descripcionPlanCliente"),
    @NamedQuery(name = "PlanCliente.findByActivoPlanCliente", query = "SELECT p FROM PlanCliente p WHERE p.activoPlanCliente = :activoPlanCliente")})
public class PlanCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plan_cliente")
    private Integer idPlanCliente;
    @Size(max = 2147483647)
    @Column(name = "nombre_plan_cliente")
    private String nombrePlanCliente;
    @Size(max = 2147483647)
    @Column(name = "descripcion_plan_cliente")
    private String descripcionPlanCliente;
    @Column(name = "activo_plan_cliente")
    private Boolean activoPlanCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanCliente")
    private Collection<CliEmpresa> cliEmpresaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanCliente")
    private Collection<CliPersona> cliPersonaCollection;

    public PlanCliente() {
    }

    public PlanCliente(Integer idPlanCliente) {
        this.idPlanCliente = idPlanCliente;
    }

    public Integer getIdPlanCliente() {
        return idPlanCliente;
    }

    public void setIdPlanCliente(Integer idPlanCliente) {
        this.idPlanCliente = idPlanCliente;
    }

    public String getNombrePlanCliente() {
        return nombrePlanCliente;
    }

    public void setNombrePlanCliente(String nombrePlanCliente) {
        this.nombrePlanCliente = nombrePlanCliente;
    }

    public String getDescripcionPlanCliente() {
        return descripcionPlanCliente;
    }

    public void setDescripcionPlanCliente(String descripcionPlanCliente) {
        this.descripcionPlanCliente = descripcionPlanCliente;
    }

    public Boolean getActivoPlanCliente() {
        return activoPlanCliente;
    }

    public void setActivoPlanCliente(Boolean activoPlanCliente) {
        this.activoPlanCliente = activoPlanCliente;
    }

    @XmlTransient
    public Collection<CliEmpresa> getCliEmpresaCollection() {
        return cliEmpresaCollection;
    }

    public void setCliEmpresaCollection(Collection<CliEmpresa> cliEmpresaCollection) {
        this.cliEmpresaCollection = cliEmpresaCollection;
    }

    @XmlTransient
    public Collection<CliPersona> getCliPersonaCollection() {
        return cliPersonaCollection;
    }

    public void setCliPersonaCollection(Collection<CliPersona> cliPersonaCollection) {
        this.cliPersonaCollection = cliPersonaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanCliente != null ? idPlanCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanCliente)) {
            return false;
        }
        PlanCliente other = (PlanCliente) object;
        if ((this.idPlanCliente == null && other.idPlanCliente != null) || (this.idPlanCliente != null && !this.idPlanCliente.equals(other.idPlanCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.PlanCliente[ idPlanCliente=" + idPlanCliente + " ]";
    }
    
}
