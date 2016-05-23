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
                @NamedQuery(name = "Analitica.findAllS", query = "SELECT a FROM Analitica a " +
                "where a.sexo='Hombre ' or a.sexo='Mujer '" ),

                @NamedQuery(name = "Analitica.countHombres", query = "SELECT COUNT (an.sexo) FROM Analitica an " +
                        " WHERE an.sexo='Hombre '" ),

                @NamedQuery(name = "Analitica.countMujeres", query = "SELECT COUNT (an.sexo) FROM Analitica an " +
                        " WHERE an.sexo='Mujer '" ),
                @NamedQuery(name = "Analitica.countSeg1", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>0 and an.edad<=15" ),
                @NamedQuery(name = "Analitica.countSeg2", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>15 and an.edad<=25" ),
                @NamedQuery(name = "Analitica.countSeg3", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>25 and an.edad<=35" ),
                @NamedQuery(name = "Analitica.countSeg4", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>35 and an.edad<=45" ),
                @NamedQuery(name = "Analitica.countSeg5", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>45 and an.edad<=55" ),
                @NamedQuery(name = "Analitica.countSeg6", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>55 and an.edad<=65" ),
                @NamedQuery(name = "Analitica.countSeg7", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>65 " ),
                @NamedQuery(name = "Analitica.audiencia", query = "SELECT COUNT (an.cuerpos) FROM Analitica an " ),
        }
)
public class Analitica implements Serializable {

    private Date fechahora;
    private String modulo;
    private Integer cuerpos;
    private Integer face;
    private Integer edad;
    private String sexo;
    private String expresion;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Temporal(TemporalType.TIMESTAMP)
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


  /*  @Override
    //public boolean equals(Object o) {
      //  if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;

        Analitica analitica = (Analitica) o;

        //if (this.getFechahora() == null || analitica.getFechahora() == null) return false;
        //else return this.getFechahora(). == analitica.getIdanalitica().intValue();
    }

    @Override
    public int hashCode() {
        return fechahora != null ? 31 * fechahora.hashCode() : 0;
    }*/
}
