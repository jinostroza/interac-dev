package cl.interac.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jiu on 24-04-15.
 */
@Entity
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
                ),
                @NamedQuery(
                       name="Usuario.findWithRelationship",
                        query="SELECT u FROM Usuario u " +
                                "LEFT JOIN FETCH u.rol " +
                                "LEFT JOIN FETCH u.contenido "),

                @NamedQuery(name = "usuario.findByEmpresa",
                        query = "SELECT u FROM Usuario u "+
                                "INNER JOIN FETCH u.empresa emp "+
                                "WHERE emp.idEmpresa=:empresa ")
        }
)
public class Usuario implements Serializable {
    private Integer idUsuario;
    private String username;
    private String password;
    private String correo;

    // Relaciones
    private Rol rol;
    private List<Contenido> contenido;
    private List<Establecimiento> establecimiento;
    private Empresa empresa;

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

    @OneToMany(mappedBy="usuario")
    public List<Contenido> getContenido() {
        return contenido;
    }
    public void setContenido(List<Contenido> contenido) {
        this.contenido = contenido;
    }

    @OneToMany(mappedBy="usuario")
    public List<Establecimiento> getEstablecimiento() { return establecimiento; }
    public void setEstablecimiento ( List<Establecimiento> establecimiento ) { this.establecimiento = establecimiento; }

    @JoinColumn(name = "idrol",referencedColumnName = "id_rol",nullable = false , columnDefinition ="4" )
    @ManyToOne(fetch = FetchType.LAZY)
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        if (rol == null) {
            rol = new Rol();
            rol.setIdrol(4);
        }
        this.rol=rol;
    }

    @JoinColumn(name = "empresa", referencedColumnName = "idempresa")
    @ManyToOne(fetch = FetchType.LAZY)
    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

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