package cl.interac.entidades;

import javax.persistence.*;


/**
 * Created by Joaco on 20/08/2015.
 */
@Entity
public class Campanun {
    private int idcamanun;
    //relaciones
    private Anuncio anuncio;
    private Campana campana;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcamanun", nullable = false, insertable = true, updatable = true)
    public int getIdcamanun() {
        return idcamanun;
    }

    public void setIdcamanun(int idcamanun) {
        this.idcamanun = idcamanun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campanun campanun = (Campanun) o;

        if (idcamanun != campanun.idcamanun) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idcamanun;
    }
}
