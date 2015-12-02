package cl.interac.presentacion.empresas;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * Created by PPablo on 01-12-2015.
 */

@Component
@Scope("view")
public class MantenedorEmpresa implements Serializable {

    private List<Empresa> empresas;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientoConfiltro;

    private Empresa empresa;

    @Autowired
    private LogicaEmpresa logicaEmpresa;

    @PostConstruct
    public void inicio(){
        empresas = logicaEmpresa.obtenerTodos();
        empresa = new Empresa();
    }

    public void agregarEmpresa() {
        logicaEmpresa.guardar(empresa);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado la empresa [" + empresa.getNombreEmpresa() + "]");
    }

    public void eliminar(Empresa empresa){
        logicaEmpresa.eliminar(empresa);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha eliminado la empresa [" + empresa.getNombreEmpresa() + "]");
}



    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public List<Empresa> getEmpresas(){
        return empresas;
    }
    public void setEmpresas(List<Empresa> empresas){
        this.empresas = empresas;
    }

}
