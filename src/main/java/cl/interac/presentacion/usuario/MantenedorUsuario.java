package cl.interac.presentacion.usuario;

import cl.interac.entidades.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author interac
 */
@Component
@Scope("flow")
public class MantenedorUsuario implements Serializable {

    private List<Usuario> usuarios;
    private Usuario usuario;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    
    private TipoOperacion operacion;

    public void inicio() {
    }

    public void guardarUsuario() {

        if (operacion == TipoOperacion.INGRESAR) {
        } else {
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }
}
