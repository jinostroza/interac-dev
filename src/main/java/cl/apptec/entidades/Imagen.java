package cl.apptec.entidades;

import javax.persistence.*;
import java.util.List;

/**
 * Created by claudio on 17-10-14.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "findAll",
                query = "SELECT i FROM Imagen i"
        )
})
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;
    @Basic
    @Column(name = "url_imagen")
    private String urlImagen;
    @Basic
    @Column(name = "imagen_activa")
    private Boolean imagenActiva;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getImagenActiva() {
        return imagenActiva;
    }

    public void setImagenActiva(Boolean imagenActiva) {
        this.imagenActiva = imagenActiva;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imagen imagen = (Imagen) o;

        if (this.getIdImagen() == null || imagen.getIdImagen() == null) return false;
        else if (this.getIdImagen().intValue() != imagen.getIdImagen().intValue()) return false;
        else return true;
    }

    @Override
    public int hashCode() {
        return idImagen != null ? idImagen.hashCode() : 0;
    }
}
