package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity

@NamedQueries(
        {
                @NamedQuery(name = "Anuncio.findAll", query = "SELECT a FROM Anuncio a "),
                @NamedQuery(name = "Anuncio.findAllWithRelationships",
                        query = "SELECT a from Anuncio a " +
                                "inner join fetch a.campana c " +
                                "inner join fetch a.categoria ca")


        }
)
public class Anuncio implements Serializable {
    private Integer idAnuncio;
    private String descanuncio;
    private String nombreAnuncio;


    private String rubro;

    // relaciones
    private Categoria categoria;
    private Campana campana;
    private Afiche afiche;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anuncio", nullable = false, insertable = true, updatable = true)
    public Integer getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    @Basic
    @Column(name = "descanuncio", nullable = true, insertable = true, updatable = true, length = 45)
    public String getDescanuncio() {
        return descanuncio;
    }

    public void setDescanuncio(String descanuncio) {
        this.descanuncio = descanuncio;
    }

    @Basic
    @Column(name = "rubro", nullable = true, insertable = true, updatable = true, length = 45)
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(fetch = FetchType.LAZY)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @JoinColumn(name = "idcampana", referencedColumnName = "idcampana")
    @ManyToOne(fetch = FetchType.LAZY)
    public Campana getCampana() {
        return campana;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }


    @JoinColumn(name = "idafiche", referencedColumnName = "idafiche")
    @OneToOne(fetch = FetchType.LAZY)
    public Afiche getAfiche() {
        return afiche;
    }

    public void setAfiche(Afiche afiche) {
        this.afiche = afiche;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anuncio anuncio = (Anuncio) o;

        if (this.getIdAnuncio() == null || anuncio.getIdAnuncio() == null) return false;
        return this.getIdAnuncio().intValue() == anuncio.getIdAnuncio().intValue();
    }

    @Override
    public int hashCode() {
        return idAnuncio != null ? 31 * idAnuncio.hashCode() : 0;
    }
}
