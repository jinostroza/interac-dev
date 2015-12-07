package cl.interac.presentacion;

import cl.interac.entidades.Campana;
import cl.interac.entidades.Totem;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudio on 03-10-15.
 */
@Component
@Scope("view")
public class Test {
    @Autowired
    private LogicaCampana logicaCampana;

    private Usuario usuario;
    @Autowired
    private LogicaUsuario logicaUsuario;

    private Campana c;

    @PostConstruct
    public void inicio() {
        /// c = logicaCampana.obtenerPorIdConTotems(58);
    }

    public String botton(){
        usuario = logicaUsuario.obtenerPorUsuarioContrasenna("dreamteam","dreamteam");
        System.err.print(usuario);
        return usuario.getUsername();
    }

    public String dummy() {
        List<Totem> totems = new ArrayList<Totem>();
        Totem t = new Totem();
        t.setIdtotem(201);
        totems.add(t);
        c.setTotemList(totems);

        logicaCampana.guardarCampana(c);
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
