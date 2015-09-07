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
                @NamedQuery(
                        name = "Campana.findAllWithRelationships",
                        query = "SELECT c FROM Campana c " +
                                "INNER JOIN FETCH c.cliente cli"
                )
        }
)
public class Campana implements Serializable {
    private Integer idcampana;
    private Date fechaCreacion;
    private String nombre;
    private Date fechaFin;
    private Date fechaInicio;
    private Integer pasadas;

    // relaciones
    private List<Anuncio> anuncios;

    private Usuario cliente;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcampana")
    public Integer getIdcampana() {
        return idcampana;
    }

    public void setIdcampana(Integer idcampana) {
        this.idcampana = idcampana;
    }

    @Basic
    @Column(name = "fechacreacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre==null){
          String  nom="eraUnaCampanaMuyChiquita" ;
         setNombre(nom);
           }

        this.nombre = nombre;
    }
    @Basic
    @Column(name = "fechafin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    @Basic
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

    @JoinColumn(name = "idcliente", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario usuario) {
        this.cliente = usuario;
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
