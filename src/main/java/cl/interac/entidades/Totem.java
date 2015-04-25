package cl.interac.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
public class Totem implements Serializable {
    private Integer idtotem;
    private String nombre;
    private Integer idubicacion;
    private String tipo;

    @Id
    @Column(name = "idtotem")
    public Integer getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(Integer idtotem) {
        this.idtotem = idtotem;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "idubicacion")
    public Integer getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Totem totem = (Totem) o;

        if (idtotem != null ? !idtotem.equals(totem.idtotem) : totem.idtotem != null) return false;
        if (nombre != null ? !nombre.equals(totem.nombre) : totem.nombre != null) return false;
        if (idubicacion != null ? !idubicacion.equals(totem.idubicacion) : totem.idubicacion != null) return false;
        if (tipo != null ? !tipo.equals(totem.tipo) : totem.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtotem != null ? idtotem.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (idubicacion != null ? idubicacion.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }
}
