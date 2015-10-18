package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Pedro Pablo on 20-05-2015.
 */


@Entity
@NamedQueries({
        @NamedQuery(name = "establecimiento.findAll", query = "select e from Establecimiento e"),
        @NamedQuery(name = "estabecimiento.findAllByusuario",
                query = "select e from Establecimiento e " +
                        "inner join fetch e.usuario " +
                        "inner join fetch e.ubicacion"),

        @NamedQuery(name = "establecimiento.findIdestablecimiento",
                query = "SELECT e from Establecimiento e where e.idEstablecimiento = :estable"),
        @NamedQuery(name= "establecimiento.findbyUser",
                        query="SELECT e FROM Establecimiento e " +
                                "INNER JOIN FETCH e.ubicacion ub " +
                                "INNER JOIN FETCH e.usuario u " +
                                "INNER JOIN FETCH e.totem t " +
                                "WHERE u.username=:username "),

})

public class Establecimiento implements Serializable {
    private Integer idEstablecimiento;
    private String nombreEstablecimiento;
    private String direccion;
    private String fono;
    private Double lat;
    private Double longi;


    // Relaciones
    private Usuario usuario;
    private List<Totem> totem;
    private Ubicacion ubicacion;


    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @OneToMany(mappedBy = "establecimiento")
    public List<Totem> getTotem() {
        return totem;
    }

    public void setTotem(List<Totem> totem) {
        this.totem = totem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestablecimiento", nullable = false, insertable = true, updatable = false)
    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }


    @Basic
    @Column(name = "desestablecimiento",insertable = true,length = 200)
    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    @Basic
    @Column
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column
    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    @Basic
    @Column(name = "latitud")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "longitud")
    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    @JoinColumn(name = "idubicacion", referencedColumnName = "idubicacion")
    @ManyToOne(fetch = FetchType.LAZY)
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Establecimiento establecimiento = (Establecimiento) o;

        if (this.getIdEstablecimiento() == null || establecimiento.getIdEstablecimiento() == null) return false;
        else return this.getIdEstablecimiento().intValue() == establecimiento.getIdEstablecimiento().intValue();
    }

    @Override
    public int hashCode() {
        return idEstablecimiento != null ? 31 * idEstablecimiento.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Establecimiento{" +
                "idEstablecimiento=" + idEstablecimiento +
                '}';
    }
}
