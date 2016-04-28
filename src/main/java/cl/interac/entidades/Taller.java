package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Joaco on 07-04-2016.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Taller.findAll", query = "select t from Taller t " +
                             "inner join fetch t.usuario u " +
                                "where u.idUsuario=:user"),
        @NamedQuery(
                name = "Taller.findBySede",
                query = "select t from Taller t " +
                        "where t.sede=:sedenom"
        )
})
public class Taller implements Serializable {

    private Integer idtaller;
    private String sede;
    private Date fecha;
    private String hora;
    private String link;
    private String nombre;

    //relaciones
    private Usuario usuario;

    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taller", nullable = false, insertable = true, updatable = false)
    public Integer getIdtaller() {
        return idtaller;
    }

    public void setIdtaller(Integer idtaller) {
        this.idtaller = idtaller;
    }

    @Basic
    @Column(name = "sede", nullable = true, insertable = true, updatable = true)
    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
    @Basic
    @Column(name = "link", nullable = true, insertable = true, updatable = true)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    @Basic
    @Column(name = "hora", nullable = true, insertable = true, updatable = true)
    public String getHora() {

        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    @Basic
    @Column(name = "fecha", nullable = true, insertable = true, updatable = true)
    public Date getFecha() {

        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @Basic
    @Column(name = "nombre", nullable = true, insertable = true, updatable = true)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taller taller = (Taller) o;

        if (this.getIdtaller() == null || taller.getIdtaller() == null) return false;
        else return this.getIdtaller().intValue() == taller.getIdtaller().intValue();
    }

    @Override
    public int hashCode() {
        return idtaller != null ? 31 * idtaller.hashCode() : 0;
    }
}

