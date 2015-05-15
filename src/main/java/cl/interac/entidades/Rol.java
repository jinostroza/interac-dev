package cl.interac.entidades;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


/**
 * Created by luis on 14-05-2015.
 */

@Entity
@NamedQueries(

        {@NamedQuery(name = "rol.FindAll", query = "select r from Rol r"),
         @NamedQuery(name= "rol.FindIdRol",query = "SELECT r.idrol from Rol r")
        }
)

public class Rol implements Serializable {

     private Integer idrol;
    private String namerol;

    //relaciones
    private List<Usuario> usuario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol", nullable = false,insertable = true,updatable = false)
    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }


    @Basic
    @Column(name ="namerol",nullable = true , insertable = true ,updatable = false)

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
        if (!(o instanceof Rol)) return false;

        Rol rol = (Rol) o;

        if (!idrol.equals(rol.idrol)) return false;
        return usuario.equals(rol.usuario);

    }






    @Override
    public int hashCode() {
        int result = idrol.hashCode();
        result = 31 * result + (namerol != null ? namerol.hashCode() : 0);
        result = 31 * result + usuario.hashCode();
        return result;
    }
}
