package cl.interac.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
public class Categoria {
    private int idcategoria;
    private String desccategoria;

    @Id
    @Column(name = "idcategoria")
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
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

        if (idcategoria != categoria.idcategoria) return false;
        if (desccategoria != null ? !desccategoria.equals(categoria.desccategoria) : categoria.desccategoria != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcategoria;
        result = 31 * result + (desccategoria != null ? desccategoria.hashCode() : 0);
        return result;
    }
}
