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
public class Ubicacion implements Serializable {
    private Integer idubicacion;
    private Integer idtotem;
    private String descubicacion;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ubicacion ubicacion = (Ubicacion) o;

        if (idubicacion != null ? !idubicacion.equals(ubicacion.idubicacion) : ubicacion.idubicacion != null)
            return false;
        if (idtotem != null ? !idtotem.equals(ubicacion.idtotem) : ubicacion.idtotem != null) return false;
        if (descubicacion != null ? !descubicacion.equals(ubicacion.descubicacion) : ubicacion.descubicacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idubicacion != null ? idubicacion.hashCode() : 0;
        result = 31 * result + (idtotem != null ? idtotem.hashCode() : 0);
        result = 31 * result + (descubicacion != null ? descubicacion.hashCode() : 0);
        return result;
    }
}
