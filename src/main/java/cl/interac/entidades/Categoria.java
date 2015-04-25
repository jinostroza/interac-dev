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
public class Categoria implements Serializable {
    private Integer idcategoria;
    private String desccategoria;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (idcategoria != null ? !idcategoria.equals(categoria.idcategoria) : categoria.idcategoria != null)
            return false;
        if (desccategoria != null ? !desccategoria.equals(categoria.desccategoria) : categoria.desccategoria != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcategoria != null ? idcategoria.hashCode() : 0;
        result = 31 * result + (desccategoria != null ? desccategoria.hashCode() : 0);
        return result;
    }
}
