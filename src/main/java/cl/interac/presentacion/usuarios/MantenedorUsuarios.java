package cl.interac.presentacion.usuarios;

import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author interac
 */
@Component
@Scope("flow")
public class MantenedorUsuarios implements Serializable {
    @Autowired
    private LogicaUsuario logicaUsuario;

    private List<Usuario> usuarios;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    };
    
    private TipoOperacion operacion;

    public void inicio() {
        usuarios = logicaUsuario.obtenerTodos();
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
