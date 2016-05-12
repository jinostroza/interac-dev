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
                @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c " +
                        "INNER JOIN FETCH c.idcliente cc " +
                        "inner join fetch c.idempresa em " )

        }
)
public class Contrato implements Serializable {
    private Integer idcontrato;
    private Date fechaFin;
    private Date fechaInicio;
    private Usuario idcliente;
    private Empresa idempresa;
    private String path;
    private String firma;
    private String usuario;





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontrato")
    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "ffin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "finicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "usuario")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Column(name = "firma")
    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }
    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    @JoinColumn(name = "idcliente", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getIdcliente() { return idcliente; }
    public void setIdcliente(Usuario idcliente) { this.idcliente = idcliente; }

    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne(fetch = FetchType.LAZY)
    public Empresa getIdempresa() { return idempresa; }
    public void setIdempresa(Empresa idempresa) { this.idempresa = idempresa; }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contrato contrato = (Contrato) o;

        if (this.getIdcontrato() == null || contrato.getIdcontrato() == null) return false;
        else return this.getIdcontrato().intValue() == contrato.getIdcontrato().intValue();
    }

    @Override
    public int hashCode() {
        return idcontrato != null ? 31 * idcontrato.hashCode() : 0;
    }
}
