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

                @NamedQuery(name = "Analitica.totem", query = "SELECT an FROM Analitica an " +
                        " WHERE an.modulo=:idtotem and an.camara_date>=:fec_ini and an.camara_date<=:fec_end " ),

                @NamedQuery(name = "Analitica.contenido", query = "SELECT an FROM Analitica an " +
                        " WHERE an.imagen=:path and an.camara_date>=:fec_ini and an.camara_date<=:fec_end " ),
                @NamedQuery(name = "Analitica.conTotem", query = "SELECT an FROM Analitica an " +
                        " WHERE an.modulo=:idtotem and an.imagen=:path and an.camara_date>=:fec_ini and an.camara_date<=:fec_end " )





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
