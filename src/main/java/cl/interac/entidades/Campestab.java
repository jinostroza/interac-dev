package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pedro Pablo on 20-05-2015.
 */


@Entity
@NamedQueries({
        @NamedQuery(name = "campestab.findAll", query = "select ce from Campestab ce"),
        @NamedQuery(name = "campestab.findbyEstablecimiento",
                    query = "SELECT ce FROM Campestab ce " +
                            "INNER JOIN FETCH ce.establecimiento e " +
                            "INNER JOIN FETCH ce.campana c " +
                            "INNER JOIN FETCH e.usuario u " +
                            "WHERE u.username=:username AND ce.estado ='Esperando Aprobacion'"
        ),
        @NamedQuery(name = "campestab.findbyAprobado",
                query = "SELECT ce FROM Campestab ce " +
                        "INNER JOIN FETCH ce.establecimiento e " +
                        "INNER JOIN FETCH ce.campana c " +
                        "INNER JOIN FETCH e.usuario u " +
                        "WHERE u.username=:username AND ce.estado ='Aprobado'"
        ),
        @NamedQuery(name = "campestab.count",
        query = "SELECT COUNT (ce.idcam_est) FROM Campestab ce " +
                "INNER JOIN  ce.establecimiento e " +
                "INNER JOIN  ce.campana c " +
                "INNER JOIN  e.usuario u " +
                "WHERE u.username=:username AND ce.estado ='Esperando Aprobacion'"
)





})

public class Campestab implements Serializable {
    private Integer idcam_est;
    private String estado;


    // Relaciones
    private Establecimiento establecimiento;
    private Campana campana;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcam_est")
    public Integer getIdcam_est() {
        return idcam_est;
    }

    public void setIdcam_est(Integer idcam_est) {
        this.idcam_est = idcam_est;
    }

    @JoinColumn(name = "idestablecimiento", referencedColumnName = "idestablecimiento")
    @ManyToOne(fetch = FetchType.LAZY)
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    @JoinColumn(name = "idcampana", referencedColumnName = "idcampana")
    @ManyToOne(fetch = FetchType.LAZY)
    public Campana getCampana() {
        return campana;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }

    @Basic
    @Column(name= "estado")
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campestab campestab = (Campestab) o;

        if (this.getIdcam_est() == null || campestab.getIdcam_est() == null) return false;
        else return this.getIdcam_est().intValue() == campestab.getIdcam_est().intValue();
    }

    @Override
    public int hashCode() {
        return idcam_est != null ? 31 * idcam_est.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Campestab{" +
                "idcam_est=" + idcam_est +
                '}';
    }
}
