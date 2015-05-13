package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Ubicacion.findAll",
                query = "select u from Ubicacion u"
        )
})
public class Ubicacion implements Serializable {
    private Integer idubicacion;
    private Integer idtotem;
    private String descubicacion;

    // relaciones
    private Totem totem;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idubicacion")
    public Integer getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }

    @Basic
    @Column(name = "idtotem")
    public Integer getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(Integer idtotem) {
        this.idtotem = idtotem;
    }

    @Basic
    @Column(name = "descubicacion")
    public String getDescubicacion() {
        return descubicacion;
    }

    public void setDescubicacion(String descubicacion) {
        this.descubicacion = descubicacion;
    }

    @OneToOne(mappedBy = "ubicacion", fetch = FetchType.LAZY)
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

        Ubicacion ubicacion = (Ubicacion) o;

        if (this.getIdubicacion() == null || ubicacion.getIdubicacion() == null) return false;
        else return this.getIdubicacion().intValue() == ubicacion.getIdubicacion().intValue();
    }

    @Override
    public int hashCode() {
        return idubicacion != null ? 31 * idubicacion.hashCode() : 0;
    }
}
