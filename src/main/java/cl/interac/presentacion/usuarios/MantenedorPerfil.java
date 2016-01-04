package cl.interac.presentacion.usuarios;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.security.LogInManager;
import cl.interac.security.SHA512;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import cl.interac.util.pojo.Encriptador;
import cl.interac.util.pojo.Md5;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by luisPc on 17-08-2015.
 */
@Component
@Scope("flow")
public class MantenedorPerfil implements Serializable {
    @Autowired
    public LogicaUsuario logicaUsuario;
    @Autowired
    private UserSession userSession; // es un componente spring y de scope session, por ende hay que
    @Autowired
    LogInManager logInManager;


    private String claveActual;
    private String claveNueva;
    private String claveConfirmada;
    private Rol rol;
    private String correo;
    private String empresa;
    private String username;
    private Usuario usuario;
    private List<Usuario> usuarioList;


    public void inicio() {
        usuarioList = logicaUsuario.obtenerTodos();
        usuario = new Usuario();
    }


       public void cambiaPerfil() {
        if (!claveConfirmada.equals(claveNueva)) {
            FacesUtil.mostrarMensajeError("Operación fallida", "La nueva contraseña no coincide con lo confirmado");
            return;
        }
        else{
            logicaUsuario.cambiarClave(userSession.getUsuario().getUsername(), md5(claveConfirmada));
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha cambiado correctamente la contraseña");
        }

        if (!userSession.getUsuario().getPassword().equals(claveActual)) {
            FacesUtil.mostrarMensajeError("Operación fallida", "contraseña invalida");
            return;
        }

        logicaUsuario.editarPerfil(userSession.getUsuario().getUsername(),correo,empresa);
        FacesUtil.mostrarMensajeInformativo("Operación exitosa", "usuario [" + userSession.getUsuario().getUsername() + "] modificado");
    }

    public static String md5(String input){

        String md5 = null; //La variable esta vacia
        if(null == input){ //Si es nulo el input, la funcion retornara null
            return null;
        }

        try{
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    //getter and setter

    public String irVerPerfil() {
        return "flowVerPerfil";
    }

    public String irCambiarClave() {
        return "flowCambiarClave";
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getClaveConfirmada() { return claveConfirmada; }

    public void setClaveConfirmada(String claveConfirmada) {
        this.claveConfirmada = claveConfirmada;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
