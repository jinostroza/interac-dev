package cl.interac.util.components;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author colivares
 */
@Component
@Scope("application")
public class FacesUtil {
    public static HttpServletRequest obtenerHttpServletRequest() {
        return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static HttpServletResponse obtenerHttpServletResponse() {
        return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
    
    public static String obtenerParametroSesion(String param) {
        return (String) ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute(param);
    }

    public static void mostrarMensajeError(String resumen, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, resumen, detalle));
    }
    
    public static void mostrarMensajeInformativo(String resumen, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, resumen, detalle));
    }

    public static void despachar(String url) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extenalContext = facesContext.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest)extenalContext.getRequest()).getRequestDispatcher(url);
        try {
            dispatcher.forward((ServletRequest)extenalContext.getRequest(), (ServletResponse)extenalContext.getResponse());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        facesContext.responseComplete();
    }

    public static void redirigir(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(obtenerHttpServletRequest().getContextPath()+url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
