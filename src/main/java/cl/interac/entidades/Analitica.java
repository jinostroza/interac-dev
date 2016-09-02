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
                "where a.genero='Hombre ' or a.genero='Mujer '" ),

                @NamedQuery(name = "Analitica.countHombres", query = "SELECT COUNT (an.genero) FROM Analitica an " +
                        " WHERE an.genero='Hombre ' AND an.modulo=:idtotem" ),

                @NamedQuery(name = "Analitica.countMujeres", query = "SELECT COUNT (an.genero) FROM Analitica an " +
                        " WHERE an.genero='Mujer ' AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSeg1", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>0 and an.edad<=15 AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSeg2", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>15 and an.edad<=25 AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSeg3", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>25 and an.edad<=35 AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSeg4", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>35 and an.edad<=45 AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSeg5", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>45 and an.edad<=55 AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSeg6", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>55 and an.edad<=65 AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSeg7", query = "SELECT COUNT (an.edad) FROM Analitica an " +
                        " WHERE an.edad>65 AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countNeutral", query = "SELECT COUNT (an.expresion) FROM Analitica an " +
                        " WHERE an.expresion = 'Neutral ' AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSad", query = "SELECT COUNT (an.expresion) FROM Analitica an " +
                " WHERE an.expresion = 'Triste ' AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countHappy", query = "SELECT COUNT (an.expresion) FROM Analitica an " +
                        " WHERE an.expresion = 'Feliz ' AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countAnger", query = "SELECT COUNT (an.expresion) FROM Analitica an " +
                        " WHERE an.expresion = 'Enojado ' AND an.modulo=:idtotem" ),
                @NamedQuery(name = "Analitica.countSurp", query = "SELECT COUNT (an.expresion) FROM Analitica an " +
                        " WHERE an.expresion = 'Sorpresa ' AND an.modulo=:idtotem" )



                //@NamedQuery(name = "Analitica.audiencia", query = "SELECT COUNT (an.cuerpos) FROM Analitica an " ),
        }
)
public class Analitica implements Serializable {
    private Integer id_analitica;
    private Date camara_date;
    private Integer modulo;
    private String imagen;
    private Integer edad;
    private String genero;
    private String expresion;
    private Date slider_inicio;
    private Date slider_fin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_analitica")
    public Integer getId_analitica() {
        return id_analitica;
    }

    public void setId_analitica(Integer id_analitica) {
        this.id_analitica = id_analitica;
    }
    @Basic
    @Column(name = "id_modulo")
    public Integer getModulo() {
        return modulo;
    }

    public void setModulo(Integer modulo) {
        this.modulo = modulo;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "camara_date")
    public Date getCamara_date() {
        return camara_date;
    }

    public void setCamara_date(Date fechahora) {
        this.camara_date = fechahora;
    }

    @Basic
    @Column(name = "slider_img")
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
    @Column(name = "genero")
    public String getGenero() {
        return genero;
    }

    public void setGenero(String sexo) {
        this.genero = sexo;
    }
    @Basic
    @Column(name = "expresion")
    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "slider_fin")
    public Date getSlider_fin() {
        return slider_fin;
    }

    public void setSlider_fin(Date slider_fin) {
        this.slider_fin = slider_fin;
    }
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "slider_inicio")
    public Date getSlider_inicio() {

        return slider_inicio;
    }

    public void setSlider_inicio(Date slider_inicio) {
        this.slider_inicio = slider_inicio;
    }


  @Override
   public boolean equals(Object o) {
     if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Analitica analitica = (Analitica) o;

        if (this.getId_analitica() == null || analitica.getId_analitica() == null) return false;
        else return this.getId_analitica().intValue() == analitica.getId_analitica().intValue();
    }

    @Override
    public int hashCode() {
        return camara_date != null ? 31 * camara_date.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "Analitica" +
                "{" +
                "id_analitica =" + id_analitica +
                '}';
    }
}
