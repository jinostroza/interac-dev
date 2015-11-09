package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Campana.findAll", query = "SELECT c FROM Campana c "),
                @NamedQuery(name ="Campana.findBycontenido",
                           query="SELECT c FROM Campana c  " +
                                   "INNER JOIN FETCH c.contenido co " ),

                @NamedQuery(name="Campana.findByUsuario",query = "SELECT c FROM Campana c " +
                        "INNER JOIN FETCH c.contenido co " +
                        "INNER JOIN FETCH co.usuario u " +
                        "WHERE u.username=:username "),

                @NamedQuery(name="Campana.findByUsuarioAprobado",query = "SELECT c FROM Campana c " +
                        "INNER JOIN FETCH c.contenido co " +
                        "INNER JOIN FETCH co.usuario u " +
                        "WHERE u.username=:username AND co.estado=''"),

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

                @NamedQuery(name="Campana.findByEstado",
                        query="SELECT c FROM Campana c "+
                              "INNER JOIN FETCH c.contenido co " +
                              "INNER JOIN FETCH c.totemList tl " +
                              "INNER JOIN FETCH tl.establecimiento e  " +
                              "INNER JOIN FETCH e.usuario u " +
                              " WHERE u.username=:username AND co.estado='Validando'"

                           ),
                @NamedQuery(name="Campana.count",
                        query="SELECT COUNT (c.idcampana) FROM Campana c "+
                                "INNER JOIN  c.contenido co " +
                                "INNER JOIN  c.totemList tl " +
                                "INNER JOIN  tl.establecimiento e " +
                                "INNER JOIN  e.usuario u "+
                                " WHERE u.username=:username AND co.estado='Validando'"
                ),

                @NamedQuery(name="Campana.findByTotem",
                            query="SELECT c FROM Campana c " +
                                  "RIGHT JOIN FETCH c.totemList tl "+
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


    // relaciones
    private Contenido contenido;
    private List<Totem> totemList;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "campatotem",
            inverseJoinColumns = {
                    @JoinColumn(name = "idtotem", referencedColumnName = "idtotem")
            },
            joinColumns = {
                    @JoinColumn(name = "idcampana" , referencedColumnName = "idcampana")
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
