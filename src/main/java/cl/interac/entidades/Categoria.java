package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Categoria.findAll", query = "SELECT g FROM Categoria g ")

        }
)
public class Categoria implements Serializable {
    private Integer idcategoria;
    private String desccategoria;

    // relaciones
   private List<Contenido> contenidos;
   private List<Establecimiento> establecimientos;


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Basic
    @Column(name = "desccategoria")
    public String getDesccategoria() {
        return desccategoria;
    }

    public void setDesccategoria(String desccategoria) {
        this.desccategoria = desccategoria;
    }


    @OneToMany(mappedBy="categoria")
    public List<Contenido> getContenidos() {
        return contenidos;
    }
    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    @OneToMany(mappedBy="categoria")
    public List<Establecimiento> getEstablecimientos() { return establecimientos; }
    public void setEstablecimientos(List<Establecimiento> establecimientos) { this.establecimientos = establecimientos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (this.getIdcategoria() == null || categoria.getIdcategoria() == null) return false;
        else return this.getIdcategoria().intValue() == categoria.getIdcategoria().intValue();
    }

    @Override
    public int hashCode() {
        return idcategoria != null ? 31 * idcategoria.hashCode() : 0;
    }
}
