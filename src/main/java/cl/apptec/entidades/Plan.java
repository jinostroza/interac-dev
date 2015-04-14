/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.entidades;

import java.io.Serializable;
import java.util.List;
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
 * @author apptec
 */
@Entity
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findByIdPlan", query = "SELECT p FROM Plan p WHERE p.idPlan = :idPlan"),
    @NamedQuery(name = "Plan.findByNombrePlan", query = "SELECT p FROM Plan p WHERE p.nombrePlan = :nombrePlan"),
    @NamedQuery(name = "Plan.findByDescripcionPlan", query = "SELECT p FROM Plan p WHERE p.descripcionPlan = :descripcionPlan"),
    @NamedQuery(name = "Plan.findByActivoPlan", query = "SELECT p FROM Plan p WHERE p.activoPlan = :activoPlan")})
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plan")
    private Integer idPlan;
    @Size(max = 50)
    @Column(name = "nombre_plan")
    private String nombrePlan;
    @Size(max = 100)
    @Column(name = "descripcion_plan")
    private String descripcionPlan;
    @Column(name = "activo_plan")
    private Boolean activoPlan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan")
    private List<ClienteApptec> clienteApptecList;

    public Plan() {
    }

    public Plan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getDescripcionPlan() {
        return descripcionPlan;
    }

    public void setDescripcionPlan(String descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
    }

    public Boolean getActivoPlan() {
        return activoPlan;
    }

    public void setActivoPlan(Boolean activoPlan) {
        this.activoPlan = activoPlan;
    }

    @XmlTransient
    public List<ClienteApptec> getClienteApptecList() {
        return clienteApptecList;
    }

    public void setClienteApptecList(List<ClienteApptec> clienteApptecList) {
        this.clienteApptecList = clienteApptecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.Plan[ idPlan=" + idPlan + " ]";
    }
    
}
