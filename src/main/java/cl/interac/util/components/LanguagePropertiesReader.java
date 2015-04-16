package cl.interac.util.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by claudio on 17-11-14.
 */
@Component
@Scope("application")
public class LanguagePropertiesReader implements Serializable {

    public String get(String key) {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("idiomas.messages", locale);
        return rb.getString(key);
    }

    public String get(String key, String[] params) {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("idiomas.messages", locale);
        return MessageFormat.format(rb.getString(key), params);
    }

    public String get(String key, String param) {
        String[] params = new String[] { param };
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("idiomas.messages", locale);
        return MessageFormat.format(rb.getString(key), params);
    }
}
