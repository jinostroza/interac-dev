package cl.interac.entidades;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
                                   "INNER JOIN FETCH c.contenidoList co " ),

                @NamedQuery(name = "Campana.findByUsuario", query = "SELECT c FROM Campana c " +
                        "INNER JOIN FETCH c.contenidoList co " +
                        "INNER JOIN FETCH co.usuario u " +
                        "WHERE u.username=:username "),

                @NamedQuery(name = "Campana.findByUsuarioAprobado", query = "SELECT c FROM Campana c " +
                        "INNER JOIN FETCH c.contenidoList co " +
                        "INNER JOIN FETCH co.usuario u " +
                        "WHERE u.username=:username AND c.estado='Aprobado'"),

                @NamedQuery(
                        name = "Campana.findByIdWithTotems",
                        query = "SELECT c FROM Campana c INNER JOIN FETCH c.totemList WHERE c.id = :id"
                ),

                @NamedQuery(
                        name="Campana.findByTotemsAndCampana",
                        query="SELECT c FROM Campana c " +
                              "INNER JOIN FETCH c.totemList t " +
                              "INNER JOIN FETCH t.establecimiento e " +
                              "WHERE c.id=:id"
                ),

                @NamedQuery(name = "Campana.findByEstado",
                        query = "SELECT c FROM Campana c " +
                                "INNER JOIN FETCH c.contenidoList co " +
                                "INNER JOIN Fetch co.usuario u " +
                                " WHERE u.username=:username AND c.estado='Esperando Aprobacion'"
                ),
                @NamedQuery(name = "Campana.count",
                        query = "SELECT COUNT (c.idcampana) FROM Campana c " +
                                "INNER JOIN  c.establecimientoList e " +
                                "INNER JOIN e.usuario u " +
                                " WHERE u.username=:username AND c.estado='Esperando Aprobacion'"
                ),
                @NamedQuery(name = "Campana.countEstablecimiento",
                        query = "SELECT COUNT (c.idcampana) FROM Campana c " +
                                "INNER JOIN  c.establecimientoList e " +
                                "WHERE e.idEstablecimiento=:establecimiento AND c.estado='Aprobado'"
                ),
                @NamedQuery(name = "Campana.findByDate",
                        query = "SELECT c  FROM Campana c " +
                                "INNER JOIN  c.contenidoList co " +
                                " WHERE c.fechaFin<:fechavencida"
                ),
                @NamedQuery(name = "Campana.findByEstablecimiento", query = "SELECT c FROM Campana c " +
                        "INNER JOIN FETCH c.contenidoList co " +
                        "INNER JOIN FETCH co.usuario u " +
                        "WHERE co.usuario.idUsuario=:iduser"),

                @NamedQuery(name = "Campana.findByTotem",
                        query = "SELECT c FROM Campana c " +
                                "RIGHT JOIN FETCH c.totemList tl " +
                                "RIGHT JOIN FETCH tl.establecimiento e " +
                                "RIGHT JOIN FETCH e.usuario u " +
                                "where u.username=:username "
                )

        }
)
public class Campana implements Serializable {
    private Integer idcampana;
    private Date fechaCreacion;
    private Date fechaFin;
    private Date fechaInicio;
    private Integer pasadas;
    private String nombrecampana;
    private String estado;
    private Integer valor;

    // Relaciones
     private List<Totem> totemList;
    private List<Contenido> contenidoList;

    private List<Establecimiento> establecimientoList;

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
    @Column(name = "nombrecampana")
    public String getNombrecampana() {
        return nombrecampana;
    }

    public void setNombrecampana(String nombrecampana) {
        this.nombrecampana = nombrecampana;
    }

    @Basic
    @Column(name = "estado", nullable = false)
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) { this.estado = estado; }

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(
            name = "campestab",
            inverseJoinColumns = {
                    @JoinColumn(name = "idestablecimiento", referencedColumnName = "idestablecimiento")
            },
            joinColumns = {
                    @JoinColumn(name = "idcampana", referencedColumnName = "idcampana")
            }
    )

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(
            name = "campaconte",
            inverseJoinColumns = {
                    @JoinColumn(name = "idcontenido", referencedColumnName = "idcontenido")
            },
            joinColumns = {
                    @JoinColumn(name = "idcampana", referencedColumnName = "idcampana")
            }
    )
    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    @Basic
    @Column(name = "valor")
    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "campatotem",
            inverseJoinColumns = {
                    @JoinColumn(name = "idtotem", referencedColumnName = "idtotem")
            },
            joinColumns = {
                    @JoinColumn(name = "idcampana", referencedColumnName = "idcampana")
            }
    )
    public List<Totem> getTotemList() {
        return totemList;
    }


    public void setTotemList(List<Totem> totemList) {
        this.totemList = totemList;
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
