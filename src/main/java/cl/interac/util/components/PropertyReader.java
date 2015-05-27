package cl.interac.util.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by luis on 26-05-2015.
 */
@Component
@Scope("application")
public class PropertyReader implements Serializable {
    public String get(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("configuraciones.main");
        return rb.getString(key);
    }

    public String get(String key, String[] params) {
        ResourceBundle rb = ResourceBundle.getBundle("configuraciones.main");
        return MessageFormat.format(rb.getString(key), params);
    }
}
