package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


/**
 * Created by Jorge on 25-04-15.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Totem.findAll", query = "SELECT t FROM Totem t "),
                @NamedQuery(name = "Totem.findWithRelationship",
                        query = "SELECT t FROM Totem t " +
                                "LEFT JOIN FETCH t.tipototem " +
                                "LEFT JOIN FETCH t.establecimiento e " +
                                "LEFT JOIN FETCH e.usuario u"


                               ),
                @NamedQuery(name="Totem.findbyUsuario",
                            query="SELECT t FROM Totem t " +
                                    "LEFT JOIN FETCH t.establecimiento e " +
                                    "LEFT JOIN FETCH e.ubicacion ub " +
                                    "LEFT JOIN FETCH e.usuario u " +
                                    "LEFT JOIN FETCH t.tipototem " +
                                    "LEFT JOIN FETCH t.marcaPantalla " +
                                    "WHERE u.username=:username"),

                @NamedQuery(name="Totem.findByIdWithTotem",
                            query="SELECT DISTINCT t FROM Totem t " +
                                  "LEFT JOIN FETCH t.campanaList cs " +
                                  "LEFT JOIN FETCH t.establecimiento e " +
                                  "Left join fetch e.usuario u " +
                                  "WHERE u.username=:username "
                ),
                @NamedQuery(name="Totem.count",
                        query="SELECT COUNT (t.idtotem) FROM Totem t "+
                                "INNER JOIN  t.establecimiento e " +
                                " WHERE e.idEstablecimiento=:establecimiento"
                ),
                @NamedQuery(name="Totem.findByEstablecimiento",
                        query="SELECT  t FROM Totem t " +
                                "LEFT JOIN FETCH t.establecimiento e " +
                                "WHERE e.idEstablecimiento=:establecimiento"
                ),

                @NamedQuery(name = "Totem.findTotemAndTodaLaWea",
                            query="SELECT t FROM Totem t " +
                                  "LEFT JOIN FETCH  t.campanaList cs " +
                                  "LEFT JOIN FETCH t.establecimiento " )



        }
)
public class Totem implements Serializable {
    private Integer idtotem;
    private String noserie;
    private Double lat;
    private Double longi;
    private String orientacion;
    private String modelo;
    private String pulgadas;

    // relaciones
    private List<Campana> campanaList;
    private Establecimiento establecimiento;
    private Tipototem tipototem;
    private Marcapantalla marcaPantalla;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtotem")
    public Integer getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(Integer idtotem) {
        this.idtotem = idtotem;
    }



  /* @JoinTable(
                name = "campatotem",
                inverseJoinColumns = {
                        @JoinColumn(name = "idcampana", referencedColumnName = "idcampana")
                },
                joinColumns = {
                        @JoinColumn(name = "idtotem" , referencedColumnName = "idtotem")
                }
        )*/
  @ManyToMany(mappedBy = "totemList")
    public List<Campana> getCampanaList() {
        return campanaList;
    }

    public void setCampanaList(List<Campana> campanaList) {
        this.campanaList = campanaList;
    }
/*
    @Basic
    @Column(name = "orientacion")
    public String getOrientacion() { return orientacion; }

    public void setOrientacion(String orientacion) { this.orientacion = orientacion; }


    @Basic
    @Column(name = "modelo")
    public String getModelo() { return modelo; }

    public void setModelo(String modelo) { this.modelo = modelo; }

    @Basic
    @Column(name = "pulgadas")
    public String getPulgadas() { return pulgadas; }
*/
    public void setPulgadas(String pulgadas) { this.pulgadas = pulgadas; }

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmarca",referencedColumnName = "idmarca",nullable = false)
    public Marcapantalla getMarcaPantalla(){
        return marcaPantalla;
    }

    public void setMarcaPantalla(Marcapantalla marcaPantalla) { this.marcaPantalla = marcaPantalla; }



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
