package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jiu on 24-04-15.
 */
@Entity
//@Table(name="Usuario")
@NamedQueries(
        {
                @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u "),
                @NamedQuery(
                        name = "Usuario.findByUserAndPassword",
                        query = "SELECT u FROM Usuario u WHERE u.username = :username and u.password = :password"
                ),
                @NamedQuery(
                        name = "Usuario.findByUser",
                        query = "SELECT u FROM Usuario u WHERE u.username = :username"
                ),



                @NamedQuery(
                        name="Usuario.findByCorreo",
                        query = "SELECT u FROM Usuario u where u.correo= :correo "
                ),
                @NamedQuery(
                        name="Usuario.findByEmpresa",
                        query="SELECT u FROM Usuario u where u.empresa = :empresa"
                )
        }
)
public class Usuario implements Serializable {
    private Integer idUsuario;
    private String username;
    private String password;
    private String correo;
    private String empresa;

    // relaciones
    private List<Campana> campanas;
    private Rol rol;
    private List<Contenido> contenido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario", nullable = false, insertable = true, updatable = true)
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idusuario) {
        this.idUsuario = idusuario;
    }

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "correo", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "empresa", nullable = true, insertable = true, updatable = true, length = 45)
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }


    @OneToMany(mappedBy = "cliente")
    public List<Campana> getCampanas() {
        return campanas;
    }

    public void setCampanas(List<Campana> campanas) {
        this.campanas = campanas;
    }


    @OneToMany(mappedBy="usuario")
    public List<Contenido> getContenido() {
        return contenido;
    }

    public void setContenido(List<Contenido> contenido) {
        this.contenido = contenido;
    }

   @JoinColumn(name = "idrol",referencedColumnName = "id_rol",nullable = false, columnDefinition = "1")
    @ManyToOne(fetch = FetchType.LAZY)
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (usuario.getIdUsuario() == null || this.getIdUsuario() == null) return false;
        return this.getIdUsuario().intValue() == usuario.getIdUsuario().intValue();
    }

    @Override
    public int hashCode() {
        return idUsuario != null ? 31 * idUsuario.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                '}';
    }

}