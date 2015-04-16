package cl.interac.util.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by claudio on 21-11-14.
 */
@Component
@Scope("view")
public class DataTableFilter implements Serializable {
    public boolean insensitivo(Object valor, Object filtro, Locale idioma) {
        if (valor == null) return false;
        if (filtro == null) return true;
        String v = valor.toString().toLowerCase();
        String f = filtro.toString().toLowerCase();

        v = v.replaceAll("á","a");
        v = v.replaceAll("é","e");
        v = v.replaceAll("í","i");
        v = v.replaceAll("ó","o");
        v = v.replaceAll("ú","u");
        v = v.replaceAll("ü","u");

        f = f.replaceAll("á", "a");
        f = f.replaceAll("é", "e");
        f = f.replaceAll("í", "i");
        f = f.replaceAll("ó", "o");
        f = f.replaceAll("ú", "u");
        f = f.replaceAll("ü", "u");
        return v.contains(f);
    }
}
