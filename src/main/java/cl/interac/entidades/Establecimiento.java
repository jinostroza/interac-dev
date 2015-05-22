package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Pedro Pablo on 20-05-2015.
 */
@Entity
@NamedQueries(
        @NamedQuery(
                name = "Establecimiento.findAll",
                query = "SELECT es FROM Establecimiento es"
        )
)
public class Establecimiento implements Serializable {
    private Integer idEstablecimiento;
    private String nombreEstablecimiento;
    private String direccion;
    private String comuna;
    private String region;
    private String fono;

    // Relaciones
    private Usuario usuario;

    // Propiedades de las columnas
    // Metodos GET y SET
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstablecimiento", nullable = false, insertable = true, updatable = true)
    public Integer getIdEstablecimiento() {return idEstablecimiento;}
    public void setIdEstablecimiento(Integer idEstablecimiento) {this.idEstablecimiento = idEstablecimiento;}

    @Basic
    @Column(name = "nombreEstablecimiento", nullable = true, insertable = true, updatable = true)
    public String getNombreEstablecimiento() {return nombreEstablecimiento;}
    public void setNombreEstablecimiento(String nombreEstablecimiento) {this.nombreEstablecimiento = nombreEstablecimiento;}

    @Basic
    @Column(name = "direccion", nullable = true, insertable = true, updatable = true)
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    @Basic
    @Column(name = "comuna", nullable = true, insertable = true, updatable = true)
    public String getComuna() {return comuna;}
    public void setComuna(String comuna) {this.comuna = comuna;}

    @Basic
    @Column(name = "region", nullable = true, insertable = true, updatable = true)
    public String getRegion() {return region;}
    public void setRegion(String region) {this.region = region;}

    @Basic
    @Column(name = "fono", nullable = true, insertable = true, updatable = true)
    public String getFono() {return fono;}
    public void setFono(String fono) {this.fono = fono;}

    @Override
    public int hashCode() {return idEstablecimiento != null ? 31 * idEstablecimiento.hashCode() : 0;}
}
