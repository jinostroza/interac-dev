package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Provincias.findAll",
                query = "select p from Provincias p"
        ),
        @NamedQuery(
                name = "Provincias.findAllWithRelationships",
                query = "SELECT p from Provincias p " +
                        "inner join fetch p.regiones r " +
                        "where r.region_id = :region_id "

        )
})
public class Provincias implements Serializable {
    private Integer provincia_id;
    private String provincia_nombre;


    // relaciones
   private Regiones regiones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provincia_id")
    public Integer getProvincia_id() {
        return provincia_id;
    }

    public void setProvincia_id(Integer provincia_id) {
        this.provincia_id = provincia_id;
    }

    @Basic
    @Column(name = "provincia_nombre")
    public String getProvincia_nombre() {
        return provincia_nombre;
    }

    public void setProvincia_nombre(String provincia_nombre) {
        this.provincia_nombre = provincia_nombre;
    }
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    @ManyToOne(fetch = FetchType.LAZY)
       public Regiones getRegiones() {
        return regiones;
    }

    public void setRegiones(Regiones regiones) {
        this.regiones = regiones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provincias provincias = (Provincias) o;

        if (this.getProvincia_id() == null || provincias.getProvincia_id() == null) return false;
        else return this.getProvincia_id().intValue() == provincias.getProvincia_id().intValue();
    }

    @Override
    public int hashCode() {
        return provincia_id != null ? 31 * provincia_id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Provincias{" +
                "provincia_id=" + provincia_id +
                '}';
    }
}
