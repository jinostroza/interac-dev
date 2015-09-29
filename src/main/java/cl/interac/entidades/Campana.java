package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Campana.findAll", query = "SELECT c FROM Campana c "),
                @NamedQuery(name ="Campana.findBycontenido",
                           query="SELECT c FROM Campana c  " +
                                   "INNER JOIN FETCH c.contenido co " +
                                   "INNER JOIN FETCH c.totem to "),
                @NamedQuery(name="Campana.findByUsuario",query = "SELECT c FROM Campana c " +
                        "INNER JOIN FETCH c.contenido co " +
                        "INNER JOIN FETCH c.totem to " +
                        "INNER JOIN FETCH co.usuario u " +
                        "WHERE u.username=:username "),



        }
)
public class Campana implements Serializable {
    private Integer idcampana;
    private Date fechaCreacion;
    private Date fechaFin;
    private Date fechaInicio;
    private Integer pasadas;
    private String nombrecampana;


    // relaciones
    private Contenido contenido;
    private Totem totem;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcampana")
    public Integer getIdcampana() {
        return idcampana;
    }

    public void setIdcampana(Integer idcampana) {
        this.idcampana = idcampana;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fechacreacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fechafin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    @Temporal(TemporalType.DATE)
    @Column(name = "fechainicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    @Basic
    @Column(name = "pasadas")
    public Integer getPasadas() {
        return pasadas;
    }

    public void setPasadas(Integer pasadas) {
        this.pasadas = pasadas;
    }

    @Basic
    @Column(name="nombrecampana")
    public String getNombrecampana() {
        return nombrecampana;
    }

    public void setNombrecampana(String nombrecampana) {
        this.nombrecampana = nombrecampana;
    }


    @JoinColumn(name="idcontenido",referencedColumnName = "idcontenido")
    @ManyToOne(fetch=FetchType.LAZY)
    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }


    @JoinColumn(name="idtotem",referencedColumnName = "idtotem")
    @ManyToOne(fetch=FetchType.LAZY)
    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campana campana = (Campana) o;

        if (this.getIdcampana() == null || campana.getIdcampana() == null) return false;
        else return this.getIdcampana().intValue() == campana.getIdcampana().intValue();
    }

    @Override
    public int hashCode() {
        return idcampana != null ? 31 * idcampana.hashCode() : 0;
    }
}
