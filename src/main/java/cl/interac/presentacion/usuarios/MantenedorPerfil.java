
package cl.interac.presentacion.usuarios;

import cl.interac.entidades.Empresa;
import cl.interac.entidades.Rol;
import cl.interac.entidades.Usuario;
import cl.interac.negocio.LogicaEmpresa;
import cl.interac.negocio.LogicaUsuario;
import cl.interac.security.LogInManager;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by luisPc on 17-08-2015.
 */
@Component
@Scope("flow")
public class MantenedorPerfil implements Serializable {
    @Autowired
    public LogicaUsuario logicaUsuario;
    @Autowired
    public LogicaEmpresa logicaEmpresa;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private UserSession userSession; // es un componente spring y de scope session, por ende hay que
    @Autowired
    LogInManager logInManager;
    @Autowired
    private Constantes constantes;
    @Autowired
    private MailSender mailSender;

    private String claveActual;
    private String claveNueva;
    private String claveConfirmada;
    private Rol rol;
    private String correo;
    private String empresa;
    private String username;
    private Usuario usuario;
    private List<Usuario> usuarioList;
    private Empresa empresas;
    private String verifica = "false";
    private String recupera = "true";
    private List<Empresa> empresaList;

    private Long retornoCorreos;
    private Integer idUsuario;

    public void inicio() {
        usuarioList = logicaUsuario.obtenerTodos();
        empresaList = logicaEmpresa.obtenerTodos();
        usuario = new Usuario();
    }

       public void cambiaPerfil() {
        if (!claveConfirmada.equals(claveNueva)) {
            FacesUtil.mostrarMensajeError("Operación fallida", "La nueva contraseña no coincide con lo confirmado");
            return;
        }
        else{
            logicaUsuario.cambiarClave(userSession.getUsuario().getUsername(), md5(claveConfirmada));
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha cambiado correctamente la contraseña");
        }

        if (!userSession.getUsuario().getPassword().equals(claveActual)) {
            FacesUtil.mostrarMensajeError("Operación fallida", "contraseña invalida");
            return;
        }

        logicaUsuario.editarPerfil(userSession.getUsuario().getUsername(),correo,empresa);
        FacesUtil.mostrarMensajeInformativo("Operación exitosa", "usuario [" + userSession.getUsuario().getUsername() + "] modificado");
    }
    public String verificar(String pass){
        claveActual = pass;
        if (!usuario.getPassword().equals(claveActual)){
            FacesUtil.mostrarMensajeError("Operación fallida", "Código de Verificación Incorrecto");
            verifica = "false";
            recupera = "true";
            return "verify";
        }else {
            verifica = "true";
            recupera = "false";
            return "verify";
        }

    }
    public String recuperar() {
      if (!claveConfirmada.equals(claveNueva)) {
            FacesUtil.mostrarMensajeError("Operación fallida", "La nueva contraseña no coincide con lo confirmado");
            return "verify";
        }
        else{
            logicaUsuario.cambiarClave(usuario.getUsername(), md5(claveConfirmada));
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Su contraseña se modifico  correctamente");
        }
    return "next1" ;
    }
    public String nombreEmpresa(Integer emp){
        empresas = logicaEmpresa.obtenerNombre(emp);
        return  empresas.getNombreEmpresa();
    }
    public static String md5(String input){

        String md5 = null; //La variable esta vacia
        if(null == input){ //Si es nulo el input, la funcion retornara null
            return null;
        }

        try{
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public void verificarCorreo(String correoEntrante){
        retornoCorreos = logicaUsuario.verificarCorreo(correoEntrante);
        System.out.println("Correo entrante: "+correoEntrante);
        String ambiente = propertyReader.get("ambiente");
        if (retornoCorreos >= 1){
            System.out.println("Correo existe");
            usuario = logicaUsuario.obtenerPorCorreo(correoEntrante);
            String code = usuario.getPassword();
            System.out.println("ID: "+usuario.getIdUsuario());
            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                System.out.println(code);

            }else if ("produccion".equals(ambiente)) {
                //cuerpo del mensaje
                String mensajeLocal = new String(constantes.getRecuperar());
                mensajeLocal = mensajeLocal.replaceFirst("\\$codigo", String.valueOf(code));

                //correo
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String[] replicas = new String[1];

                replicas[0] = correoEntrante;
                mailSender.send(replicas, "Interac", mensajeLocal);
            }

            FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "Se ah enviado informacion de recuperacion a su correo");

        }

        else{
            System.out.println("Correo no existe");
        }
    }

    public Usuario restablecerClave(){
        return logicaUsuario.obtenerPorID(idUsuario);
    }

    //getter and setter
    public String irVerPerfil() {
        return "flowVerPerfil";
    }

    public String irCambiarClave() {
        return "flowCambiarClave";
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getClaveNueva() { return claveNueva; }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getClaveConfirmada() { return claveConfirmada; }

    public void setClaveConfirmada(String claveConfirmada) {
        this.claveConfirmada = claveConfirmada;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Integer getIdUsuario() { return idUsuario; }

    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getVerifica() {
        return verifica;
    }

    public void setVerifica(String verifica) {
        this.verifica = verifica;
    }

    public String getRecupera() {
        return recupera;
    }

    public void setRecupera(String recupera) {
        this.recupera = recupera;
    }

    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }
}
