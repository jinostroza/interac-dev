package cl.interac.presentacion;

import java.io.Serializable;

import cl.interac.util.components.FacesUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author colivares
 */
@Component
@Scope("view")
public class AutenticarBean implements Serializable {

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
