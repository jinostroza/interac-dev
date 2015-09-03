package cl.interac.presentacion;

import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.presentacion.campana.MantenedorCampana;
import cl.interac.util.components.FacesUtil;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by claudio on 24-04-15.
 */
@Component
@Scope("flow")
public class RegistrarBean implements Serializable {
    @Autowired
    private LogicaUsuario logicaUsuario;

    private Usuario usuario;
    private Rol rol;

    public enum TipoOperacion {
        NEXT1,
        EDITAR
    };
    private TipoOperacion tipoOperacion;
    // Spring nos instanciará el bean cuando cree el componente, pero antes debemos setear el usuario para poder usar
    // sus atributos en el jsf
    public RegistrarBean () {
        usuario = new Usuario();
    }

    public String signUp() {

        System.err.println("LLEGO A REGISTRAR");
        logicaUsuario.guardar(usuario);
        usuario.setRol(rol);
       return next1();

    }

    public String next1(){ return "next1";}
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}