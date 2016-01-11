package cl.interac.entidades;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by Joaco on 19/11/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Meses.findAll", query = "SELECT m FROM Meses m")
})
public class Meses implements Serializable {
    private Integer idmes;
    private String mes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmes", nullable = false, insertable = true, updatable = true)
    public Integer getIdmes() {
        return idmes;
    }

    public void setIdmes(Integer idmes) {
        this.idmes = idmes;
    }

    @Basic
    @Column(name = "mes", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meses meses = (Meses) o;

        if (this.getIdmes() == null || meses.getIdmes() == null) return false;
        else return this.getIdmes().intValue() == meses.getIdmes().intValue();
    }

    @Override
    public int hashCode() {
        return idmes != null ? 31 * idmes.hashCode() : 0;
    }
}

