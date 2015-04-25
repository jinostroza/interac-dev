package cl.interac.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jorge on 25-04-15.
 */
@Entity
public class Campana {
    private Integer idcampana;
    private Integer idcliente;
    private String desccampana;
    private Integer idtotem;

    @Id
    @Column(name = "idcampana")
    public Integer getIdcampana() {
        return idcampana;
    }

    public void setIdcampana(Integer idcampana) {
        this.idcampana = idcampana;
    }

    @Basic
    @Column(name = "idcliente")
    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    @Basic
    @Column(name = "desccampana")
    public String getDesccampana() {
        return desccampana;
    }

    public void setDesccampana(String desccampana) {
        this.desccampana = desccampana;
    }

    @Basic
    @Column(name = "idtotem")
    public Integer getIdtotem() {
        return idtotem;
    }

    public void setIdtotem(Integer idtotem) {
        this.idtotem = idtotem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campana campana = (Campana) o;

        if (idcampana != null ? !idcampana.equals(campana.idcampana) : campana.idcampana != null) return false;
        if (idcliente != null ? !idcliente.equals(campana.idcliente) : campana.idcliente != null) return false;
        if (desccampana != null ? !desccampana.equals(campana.desccampana) : campana.desccampana != null) return false;
        if (idtotem != null ? !idtotem.equals(campana.idtotem) : campana.idtotem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcampana != null ? idcampana.hashCode() : 0;
        result = 31 * result + (idcliente != null ? idcliente.hashCode() : 0);
        result = 31 * result + (desccampana != null ? desccampana.hashCode() : 0);
        result = 31 * result + (idtotem != null ? idtotem.hashCode() : 0);
        return result;
    }
}
