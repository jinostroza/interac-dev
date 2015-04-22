package cl.interac.presentacion.usuario;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by claudio on 22-04-15.
 */
@Component
@Scope("flow")
public class MantenedorRegistroUsuario implements Serializable {

    public void inicio() {

    }

    public String irPaso2() {
        return "flowPaso2";
    }

    public String irPaso1() {
        return "flowPaso1";
    }
}
