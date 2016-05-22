package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Joaco on 11-05-16.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Analitica.findAll", query = "SELECT a FROM Analitica a "),

        }
)
public class Analitica implements Serializable {
    private Integer idanalitica;
    private Date fechahora;
    private String modulo;
    private Integer cuerpos;
    private Integer face;
    private Integer edad;
    private String sexo;
    private String expresion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idanalitica")
    public Integer getIdanalitica() {
        return idanalitica;
    }

    public void setIdanalitica(Integer idanalitica) {
        this.idanalitica = idanalitica;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "fechahora")
    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }


    @Basic
    @Column(name = "modulo")
    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    @Basic
    @Column(name = "cuerpos")
    public Integer getCuerpos() {
        return cuerpos;
    }

    public void setCuerpos(Integer cuerpos) {
        this.cuerpos = cuerpos;
    }
    @Basic
    @Column(name = "face")
    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }
    @Basic
    @Column(name = "edad")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    @Basic
    @Column(name = "sexo")
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    @Basic
    @Column(name = "expresion")
    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Analitica analitica = (Analitica) o;

        if (this.getIdanalitica() == null || analitica.getIdanalitica() == null) return false;
        else return this.getIdanalitica().intValue() == analitica.getIdanalitica().intValue();
    }

    @Override
    public int hashCode() {
        return idanalitica != null ? 31 * idanalitica.hashCode() : 0;
    }
}
