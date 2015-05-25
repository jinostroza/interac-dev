package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Ubicacion.findAll",
                query = "select u from Ubicacion u"
        ),
        @NamedQuery(
                name = "Ubicacion.findAllWithRelationships",
                query = "SELECT u from Ubicacion u " +
                        "inner join fetch u.establecimientoList "
        )
})
public class Ubicacion implements Serializable {
    private Integer idubicacion;
    private String descubicacion;

    // relaciones
    private Establecimiento establecimientoList;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idubicacion")
    public Integer getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }


    @Basic
    @Column(name = "descubicacion")
    public String getDescubicacion() {
        return descubicacion;
    }

    public void setDescubicacion(String descubicacion) {
        this.descubicacion = descubicacion;
    }


    @OneToOne(mappedBy = "ubicacion")
    public Establecimiento getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(Establecimiento establecimientoList) {
        this.establecimientoList = establecimientoList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ubicacion ubicacion = (Ubicacion) o;

        if (this.getIdubicacion() == null || ubicacion.getIdubicacion() == null) return false;
        else return this.getIdubicacion().intValue() == ubicacion.getIdubicacion().intValue();
    }

    @Override
    public int hashCode() {
        return idubicacion != null ? 31 * idubicacion.hashCode() : 0;
    }
}
