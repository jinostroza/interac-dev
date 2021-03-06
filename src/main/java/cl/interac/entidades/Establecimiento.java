package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Pedro Pablo on 20-05-2015.
 */


@Entity
@NamedQueries({
        @NamedQuery(name = "establecimiento.findAll", query = "select e from Establecimiento e"),

        @NamedQuery(name = "estabecimiento.findAllByusuario",
                query = "select e from Establecimiento e " +
                        "inner join fetch e.usuario u " +

                        "INNER JOIN FETCH e.categoria c " +
                        "INNER JOIN FETCH e.empresa em "),

        @NamedQuery(name = "establecimiento.findEstablecimientoByUser",
                query = "SELECT e FROM Establecimiento e " +
                        "INNER JOIN FETCH e.usuario u "+
                        "WHERE u.username=:username "),

        @NamedQuery(name = "establecimiento.findIdestablecimiento",
                query = "SELECT e from Establecimiento e where e.idEstablecimiento = :estable"),

        @NamedQuery(name= "establecimiento.findbyUser",
                query = "SELECT e FROM Establecimiento e " +

                        "INNER JOIN FETCH e.usuario u " +

                        "WHERE u.username=:username "),
        @NamedQuery(name= "establecimiento.findbyIDUser",
                query = "SELECT e FROM Establecimiento e " +
                        "INNER JOIN FETCH e.usuario u " +
                        "WHERE u.idUsuario=:idUsuario and e.idEstablecimiento=:idEstablecimiento"),

        @NamedQuery(name = "establecimiento.findtotem",
                query = "SELECT e FROM Establecimiento e " +

                        "INNER JOIN FETCH e.usuario u " +
                        "INNER JOIN FETCH e.categoria ca "),

        @NamedQuery(name = "establecimiento.findByEstado",
                query = "SELECT e FROM Establecimiento e " +
                        "WHERE e.estado = :estado"),

        @NamedQuery(name = "establecimiento.findFilter",
                query = "SELECT e FROM Establecimiento e " +
                        "INNER JOIN FETCH e.comunas co " +
                        "INNER JOIN FETCH e.provincias p " +
                        "INNER JOIN FETCH e.regiones r " +
                        "INNER JOIN FETCH e.categoria c " +
                        "INNER JOIN FETCH e.empresa em " +
                        "WHERE e.empresa = :idempresa or e.categoria = :idcategoria or e.nombreEstablecimiento = :nombre " +
                        "or e.comunas = :idubicacion or e.provincias = :idprovincias or e.regiones = :idregiones or e.orientacion = :orientacion or e.estado = 'activo' "),

        @NamedQuery(name = "establecimiento.findByEmpresa",
                query = "SELECT e FROM Establecimiento e "+
                        "INNER JOIN FETCH e.empresa emp "+
                        "WHERE emp.idEmpresa = :empresa ")
})

public class Establecimiento implements Serializable {
    private Integer idEstablecimiento;
    private String nombreEstablecimiento;
    private String direccion;
    private String fono;
    private Double lat;
    private Double longi;
    private Integer valor;
    private Date horaInicio;
    private Date horaTermino;
    private Integer slots;
    private Integer numeroPantallas;
    private String urlImagen;
    private String estado;
    private String carpetaFtp;
    private String orientacion;

    // Relaciones
    private Usuario usuario;
    private List<Totem> totem;
    private Ubicacion ubicacion;
    private Comunas comunas;
    private Provincias provincias;
    private Regiones regiones;
    private Categoria categoria;
    private List<Campana> campanaList;
    private Empresa empresa;

    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToMany(mappedBy = "establecimientoList")
    public List<Campana> getCampanaList() {
        return campanaList;
    }
    public void setCampanaList(List<Campana> campanaList) {
        this.campanaList = campanaList;
    }

    @OneToMany(mappedBy = "establecimiento")
    public List<Totem> getTotem() {
        return totem;
    }
    public void setTotem(List<Totem> totem) {
        this.totem = totem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestablecimiento", nullable = false, insertable = true, updatable = false)
    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }
    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    @Basic
    @Column(name = "desestablecimiento",insertable = true,length = 200)
    public String getNombreEstablecimiento() { return nombreEstablecimiento; }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "fono")
    public String getFono() {
        return fono;
    }
    public void setFono(String fono) {
        this.fono = fono;
    }

    @Basic
    @Column(name = "latitud")
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "longitud")
    public Double getLongi() {
        return longi;
    }
    public void setLongi(Double longi) {
        this.longi = longi;
    }

    @Basic
    @Column(name = "valor")
    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }

    @Basic
    @Column(name = "horainicio")
    public Date getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Date horaInicio) { this.horaInicio = horaInicio; }

    @Basic
    @Column(name = "horatermino")
    public Date getHoraTermino() { return horaTermino; }
    public void setHoraTermino(Date horaTermino) { this.horaTermino = horaTermino; }

    @Basic
    @Column(name = "slots")
    public Integer getSlots() { return slots; }
    public void setSlots(Integer slots) { this.slots = slots; }

    @Basic
    @Column(name = "numeropantallas")
    public Integer getNumeroPantallas() { return numeroPantallas; }
    public void setNumeroPantallas(Integer numeroPantallas) { this.numeroPantallas = numeroPantallas; }

    @Basic
    @Column(name ="urlImagen")
    public String getUrlImagen() { return urlImagen; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }

    @Basic
    @Column(name= "estado")
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    @Basic
    @Column(name = "carpetaftp")
    public String getCarpetaFtp() {
        return carpetaFtp;
    }

    public void setCarpetaFtp(String carpetaFtp) {
        this.carpetaFtp = carpetaFtp;
    }

    @Basic
    @Column(name= "orientacion")
    public String getOrientacion() { return orientacion; }
    public void setOrientacion(String orientacion) { this.orientacion = orientacion; }

    @JoinColumn(name = "fk_rubro", referencedColumnName = "idcategoria")
    @ManyToOne(fetch = FetchType.LAZY)
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }


    @JoinColumn(name = "idubicacion", referencedColumnName = "comuna_id")
    @ManyToOne(fetch = FetchType.LAZY)
    public Comunas getComunas() {
        return comunas;
    }

    public void setComunas(Comunas comunas) {
        this.comunas = comunas;
    }
    @JoinColumn(name = "idprovincia", referencedColumnName = "provincia_id")
    @ManyToOne(fetch = FetchType.LAZY)

    public Provincias getProvincias() {
        return provincias;
    }

    public void setProvincias(Provincias provincias) {
        this.provincias = provincias;
    }
    @JoinColumn(name = "idregion", referencedColumnName = "region_id")
    @ManyToOne(fetch = FetchType.LAZY)

    public Regiones getRegiones() {
        return regiones;
    }

    public void setRegiones(Regiones regiones) {
        this.regiones = regiones;
    }

    @JoinColumn(name = "empresa", referencedColumnName = "idempresa")
    @ManyToOne(fetch = FetchType.LAZY)
    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Establecimiento establecimiento = (Establecimiento) o;

        if (this.getIdEstablecimiento() == null || establecimiento.getIdEstablecimiento() == null) return false;
        else return this.getIdEstablecimiento().intValue() == establecimiento.getIdEstablecimiento().intValue();
    }

    @Override
    public int hashCode() {
        return idEstablecimiento != null ? 31 * idEstablecimiento.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Establecimiento{" +
                "idEstablecimiento=" + idEstablecimiento +
                '}';
    }
}
