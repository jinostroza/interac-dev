package cl.interac.util.components;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author colivares
 */
@Component
@Scope("application")
public class Constantes implements Serializable {
    private String pathArchivos;

    private String correo = "erlusho30@gmail.com";
    private String claveCorreo = "ladeantes";
    private String servidorCorreo = "smtp.gmail.com";
    private Integer puertoCorreo = 587;


    public Constantes() {
        ProxyFactory pf = new ProxyFactory(this);
        pf.addAdvice(new MethodInterceptor() {

            public Object invoke(MethodInvocation mi) throws Throwable {
                if (mi.getMethod().getName().startsWith("set"))
                    throw new UnsupportedOperationException("Constantes: Estás intentando modificar un valor constante");
                return null;
            }
        });
        pathArchivos = System.getProperty("catalina.home") + "/static/interac/tmp";
    }

    public String getPathArchivos() {
        return pathArchivos;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClaveCorreo() {
        return claveCorreo;
    }

    public void setClaveCorreo(String claveCorreo) {
        this.claveCorreo = claveCorreo;
    }

    public String getServidorCorreo() {
        return servidorCorreo;
    }

    public void setServidorCorreo(String servidorCorreo) {
        this.servidorCorreo = servidorCorreo;
    }

    public Integer getPuertoCorreo() {
        return puertoCorreo;
    }

    public void setPuertoCorreo(Integer puertoCorreo) {
        this.puertoCorreo = puertoCorreo;
    }
}