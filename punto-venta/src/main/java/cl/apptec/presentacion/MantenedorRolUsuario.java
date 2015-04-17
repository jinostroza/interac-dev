/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.apptec.presentacion;


import cl.apptec.entidades.RolUsuario;
import cl.apptec.negocio.LogicaRolUsuario;
import cl.apptec.util.components.FacesUtil;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author apptec
 */
@Component
@Scope("flow")
public class MantenedorRolUsuario implements Serializable {

    private List<RolUsuario> rolesUsuario;
    private RolUsuario rolUsuarioSeleccionado;

    private enum TipoOperacion {
        INGRESAR,
        EDITAR
    }

    ;
    private TipoOperacion operacion;

    @Autowired
    private LogicaRolUsuario logicaRolUsuario;

    public void inicio() {
        rolesUsuario = logicaRolUsuario.obtenerTodos();
    }

    public String irAgregar() {
        rolUsuarioSeleccionado = new RolUsuario();
        operacion = TipoOperacion.INGRESAR;
        return "flowAgregar";
    }

    public String irEditar(RolUsuario r) {
        rolUsuarioSeleccionado = r;
        operacion = TipoOperacion.EDITAR;
        return "flowAgregar";
    }

    public String irListar() {
        return "flowListar";
    }

    public void eliminarRolUsuario() {
        rolesUsuario.remove(rolUsuarioSeleccionado);
        logicaRolUsuario.eliminarRolUsuario(rolUsuarioSeleccionado);
    }

    public void guardarRolUsuario() {
        logicaRolUsuario.guardarRolUsuario(rolUsuarioSeleccionado);
        if (operacion == TipoOperacion.INGRESAR) {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha creado el nuevo Rol de usuario [ID: " + rolUsuarioSeleccionado.getIdRolUsuario() + "]");
            rolesUsuario.add(rolUsuarioSeleccionado);
            rolUsuarioSeleccionado = new RolUsuario();
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación exitosa", "Se ha editado Rol de usuario [ID: " + rolUsuarioSeleccionado.getIdRolUsuario() + "]");
        }
    }

    public boolean esIngreso() {
        return operacion == TipoOperacion.INGRESAR;
    }

    public boolean esEdicion() {
        return operacion == TipoOperacion.EDITAR;
    }

    public List<RolUsuario> getRolesUsuario() {
        return rolesUsuario;
    }

    public void setRolesUsuario(List<RolUsuario> rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
    }

    public RolUsuario getRolUsuarioSeleccionado() {
        return rolUsuarioSeleccionado;
    }

    public void setRolUsuarioSeleccionado(RolUsuario rolUsuarioSeleccionado) {
        this.rolUsuarioSeleccionado = rolUsuarioSeleccionado;
    }


}
