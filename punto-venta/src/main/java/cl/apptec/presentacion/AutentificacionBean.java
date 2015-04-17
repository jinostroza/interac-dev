package cl.apptec.presentacion;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cl.apptec.util.components.FacesUtil;

/**
 * @author colivares
 */
@Component
@Scope("view")
public class AutentificacionBean implements Serializable {

    private String user;
    private String pass;

    public void logIn() {
        FacesUtil.despachar("j_spring_security_check");
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
