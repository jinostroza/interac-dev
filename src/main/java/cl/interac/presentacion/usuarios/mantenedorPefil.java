package cl.interac.presentacion.usuarios;

import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import cl.interac.util.pojo.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by luisPc on 17-08-2015.
 */
@Component
@Scope("flow")
public class mantenedorPefil implements Serializable {
    @Autowired
    public LogicaUsuario logicaUsuario;

    private String claveActual;
    private String claveNueva;
    private String claveConfirmada;
    private UserSession userSession;
    private Usuario usuario;


    public void inicio(){
        logicaUsuario.obtenerTodos();
      usuario = new Usuario();

    }


    public void cambiarClave() {
        if (!claveConfirmada.equals(claveNueva)) {
            FacesUtil.mostrarMensajeError("Operación fallida", "La nueva contraseña no coincide con lo confirmado");
            return;
        }
        if (logicaUsuario.logInExterno(userSession.getUsuario().getUsername(), claveActual)== null) {
            FacesUtil.mostrarMensajeError("Operación fallida", "La nueva contraseña actual no coincide");
            return;
        }else
        logicaUsuario.cambiarClave(userSession.getUsuario().getUsername(), Md5.hash(claveConfirmada));
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha cambiado correctamente la contraseña");
    }

    //getter and setter

    public String irVerPerfil() {
        return "flowVerPerfil";
    }

    public String irCambiarClave() {
        return "flowCambiarClave";
    }


    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getClaveConfirmada() {
        return claveConfirmada;
    }

    public void setClaveConfirmada(String claveConfirmada) {
        this.claveConfirmada = claveConfirmada;
    }


}
