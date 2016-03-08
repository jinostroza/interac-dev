package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by PPablo on 30-11-2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "empresa.findAll", query = "SELECT emp FROM Empresa emp"),
        @NamedQuery(
        name = "Empresa.findnames",
        query = "SELECT e from Empresa e " +
                "where e.idEmpresa = :empresa "

)
})

public class Empresa implements Serializable {
    private Integer idEmpresa;
    private String nombreEmpresa;
    private String razonSocial;
    private String rut;
    private String direccion;
    private String telefono;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idempresa", nullable=false, insertable=true, updatable=false)
    public Integer getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Integer idEmpresa) { this.idEmpresa = idEmpresa; }

    @Basic
    @Column(name="nombre")
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    @Basic
    @Column(name="razonsocial")
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    @Basic
    @Column(name="rut")
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    @Basic
    @Column(name="direccion")
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Basic
    @Column(name="telefono")
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empresa empresa = (Empresa) o;

        if (this.getIdEmpresa() == null || empresa.getIdEmpresa() == null) return false;
        else return this.getIdEmpresa().intValue() == empresa.getIdEmpresa().intValue();
    }
    @Override
    public int hashCode() {
        return idEmpresa != null ? 31 * idEmpresa.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Empresa" +
                "{" +
                "idEmpresa =" + idEmpresa +
                '}';
    }
}
