package cl.interac.presentacion;

import cl.interac.entidades.Usuario;
import cl.interac.security.LogInManager;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author colivares
 */
@Component
@Scope("view")
public class AutenticarBean implements Serializable {

    @Autowired
    UserSession userSession;
    @Autowired
    LogInManager logInManager;
    @Autowired
    MailSender mailSender;
    @Autowired
    Constantes constantes;

    private String user;
    private String pass;

    private Usuario usuario;


    public void logIn() {
        try {
            HttpServletRequest request = FacesUtil.obtenerHttpServletRequest();

            request.login(user, md5(pass));
            FacesUtil.redirigir("/plataforma/campana");

        } catch (ServletException ex) {
            FacesUtil.mostrarMensajeError(ex.getMessage(), null);
        }
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

    public String getUser() { return user; }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

}
