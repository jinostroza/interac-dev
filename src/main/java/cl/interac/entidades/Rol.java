package cl.interac.entidades;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


/**
 * Created by luis on 14-05-2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Rol.findAll", query = "select r from Rol r"),


})
public class Rol implements Serializable {

    private Integer idrol;
    private String namerol;

    //relaciones
    private List<Usuario> usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false, insertable = true, updatable = false)
    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }


    @Basic
    @Column(name = "namerol", nullable = true, insertable = true, updatable = false)

    public String getNamerol() {
        return namerol;
    }

    public void setNamerol(String namerol) {
        this.namerol = namerol;
    }


    @OneToMany(mappedBy = "rol")
    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rol rol = (Rol) o;

        if (this.getIdrol() == null || rol.getIdrol() == null) return false;
        else return this.getIdrol().intValue() == rol.getIdrol().intValue();
    }

    @Override
    public int hashCode() {
        return idrol != null ? 31 * idrol.hashCode() : 0;
    }
}

