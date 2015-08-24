package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 17/08/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Contenido.findAll", query = "select c from Contenido c")
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
    @Column(name = "idcontenido", nullable = false, insertable = true, updatable = true)
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

        if (idcontenido != contenido.idcontenido) return false;
        if (path != null ? !path.equals(contenido.path) : contenido.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcontenido;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
