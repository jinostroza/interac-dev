package cl.interac.presentacion;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by claudio on 15-10-14.
 */
@Component
@Scope("view")
public class UtilBean implements Serializable {
    public Integer getAnioActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return Integer.valueOf(sdf.format(new Date()));
    }
}
