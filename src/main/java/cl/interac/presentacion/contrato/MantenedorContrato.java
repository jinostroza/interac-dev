package cl.interac.presentacion.contrato;

import cl.interac.entidades.Contrato;
import cl.interac.entidades.Empresa;
import cl.interac.entidades.Establecimiento;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaContrato;
import cl.interac.negocio.LogicaEmpresa;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.FileUploader;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Joaco on 11-05-2016.
 */
@Component
@Scope("flow")
public class MantenedorContrato implements Serializable
{
    private Contrato contrato;
    private Empresa empresa;
    private Usuario usuario;
    private Establecimiento establecimiento;
    private int fileUploadCount;
    @Autowired
    private LogicaContrato logicaContrato;
    @Autowired
    private LogicaEmpresa logicaEmpresa;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private FileUploader fileUploader;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private UserSession userSession;

    private List<Empresa> empresas;
    private List<Establecimiento> establecimientos;
    private List<Contrato> contratos;
    private List<Usuario> usuarios;

    @PostConstruct
    public void inicio() {
        contratos = logicaContrato.obtenerTodos();
        empresas = logicaEmpresa.obtenerTodos();
        usuarios = logicaUsuario.obtenerTodos();
        establecimientos = logicaEstablecimiento.obtenerTodos();
        contrato = new Contrato();
    }

    public String eliminar(Contrato contrato){
        logicaContrato.eliminar(contrato);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha eliminado el Contrato [" + contrato.getIdcontrato() + contrato.getIdcliente().getUsername() +  "]");
        contratos = logicaContrato.obtenerTodos();
        return "del";
    }

    public String agregarContrato(Contrato con) {
       contrato = con;

            contrato.setIdcliente(usuario);
            contrato.setIdempresa(empresa);
            contrato.setUsuario(userSession.getUsuario().getUsername());


        logicaContrato.guardar(contrato);
        contratos = logicaContrato.obtenerTodos();
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado Contrato [" + contrato.getIdcontrato() + "]");

    return "subir";
    }
    public void subirContrato(FileUploadEvent fue){
        contrato = new Contrato();
        try {
            String pathTemporal = fileUploader.subir(fue, "/contenido");

            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                contrato.setPath(pathTemporal);

            }else if ("produccion".equals(ambiente)) {
                String carpetaPrincipal = "tmp";

                String nombreArchivo = pathTemporal.substring(pathTemporal.lastIndexOf('.'));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");

                nombreArchivo = sdf.format(new Date()) + nombreArchivo;
                contrato.setPath(nombreArchivo);
                Files.copy(Paths.get(pathTemporal), Paths.get("/home/ec2-user/media/" + carpetaPrincipal + "/" + nombreArchivo));
            }

            logicaContrato.guardar(contrato);
            contratos = logicaContrato.obtenerTodos();

            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Su imagen a sido subida ");
            fileUploadCount = fileUploadCount + 1;

        } catch (Exception e) {
           e.getMessage();
        }

    }

    public void editarContrato(Contrato c) {
        contrato = c;
        logicaContrato.guardar(contrato);
        contratos = logicaContrato.obtenerTodos();
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el contrato [" +  contrato.getIdcontrato() + "]");
    }


  //getter & setter

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
}
