package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Comunas.findAll",
                query = "select c from Comunas c"
        )        ,
        @NamedQuery(
                name = "Comunas.findAllWithRelationships",
                query = "SELECT c from Comunas c " +
                        "inner join fetch c.provincias pr " +
                        "where pr.provincia_id = :provincias_id "

        )
})
public class Comunas implements Serializable {
    private Integer comuna_id;
    private String comuna_nombre;

    // relaciones
    private Provincias provincias;


    @Basic
    @Column(name = "comuna_nombre")
    public String getComuna_nombre() {

        return comuna_nombre;
    }

    public void setComuna_nombre(String comuna_nombre) {
        this.comuna_nombre = comuna_nombre;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comuna_id")
    public Integer getComuna_id() {

        return comuna_id;
    }

    public void setComuna_id(Integer comuna_id) {
        this.comuna_id = comuna_id;
    }
    @JoinColumn(name = "provincia_id", referencedColumnName = "provincia_id")
    @ManyToOne(fetch = FetchType.LAZY)
    public Provincias getProvincias() {
        return provincias;
    }

    public void setProvincias(Provincias provincias) {
        this.provincias = provincias;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comunas comunas = (Comunas) o;

        if (this.getComuna_id() == null || comunas.getComuna_id() == null) return false;
        else return this.getComuna_id().intValue() == comunas.getComuna_id().intValue();
    }

    @Override
    public int hashCode() {
        return comuna_id != null ? 31 * comuna_id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Comunas{" +
                "comuna_id=" + comuna_id +
                '}';
    }
}
