package cl.interac.presentacion.totems;

import cl.interac.entidades.Totem;
import cl.interac.negocio.LogicaTotem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@Component
@Scope("flow")
public class MantenedorTotems implements Serializable
{
    public Totem totem;
    @Autowired
    private LogicaTotem logicaTotem;

    private List<Totem> totems;

    public void inicio() {
        totem = new Totem();
    }

    public List<Totem> getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }



}
