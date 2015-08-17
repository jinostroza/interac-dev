package cl.interac.util.dto;

import java.io.Serializable;

/**
 * Created by luisPc on 17-08-2015.
 */
public class UsuarioDto implements Serializable {


    private String username;
    private String empresa;
    private String correo;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
