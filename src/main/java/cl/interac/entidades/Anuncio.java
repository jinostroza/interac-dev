package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Anuncio.findAll", query = "SELECT a FROM Anuncio a "),
        }
)
public class Anuncio implements Serializable {
    private Integer idAnuncio;
    private String descanuncio;
    private String media;
    private String rubro;

    @Id
    @Column(name = "id_anuncio", nullable = false, insertable = true, updatable = true)
    public Integer getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    @Basic
    @Column(name = "descanuncio", nullable = true, insertable = true, updatable = true, length = 45)
    public String getDescanuncio() {
        return descanuncio;
    }

    public void setDescanuncio(String descanuncio) {
        this.descanuncio = descanuncio;
    }

    @Basic
    @Column(name = "media", nullable = true, insertable = true, updatable = true, length = 45)
    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Basic
    @Column(name = "rubro", nullable = true, insertable = true, updatable = true, length = 45)
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anuncio anuncio = (Anuncio) o;

        if (this.getIdAnuncio() == null || anuncio.getIdAnuncio() == null) return false;
        return this.getIdAnuncio().intValue() == anuncio.getIdAnuncio().intValue();
    }

    @Override
    public int hashCode() {
        return idAnuncio != null ? 31 * idAnuncio.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "idAnuncio=" + idAnuncio +
                '}';
    }
}
