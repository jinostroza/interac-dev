package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luisPc on 15-08-2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name="Tipototem.findAll",query="SELECT t From Tipototem t "),
        @NamedQuery(name="Tipototem.findWithRelationship",
                         query="select t from Tipototem t " +
                        "left join fetch t.totems " )


})
public class Tipototem implements Serializable {
   private Integer idtipo;
    private String destipo;




    // relaciones
   private List<Totem> totems ;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo")
    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }


    @Basic
    @Column(name = "destipo")
    public String getDestipo() {
        return destipo;
    }

    public void setDestipo(String Destipo) {
        this.destipo = Destipo;
    }


    @OneToMany(mappedBy = "tipototem")
    public List<Totem>  getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tipototem tipototem = (Tipototem) o;

        if (this.getIdtipo() == null || tipototem.getIdtipo() == null) return false;
        else return this.getIdtipo().intValue() == tipototem.getIdtipo().intValue();
    }

    @Override
    public int hashCode() {
        return idtipo != null ? 31 * idtipo.hashCode() : 0;
    }


}
