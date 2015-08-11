package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Totem.findAll", query = "SELECT t FROM Totem t "),
                @NamedQuery(name="Totem.findWithRelationship",
                        query="SELECT t FROM Totem t " +
                        "left join fetch t.establecimiento e "
                                       )
        }
)
public class Totem implements Serializable {
    private Integer idtotem;
    private String noserie;
    private String tipo;

    // relaciones
    private List<Campana> campanas;
    private Establecimiento establecimiento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtotem")
    public Integer getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(Integer idtotem) {
        this.idtotem = idtotem;
    }


    @Basic
    @Column(name = "Noserie")
    public String getNoserie() {
        return noserie;
    }

    public void setNoserie(String noserie) {
        this.noserie = noserie;
    }

    @Basic
    @Column(name = "tipo", insertable = true, nullable = true, length = 20)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @OneToMany(mappedBy = "totem")
    public List<Campana> getCampanas() {
        return campanas;
    }

    public void setCampanas(List<Campana> campanas) {
        this.campanas = campanas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idestablecimiento", nullable = false, insertable = false, updatable = false)
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Totem totem = (Totem) o;

        if (this.getIdtotem() == null || totem.getIdtotem() == null) return false;
        else return this.getIdtotem().intValue() == totem.getIdtotem().intValue();
    }

    @Override
    public int hashCode() {
        return idtotem != null ? 31 * idtotem.hashCode() : 0;
    }
}
