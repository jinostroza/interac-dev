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
                @NamedQuery(name = "Campana.findAll", query = "SELECT c FROM Campana c "),
                @NamedQuery(
                        name = "Campana.findAllWithRelationships",
                        query = "SELECT c FROM Campana c " +
                                "INNER JOIN FETCH c.totem t " +
                                "INNER JOIN FETCH c.cliente cli"
                )
        }
)
public class Campana implements Serializable {
    private Integer idcampana;
    private String desccampana;

    // relaciones
    private List<Anuncio> anuncios;
    private Totem totem;
    private Usuario cliente;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcampana")
    public Integer getIdcampana() {
        return idcampana;
    }

    public void setIdcampana(Integer idcampana) {
        this.idcampana = idcampana;
    }

    @Basic
    @Column(name = "desccampana")
    public String getDesccampana() {
        return desccampana;
    }

    public void setDesccampana(String desccampana) {
        this.desccampana = desccampana;
    }

    @OneToMany(mappedBy = "campana")
    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    @JoinColumn(name = "idtotem", referencedColumnName = "idtotem")
    @ManyToOne(fetch = FetchType.LAZY)
    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    @JoinColumn(name = "idcliente", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario usuario) {
        this.cliente = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campana campana = (Campana) o;

        if (this.getIdcampana() == null || campana.getIdcampana() == null) return false;
        else return this.getIdcampana().intValue() == campana.getIdcampana().intValue();
    }

    @Override
    public int hashCode() {
        return idcampana != null ? 31 * idcampana.hashCode() : 0;
    }
}
