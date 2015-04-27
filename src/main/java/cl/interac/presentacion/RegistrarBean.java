package cl.interac.presentacion;

import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by claudio on 24-04-15.
 */
@Component
@Scope("view")
public class RegistrarBean {
    @Autowired

    private LogicaUsuario logicaUsuario;
    private Usuario usuario;

    // Spring nos instanciará el bean cuando cree el componente, pero antes debemos setear el usuario para poder usar
    // sus atributos en el jsf
    public RegistrarBean () {
        usuario = new Usuario();
    }

    public void signUp() {
        System.err.println("LLEGO A REGISTRAR");
        logicaUsuario.guardar(usuario);
        FacesUtil.mostrarMensajeInformativo("Resultado de la operación", "Usuario guardado exitosamente");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
