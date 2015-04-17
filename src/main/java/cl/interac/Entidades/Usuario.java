/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.interac.Entidades;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u "),
        @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
        @NamedQuery(name = "Usuario.findByUsernameUsuario", query = "SELECT u FROM Usuario u WHERE u.usernameUsuario = :usernameUsuario"),
        @NamedQuery(name = "Usuario.findByPasswordUsuario", query = "SELECT u FROM Usuario u WHERE u.passwordUsuario = :passwordUsuario"),
        @NamedQuery(name = "Usuario.findByMailUsuario", query = "SELECT u FROM Usuario u WHERE u.mailUsuario = :mailUsuario"),
        @NamedQuery(name = "Usuario.findByFotoUsuario", query = "SELECT u FROM Usuario u WHERE u.fotoUsuario = :fotoUsuario"),
        @NamedQuery(name = "Usuario.findByIdentificacionPersonaUsuario", query = "SELECT u FROM Usuario u WHERE u.identificacionPersonaUsuario = :identificacionPersonaUsuario"),
        @NamedQuery(name = "Usuario.findByNombresPersonaUsuario", query = "SELECT u FROM Usuario u WHERE u.nombresPersonaUsuario = :nombresPersonaUsuario"),
        @NamedQuery(name = "Usuario.findByApellidosPersonaUsuario", query = "SELECT u FROM Usuario u WHERE u.apellidosPersonaUsuario = :apellidosPersonaUsuario"),
        @NamedQuery(
                name = "Usuario.findByUsernameAndPassword",
                query = "SELECT u FROM Usuario u " +
                        "INNER JOIN FETCH u.idSucursal " +
                        "INNER JOIN FETCH u.idRolUsuario " +
                        "WHERE u.usernameUsuario = :username and u.passwordUsuario = :password")
})
public class Usuario implements Serializable {
    @OneToMany(mappedBy = "usuario")
    //private List<Compra> compras;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 50)
    @Column(name = "username_usuario")
    private String usernameUsuario;
    @Size(min = 128, max = 128)
    @Column(name = "password_usuario")
    private String passwordUsuario;
    @Size(max = 50)
    @Column(name = "mail_usuario")
    private String mailUsuario;
    @Size(max = 200)
    @Column(name = "foto_usuario")
    private String fotoUsuario;
    @Size(max = 50)
    @Column(name = "identificacion_persona_usuario")
    private String identificacionPersonaUsuario;
    @Size(max = 50)
    @Column(name = "nombres_persona_usuario")
    private String nombresPersonaUsuario;
    @Size(max = 50)
    @Column(name = "apellidos_persona_usuario")
    private String apellidosPersonaUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", nullable = true)
    @ManyToOne(optional = false)
//    @NotFound(action = NotFoundAction.IGNORE)
   /* private Sucursal idSucursal;
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol_usuario")
    @ManyToOne(optional = false)
    private RolUsuario idRolUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Venta> ventaList;*/

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsernameUsuario() {
        return usernameUsuario;
    }

    public void setUsernameUsuario(String usernameUsuario) {
        this.usernameUsuario = usernameUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getMailUsuario() {
        return mailUsuario;
    }

    public void setMailUsuario(String mailUsuario) {
        this.mailUsuario = mailUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public String getIdentificacionPersonaUsuario() {
        return identificacionPersonaUsuario;
    }

    public void setIdentificacionPersonaUsuario(String identificacionPersonaUsuario) {
        this.identificacionPersonaUsuario = identificacionPersonaUsuario;
    }

    public String getNombresPersonaUsuario() {
        return nombresPersonaUsuario;
    }

    public void setNombresPersonaUsuario(String nombresPersonaUsuario) {
        this.nombresPersonaUsuario = nombresPersonaUsuario;
    }

    public String getApellidosPersonaUsuario() {
        return apellidosPersonaUsuario;
    }

    public void setApellidosPersonaUsuario(String apellidosPersonaUsuario) {
        this.apellidosPersonaUsuario = apellidosPersonaUsuario;
    }

  /*  public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public RolUsuario getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(RolUsuario idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compraCollection) {
        this.compras = compraCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.interac.entidades.Usuario[ idUsuario=" + idUsuario + " ]";
    }

}
