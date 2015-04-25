package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by claudio on 24-04-15.
 */
@Entity
@Table(name = "usuario")
@NamedQueries(
        {
                @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u "),
        }
)
public class Usuario implements Serializable {
    private Integer idUsuario;
    private String user;
    private String password;
    private String correo;
    private String empresa;
    private String rol;
    private int idusuario;
    private String username;

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
    @Column(name = "USER", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    @Basic
    @Column(name = "rol", nullable = true, insertable = true, updatable = true, length = 45)
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
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
        return idUsuario != null ? idUsuario.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                '}';
    }

    @Id
    @Column(name = "idusuario")
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
