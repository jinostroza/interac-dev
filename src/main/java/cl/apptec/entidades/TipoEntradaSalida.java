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
@Table(name = "tipo_entrada_salida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEntradaSalida.findAll", query = "SELECT t FROM TipoEntradaSalida t"),
    @NamedQuery(name = "TipoEntradaSalida.findByIdTipoEntradaSalida", query = "SELECT t FROM TipoEntradaSalida t WHERE t.idTipoEntradaSalida = :idTipoEntradaSalida"),
    @NamedQuery(name = "TipoEntradaSalida.findByNombreTipoEntradaSalida", query = "SELECT t FROM TipoEntradaSalida t WHERE t.nombreTipoEntradaSalida = :nombreTipoEntradaSalida"),
    @NamedQuery(name = "TipoEntradaSalida.findByDescripcionTipoEntradaSalida", query = "SELECT t FROM TipoEntradaSalida t WHERE t.descripcionTipoEntradaSalida = :descripcionTipoEntradaSalida"),
    @NamedQuery(name = "TipoEntradaSalida.findByActivoTipoEntradaSalida", query = "SELECT t FROM TipoEntradaSalida t WHERE t.activoTipoEntradaSalida = :activoTipoEntradaSalida")})
public class TipoEntradaSalida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_entrada_salida")
    private Integer idTipoEntradaSalida;
    @Size(max = 50)
    @Column(name = "nombre_tipo_entrada_salida")
    private String nombreTipoEntradaSalida;
    @Size(max = 100)
    @Column(name = "descripcion_tipo_entrada_salida")
    private String descripcionTipoEntradaSalida;
    @Column(name = "activo_tipo_entrada_salida")
    private Integer activoTipoEntradaSalida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEntradaSalida")
    private List<EntradaSalida> entradaSalidaList;

    public TipoEntradaSalida() {
    }

    public TipoEntradaSalida(Integer idTipoEntradaSalida) {
        this.idTipoEntradaSalida = idTipoEntradaSalida;
    }

    public Integer getIdTipoEntradaSalida() {
        return idTipoEntradaSalida;
    }

    public void setIdTipoEntradaSalida(Integer idTipoEntradaSalida) {
        this.idTipoEntradaSalida = idTipoEntradaSalida;
    }

    public String getNombreTipoEntradaSalida() {
        return nombreTipoEntradaSalida;
    }

    public void setNombreTipoEntradaSalida(String nombreTipoEntradaSalida) {
        this.nombreTipoEntradaSalida = nombreTipoEntradaSalida;
    }

    public String getDescripcionTipoEntradaSalida() {
        return descripcionTipoEntradaSalida;
    }

    public void setDescripcionTipoEntradaSalida(String descripcionTipoEntradaSalida) {
        this.descripcionTipoEntradaSalida = descripcionTipoEntradaSalida;
    }

    public Integer getActivoTipoEntradaSalida() {
        return activoTipoEntradaSalida;
    }

    public void setActivoTipoEntradaSalida(Integer activoTipoEntradaSalida) {
        this.activoTipoEntradaSalida = activoTipoEntradaSalida;
    }

    @XmlTransient
    public List<EntradaSalida> getEntradaSalidaList() {
        return entradaSalidaList;
    }

    public void setEntradaSalidaList(List<EntradaSalida> entradaSalidaList) {
        this.entradaSalidaList = entradaSalidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEntradaSalida != null ? idTipoEntradaSalida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEntradaSalida)) {
            return false;
        }
        TipoEntradaSalida other = (TipoEntradaSalida) object;
        if ((this.idTipoEntradaSalida == null && other.idTipoEntradaSalida != null) || (this.idTipoEntradaSalida != null && !this.idTipoEntradaSalida.equals(other.idTipoEntradaSalida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.apptec.entidades.TipoEntradaSalida[ idTipoEntradaSalida=" + idTipoEntradaSalida + " ]";
    }
    
}
