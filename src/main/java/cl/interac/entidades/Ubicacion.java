package cl.interac.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
public class Ubicacion {
    private int idubicacion;
    private int idtotem;
    private String descubicacion;

    @Id
    @Column(name = "idubicacion")
    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }

    @Basic
    @Column(name = "idtotem")
    public int getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(int idtotem) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ubicacion ubicacion = (Ubicacion) o;

        if (idubicacion != ubicacion.idubicacion) return false;
        if (idtotem != ubicacion.idtotem) return false;
        if (descubicacion != null ? !descubicacion.equals(ubicacion.descubicacion) : ubicacion.descubicacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idubicacion;
        result = 31 * result + idtotem;
        result = 31 * result + (descubicacion != null ? descubicacion.hashCode() : 0);
        return result;
    }
}
