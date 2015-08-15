package cl.interac.presentacion;



import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luisPc on 14-08-2015.
 */ @Component
    @Scope("flow")
    public class MantenedorPanelControl implements Serializable {


        @Autowired
        private LogicaUsuario logicaUsuario;

        private UserSession traerSession;
        private List<Usuario> usuarios;
        private List<Usuario> usuariosFiltro;
        private Usuario usuario;

        private enum TipoOperacion {
            INGRESAR,
            EDITAR
        }

        private TipoOperacion operacion;

        public void editarPerfil(Usuario u){
            usuario= u;


            logicaUsuario.guardar(usuario);
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el establecimiento [" + usuario.getUsername() + "]");
        }


        public void inicio() {

            logicaUsuario.obtenerTodos();

            traerSession = new UserSession();

        }




        // getter and settter
        public boolean esIngreso() {
            return operacion == TipoOperacion.INGRESAR;
        }

    public UserSession getTraerSession() {
        return traerSession;
    }

    public void setTraerSession(UserSession traerSession) {
        this.traerSession = traerSession;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
