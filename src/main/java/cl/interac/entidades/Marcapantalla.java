package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by PPablo on 09-11-2015.
 */
@Entity

public class Marcapantalla implements Serializable {
    private Integer idmarca;
    private String nombre;
    private List<Totem> totem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarca" , nullable = false, insertable = true, updatable = true)
    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "marcaPantalla")
    public List<Totem> getTotem() {
        return totem;
    }

    public void setTotem(List<Totem> totem) {
        this.totem = totem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       Marcapantalla marcapantalla = (Marcapantalla) o;

        if (this.getIdmarca() == null || marcapantalla.getIdmarca() == null) return false;
        else return this.getIdmarca().intValue() == marcapantalla.getIdmarca().intValue();
    }

    @Override
    public int hashCode() {
        return idmarca != null ? 31 * idmarca.hashCode() : 0;
    }
}