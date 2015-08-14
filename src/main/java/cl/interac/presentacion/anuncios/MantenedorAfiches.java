package cl.interac.presentacion.anuncios;

import cl.interac.entidades.Afiche;
import cl.interac.negocio.LogicaAfiche;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
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
        INSERTAR,
        EDITAR
    };

    private TipoOperacion operacion;
    private Afiche afiche;
    private List<Afiche> aficheList;

    @Autowired
    private LogicaAfiche logicaAfiche;
    @Autowired
    private FileUploader fileUploader; // es un componente


    //vista
    public void MantenedorAfiches(){ afiche= new  Afiche();}
    public void agregarAfiche(Afiche a){logicaAfiche.guardar(a);}
    //flows
    public boolean esEditar() {
        return operacion == TipoOperacion.EDITAR;
    }

    public boolean esAgregar() {
        return operacion == TipoOperacion.INSERTAR;
    }

    public void subir(FileUploadEvent fue) {
        System.err.println("LLEGO A LA WA " + fue);
        String path = fileUploader.subir(fue, "/anuncios/");
        System.err.println("SE SUPONE QUE SUBI EN " + path);
        afiche.setPath(path);
        operacion = TipoOperacion.INSERTAR;
        logicaAfiche.guardar(afiche);

        if (esEditar()) {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado la campaña [" + afiche.getIdAfiche() + "]");
        } else {
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el afiche [" + afiche.getIdAfiche() + "]");
        }
        //logicaDocumentos.guardar(path, "algo más") // no se pos aqui tu decides xD
    }


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
