package cl.interac.presentacion.anuncios;

import cl.interac.entidades.Afiche;
import cl.interac.negocio.LogicaAfiche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 28-05-2015.
 */
@Component
@Scope("flow")
public class MantenedorAfiches implements Serializable {
 public enum TipoOperacion{
        INSENTAR,
        EDITAR
    };


    private Afiche afiche;
    private List<Afiche> aficheList;

    @Autowired
    private LogicaAfiche logicaAfiche;


    //vista
    public void MantenedorAfiches(){ afiche= new  Afiche();}
    public void agregarAfiche(Afiche a){logicaAfiche.guardar(a);}




    //getter and setter


    public List<Afiche> getAficheList() {
        return aficheList;
    }

    public void setAficheList(List<Afiche> aficheList) {
        this.aficheList = aficheList;
    }

    public Afiche getAfiche() {
        return afiche;
    }

    public void setAfiche(Afiche afiche) {
        this.afiche = afiche;
    }
}
