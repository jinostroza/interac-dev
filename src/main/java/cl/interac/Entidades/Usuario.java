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
@NamedQueries(value = {
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u "),
        @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idusuario"),
        @NamedQuery(name = "Usuario.findByUsernameUsuario", query = "SELECT u FROM Usuario u WHERE u.usernameUsuario = :USER"),
        @NamedQuery(name = "Usuario.findByPasswordUsuario", query = "SELECT u FROM Usuario u WHERE u.passwordUsuario = :password"),
        @NamedQuery(name = "Usuario.findByMailUsuario", query = "SELECT u FROM Usuario u WHERE u.mailUsuario = :correo"),
        // @NamedQuery(name = "Usuario.findByFotoUsuario", query = "SELECT u FROM Usuario u WHERE u.fotoUsuario = :fotoUsuario"),
        // @NamedQuery(name = "Usuario.findByIdentificacionPersonaUsuario", query = "SELECT u FROM Usuario u WHERE u.identificacionPersonaUsuario = :identificacionPersonaUsuario"),
        @NamedQuery(name = "Usuario.findByNombresPersonaUsuario", query = "SELECT u FROM Usuario u WHERE u.rolEmpresa = :rol"),
        @NamedQuery(name = "Usuario.findByApellidosPersonaUsuario", query = "SELECT u FROM Usuario u WHERE u.nombresEmpresa = :empresa"),
        @NamedQuery(
                name = "Usuario.findByUsernameAndPassword",
                query = "SELECT u FROM Usuario u " +
                        //"INNER JOIN FETCH u.idSucursal " +
                        // "INNER JOIN FETCH u.idRolUsuario " +
                        "WHERE u.usernameUsuario = :USER and u.passwordUsuario = :password")
})
public class Usuario implements Serializable {
    //@OneToMany(mappedBy = "usuario")
    //private List<Compra> compras;
  //  public Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Size(max = 50)
    @Column(name = "USER")
    private String usernameUsuario;
    @Size(min = 128, max = 128)
    @Column(name = "password")
    private String passwordUsuario;
    @Size(max = 50)
    @Column(name = "correo")
    private String mailUsuario;
    @Size(max = 200)
   // @Column(name = "foto_usuario")
   // private String fotoUsuario;
    //@Size(max = 50)
    @Column(name = "rol")
    private String rolEmpresa;
    @Size(max = 50)
    @Column(name = "empresa")
    private String nombresEmpresa;

   // @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", nullable = true)
    //@ManyToOne(optional = false)
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

   /* public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }*/

    public String getRolEmpresa() {
        return rolEmpresa;
    }

    public void setRolEmpresa(String rolEmpresa) {
        this.rolEmpresa = rolEmpresa;
    }

    public String getNombresEmpresa() {
        return nombresEmpresa;
    }

    public void setNombresEmpresa(String nombresEmpresa) {
        this.nombresEmpresa = nombresEmpresa;
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
