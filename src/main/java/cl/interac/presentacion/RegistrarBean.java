package cl.interac.presentacion;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by claudio on 24-04-15.
 */
@Component
@Scope("view")
public class RegistrarBean implements Serializable {
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private MailSender mailSender;

    private Usuario usuario;
    private Rol rol;
    private String pass;

    // Spring nos instanciará el bean cuando cree el componente, pero antes debemos setear el usuario para poder usar
    // sus atributos en el jsf
    public RegistrarBean () {
        usuario = new Usuario();
    }

    public String signUp() {

        System.err.println("LLEGO A REGISTRAR");

        logicaUsuario.guardar(usuario);

       return next1();

    }
    public void recuperar(String us){
        System.out.println(" ss" + us);
    }

    public String next1(){ return "next1";}
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}