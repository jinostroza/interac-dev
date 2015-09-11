package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 17/08/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Contenido.findAll", query = "select c from Contenido c"),
        @NamedQuery(name = "Contenido.findWith", query = "select c from Contenido c,Usuario u "+" where c.usuario.idUsuario=u.idUsuario "),

        @NamedQuery(name="Contenido.findByUsuario",
                query="SELECT c FROM Contenido c " +
                        "inner join c.usuario u " +
                         " where u.username=:user "
        )


})
public class Contenido implements Serializable{
    private Integer idcontenido;
    private String path;

    //relaciones
    private Usuario usuario;
    private List<Anuncio> anuncios;

    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idcontenido", nullable = false)
    public Integer getIdcontenido() {
        return idcontenido;
    }

    public void setIdcontenido(Integer idcontenido) {
        this.idcontenido = idcontenido;
    }

    @Basic
    @Column(name = "path", nullable = false, insertable = true, updatable = true, length = 1000)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @OneToMany(mappedBy = "contenido")
    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contenido contenido = (Contenido) o;

        if (this.getIdcontenido() == null || contenido.getIdcontenido() == null) return false;
        else return this.getIdcontenido().intValue() == contenido.getIdcontenido().intValue();
    }

    @Override
    public int hashCode() {
        return idcontenido != null ? 31 * idcontenido.hashCode() : 0;
    }


}
