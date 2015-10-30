package cl.interac.presentacion;

import cl.interac.security.LogInManager;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

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


    private String user;
    private String pass;

    public void enviar(){
        mailSender.send();
        System.err.print("nooo");
    }

    public void logIn() {
        try {
            HttpServletRequest request = FacesUtil.obtenerHttpServletRequest();
            request.login(user, pass);
            FacesUtil.redirigir("/plataforma/campana");

        } catch (ServletException ex) {
            FacesUtil.mostrarMensajeError(ex.getMessage(), null);
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
