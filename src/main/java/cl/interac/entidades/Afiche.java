package cl.interac.entidades;
import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by luis on 27-05-2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "afiche.findAll", query = "select a from Afiche a"),
        @NamedQuery(name= "afiche.findById",query ="select a.idAfiche from Afiche a" )

})
public class Afiche implements Serializable {
    private Integer idAfiche;
    private String path;

    //relaciones
    private Anuncio anuncio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAfiche", nullable = false, updatable = false, insertable = true)

    public Integer getIdAfiche() {
        return idAfiche;
    }

    public void setIdAfiche(Integer idAfiche) {
        this.idAfiche = idAfiche;
    }

    @Basic
    @Column(name = "path", nullable = true, updatable = true, insertable = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @OneToOne(mappedBy = "afiche")
    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}
