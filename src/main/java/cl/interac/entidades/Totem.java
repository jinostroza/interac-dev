package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Totem.findAll", query = "SELECT t FROM Totem t "),
                @NamedQuery(name = "Totem.findWithRelationship",
                        query = "SELECT t FROM Totem t " +
                                "left join fetch t.establecimiento e "

                               ),
                @NamedQuery(name="Totem.findbyUsuario",
                            query="SELECT t FROM Totem t " +
                                    "LEFT JOIN FETCH t.establecimiento e " +
                                    "LEFT JOIN FETCH e.ubicacion ub " +
                                    "LEFT JOIN FETCH e.usuario u " +
                                    "where u.username=:username")

        }
)
public class Totem implements Serializable {
    private Integer idtotem;
    private String noserie;
    private Double lat;
    private Double longi;

    // relaciones
//    private List<Campana> campanas;
    private Establecimiento establecimiento;
    private Tipototem tipototem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtotem")
    public Integer getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(Integer idtotem) {
        this.idtotem = idtotem;
    }
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "campatotem",
//            inverseJoinColumns = {
//                    @JoinColumn(name = "idcampana", referencedColumnName = "idcampana")
//            },
//            joinColumns = {
//                    @JoinColumn(name = "idtotem" , referencedColumnName = "idtotem")
//            }
//    )
//    public List<Campana> getCampanas() {
//        return campanas;
//    }
//
//    public void setCampanas(List<Campana> campanas) {
//        this.campanas = campanas;
//    }



    @Basic
    @Column(name = "latitud")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "longitud")
    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    @Basic
    @Column(name = "Noserie")
    public String getNoserie() {
        return noserie;
    }

    public void setNoserie(String noserie) {
        this.noserie = noserie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipo", referencedColumnName = "idtipo", nullable = false)
    public Tipototem getTipototem() {
        return tipototem;
    }

    public void setTipototem(Tipototem tipototem) {
        this.tipototem = tipototem;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idestablecimiento", referencedColumnName = "idestablecimiento", nullable = false)
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Totem totem = (Totem) o;

        if (this.getIdtotem() == null || totem.getIdtotem() == null) return false;
        else return this.getIdtotem().intValue() == totem.getIdtotem().intValue();
    }

    @Override
    public int hashCode() {
        return idtotem != null ? 31 * idtotem.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Totem{" +
                "idtotem=" + idtotem +
                '}';
    }
}
