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

        if (idAnuncio != null ? !idAnuncio.equals(anuncio.idAnuncio) : anuncio.idAnuncio != null) return false;
        if (descanuncio != null ? !descanuncio.equals(anuncio.descanuncio) : anuncio.descanuncio != null) return false;
        if (media != null ? !media.equals(anuncio.media) : anuncio.media != null) return false;
        if (rubro != null ? !rubro.equals(anuncio.rubro) : anuncio.rubro != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAnuncio != null ? idAnuncio.hashCode() : 0;
        result = 31 * result + (descanuncio != null ? descanuncio.hashCode() : 0);
        result = 31 * result + (media != null ? media.hashCode() : 0);
        result = 31 * result + (rubro != null ? rubro.hashCode() : 0);
        return result;
    }
}
