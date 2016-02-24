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
                name = "Regiones.findAll",
                query = "select r from Regiones r"
        ),
       @NamedQuery(
        name = "Regiones.findAllWithRelationships",
          query = "SELECT r from Regiones r " +
                "inner join fetch r.provinciasList p " +
                "where r.region_id = :region_id "

        )
})
public class Regiones implements Serializable {
    private Integer region_id;
    private String region_nombre;
    private String region_ordinal;

    // relaciones
    private List<Provincias> provinciasList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    @Basic
    @Column(name = "region_nombre")
    public String getRegion_nombre() {
        return region_nombre;
    }

    public void setRegion_nombre(String region_nombre) {
        this.region_nombre = region_nombre;
    }
    @Basic
    @Column(name = "region_ordinal")

    public String getRegion_ordinal() {
        return region_ordinal;
    }

    public void setRegion_ordinal(String region_ordinal) {
        this.region_ordinal = region_ordinal;
    }
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    @OneToMany(fetch = FetchType.LAZY)
    public List<Provincias> getProvinciasList() {
        return provinciasList;
    }

    public void setProvinciasList(List<Provincias> provinciasList) {
        this.provinciasList = provinciasList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Regiones regiones = (Regiones) o;

        if (this.getRegion_id() == null || regiones.getRegion_id() == null) return false;
        else return this.getRegion_id().intValue() == regiones.getRegion_id().intValue();
    }

    @Override
    public int hashCode() {
        return region_id != null ? 31 * region_id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Regiones{" +
                "region_id=" + region_id +
                '}';
    }
}
