package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by PPablo on 09-11-2015.
 */
@Entity
@Table(name = "marcapantalla", schema = "public", catalog = "interac-dev")
public class Marcapantalla implements Serializable {
    private int idmarca;
    private String nombre;
    private List<Totem> totem;

    @Id
    @Column(name = "idmarca")
    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
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
    public List<Totem> getTotem() { return totem; }
    public void setTotem(List<Totem> totem) { this.totem = totem; }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marcapantalla that = (Marcapantalla) o;

        if (idmarca != that.idmarca) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idmarca;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
