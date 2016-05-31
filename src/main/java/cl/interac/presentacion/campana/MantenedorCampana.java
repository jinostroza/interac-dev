package cl.interac.presentacion.campana;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.FileUploader;
import cl.interac.util.services.MailSender;
import com.lowagie.text.*;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by luis on 25-04-2015.
 */

@Component
@Scope("flow")
public class MantenedorCampana implements Serializable {

    // manejo manual
    private List<Campana> campanas;
    private List<Campana> contenidocampanas;
    private List<Campana> campanasvencidas;
    private List<Campana> campanaList;
    private List<Totem> totems;
    private List<Tipototem> tipototemList;
    private Tipototem tipototem;
    private Campana campana;
    private Integer pasadas;
    private Integer ValorTT;
    private Integer valor;
    private Long dias;
    private List<Usuario> usuarios;
    private List<Meses> mesesList;
    private Meses mes;
    private Totem totem;
    private Totem[] totemSelecionados;
    private List<Totem> totemList;
    private List<Totem> totemsConrelacion;
    private List<Totem> totemCampana;
    private List<Totem> totemsPorEstablecimiento;
    private Long contarTotem;
    private Long contarCampana;
    private Contenido contenido;
    private List<Contenido> contenidos;
    private List<Categoria> categoriaList;
    private Categoria categoria;
    private Establecimiento establecimiento;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientos;
    private List<Establecimiento> filtrar;
    private Establecimiento establecimientoseleccionado;
    private Establecimiento[] establecimientosLista;
    private Ubicacion ubicacion;
    private List<Ubicacion> ubicacionList;
    private MapModel advancedModel;
    private Contenido contenidosSelecionado;
    private List<Contenido> contenidosSelecionados;
    private List<Campestab> campestabList;
    private List<Campestab> campestabListAll;
    private Contenido[] contenidoslista;
    private String dateDiffValue;
    private Marker marker;
    private String newCenter;
    private Integer ubica;
    private String tipot;
    private Long contarCampanas;
    private Integer yearvalue;
    private Integer yearvalueend;
    private Integer mesIni;
    private Integer mesFin;
    private Date date;
    private boolean chkfecha;
    private boolean orientacion;
    private int fileUploadCount;
    private Integer pasadasTotales;
    private List<Empresa> empresaList;
    private Empresa empresa;
    private String nomEmpresa ;
    private String orienta;
    private Date dateini;
    private Date dateend;
    private Regiones region;
    private Provincias provincia;
    private Comunas comuna;
    private List<Comunas> comunases;
    private List<Regiones> regionesList;
    private List<Provincias> provinciasList;
    private List<Comunas> comunasList;
    private List<Usuario> usuariosL;
    private List<Establecimiento> establecimientoscorreos;



    @Autowired
    private MailSender mailSender;
    @Autowired
    private Constantes constantes;
    @Autowired
    private LogicaCategoria logicaCategoria;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private LogicaEmpresa logicaEmpresa;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaCampestab logicaCampestab;
    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private FileUploader fileUploader;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUbicacion logicaUbicacion;
    @Autowired
    private LogicaTipototem logicaTipototem;
    @Autowired
    private LogicaRegiones logicaRegiones;
    @Autowired
    private LogicaProvincias logicaProvincias;
    @Autowired
    private LogicaComunas logicaComunas;

    public void inicio() {
        if(userSession != null) {
            contarCampanas = logicaCampestab.obtenerPorNumero(userSession.getUsuario().getUsername());
            contenidocampanas = logicaCampana.obtenerPorContenido(userSession.getUsuario().getIdUsuario());
            campanasvencidas = logicaCampana.obtenerPorFecha(Date.from(Instant.now()));
            campestabList = logicaCampestab.obtenerAprobado(userSession.getUsuario().getUsername());
            campestabListAll = logicaCampestab.obtenerEstados(userSession.getUsuario().getUsername());
            usuarios = logicaUsuario.obtenerTodos();
            categoriaList = logicaCategoria.obtenerTodos();
            establecimientoList = logicaEstablecimiento.obtenerTodos();
            establecimientos = logicaEstablecimiento.obbtenerPorTotem();
            filtrar = logicaEstablecimiento.obtenerFiltro(orienta, empresa, comuna, provincia, region, categoria);
            ubicacionList = logicaUbicacion.obtenerTodas();
            tipototemList = logicaTipototem.obtenerTodos();
            totemsConrelacion = logicaTotem.obtenerConRelacion();
            totems = logicaTotem.obtenerPorUsuario(userSession.getUsuario().getUsername());
            campanas = logicaCampana.obtenerPorUsuario(userSession.getUsuario().getUsername());
            contenidos = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
            totemCampana = logicaTotem.obtenerDeCampana(userSession.getUsuario().getUsername());
            usuarios = logicaUsuario.obtenerTodos();
            empresaList = logicaEmpresa.obtenerTodos();
            regionesList = logicaRegiones.obtenerTodas();
            provinciasList = logicaProvincias.obtenerTodas();
            comunasList = logicaComunas.obtenerTodas();
        }
    }

    public void subir(FileUploadEvent fue) {

        contenido = new Contenido();

        try {
            String pathTemporal = fileUploader.subir(fue, "/contenido");

            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                contenido.setPath(pathTemporal);

            }else if ("produccion".equals(ambiente)) {
                String carpetaPrincipal = "tmp";

                String nombreArchivo = pathTemporal.substring(pathTemporal.lastIndexOf('.'));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");

                nombreArchivo = sdf.format(new Date()) + nombreArchivo;
                contenido.setPath(nombreArchivo);
                Files.copy(Paths.get(pathTemporal), Paths.get("/home/ec2-user/media/" + carpetaPrincipal+"/"+ nombreArchivo));
            }

            contenido.setUsuario(userSession.getUsuario());
            logicaContenido.guardar(contenido);

            FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Su imagen a sido subida ");
            fileUploadCount = fileUploadCount + 1;

        } catch (Exception e) {
            return;
        }

    }

    public String editarContenido(Contenido c) {
        contenido = c;
        contenido.setCategoria(categoria);
        //orientacion
        logicaContenido.guardar(contenido);
        categoria=null;

        return irCrear(c);

    }
    public String irPagar(Campana ca ){
        campana = ca ;
        return "paga";
    }

     public String irCrear(Contenido c) {
         contenido = c;
         contenidos = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
         return "crear";
    }
    public String irFiltrar(){
        return "filtrar";
    }

    public String  guardar(){

        Integer count =establecimientosLista.length;
        if (count.equals(0)){
            FacesUtil.mostrarMensajeError("Error", "Debe seleccionar al menos un establecimiento");
            return "subir";
        }
        if(campana.getFechaInicio().equals(null) || campana.getFechaFin().equals(null)){
            FacesUtil.mostrarMensajeError("Error", "Debe seleccionar las fechas para su campaña");
            return "subir";

        } else {
            System.out.println(contenidoslista.length);

            //perisitencia


            campana.setContenidoList(Arrays.asList(contenidoslista));

            campana.setFechaCreacion(Date.from(Instant.now()));
            campana.setEstablecimientoList(Arrays.asList(establecimientosLista));
            campana.setPasadas(pasadas);
            campana.setValor(valor);
            String ambiente = propertyReader.get("ambiente");
            if ("desarrollo".equals(ambiente)) {
            // dentro del server siempre podra subir, no importa si es wintendo o linux
            logicaCampana.guardarCampana(campana);

        }else if ("produccion".equals(ambiente)) {
        logicaCampana.guardarCampana(campana);

                FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "se ha creado tu Campaña");
    }
            dateDiff();
            return "end1";}
    }
    public String notificar(){

           //perisitencia


            campana.setContenidoList(Arrays.asList(contenidoslista));

            campana.setFechaCreacion(Date.from(Instant.now()));
            campana.setEstablecimientoList(Arrays.asList(establecimientosLista));
            campana.setPasadas(pasadas);
            campana.setValor(valor);
            String ambiente = propertyReader.get("ambiente");
            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                logicaCampana.guardarCampana(campana);

            }else if ("produccion".equals(ambiente)) {
                logicaCampana.guardarCampana(campana);
                //cuerpo del mensaje
                //String mensajeLocal = new String(constantes.getAlertas());
                //String mensajeAnunciante = new String(constantes.getHeaderCorreo());
                //mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$id",String.valueOf(campana.getIdcampana()));
                //mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$nombrecampana",campana.getNombrecampana());
                //mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$valortotal",String.valueOf(valor));


                //correo
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
               // String[] replicas = new String[1];
                //String[] alertas = new String[2];
                //for(Establecimiento et : establecimientosLista) {
                  //  establecimientoscorreos = logicaEstablecimiento.obtenerPorIDUsuario(et.getUsuario().getIdUsuario(),et.getIdEstablecimiento());
                   // for(Establecimiento u : establecimientoscorreos) {
                     //   replicas[0] = u.getUsuario().getCorreo();
                       // try {
                         //   Thread.sleep(6000);
                           // mailSender.send(replicas, "Interac", mensajeLocal);
                        //} catch (InterruptedException e) {
                          //  e.printStackTrace();
                            //FacesUtil.mostrarMensajeInformativo("Error", "Fallo correo"+replicas );
                       // }
                    //}
                //}

                //alertas[0]="contacto@interac.cl";
                //alertas[1]=userSession.getUsuario().getCorreo();
                //mailSender.send(alertas,"Interac",mensajeAnunciante);


                FacesUtil.mostrarMensajeInformativo("Operacion exitosa", "se ha creado tu Campaña");
            }
            dateDiff();

            return "fin" ;
    }


       public String eliminarFichero(Contenido conte){

        try {
            String ambiente = propertyReader.get("ambiente");


            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                logicaContenido.eliminarContenido(conte);
                FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha borrado la imagen");
                contenidos = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());

            }
            else if ("produccion".equals(ambiente)) {
                logicaContenido.eliminarContenido(conte);
                Files.delete(Paths.get("/home/ec2-user/media/tmp/" + conte.getPath()));
                FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha borrado la imagen");
                contenidos = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
            }

        }catch (Exception e){
            FacesUtil.mostrarMensajeInformativo("Operación Fallida", "Verifique que el contenido no este siendo utilizado");

        }
    return "crear";
       }
    public String nombreComuna(Integer com){
        comuna = logicaComunas.obtenerNombre(com);
     return  comuna.getComuna_nombre() ;
    }
    public String nombreCategoria(Integer cat){
        categoria = logicaCategoria.obtenerNombre(cat);
        return  categoria.getDesccategoria() ;
    }

    public String eliminarCampana(Campana campa){
        try {

            for(Establecimiento et : campa.getEstablecimientoList()) {
                String carpetaDestino = et.getCarpetaFtp();
                for(Contenido co : campa.getContenidoList() ){
                    Files.delete(Paths.get("/home/ec2-user/media/" + carpetaDestino + "/" + co.getPath()));
                }
            }
                logicaCampana.eliminarCampana(campa);

                FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha eliminado la Campaña");
            campanas = logicaCampana.obtenerPorUsuario(userSession.getUsuario().getUsername());

        }catch (Exception e){
            FacesUtil.mostrarMensajeError("Operación Fallida", "Algo Ocurrio");
        }
        return "return";
    }

    public String ver(int t){
        System.err.println("Totem:" + t);

        return "ver";
    }
    public Long numeroTotem(Integer numTotem){

        contarTotem=logicaTotem.obtenerPorNumero(numTotem);
        return contarTotem;
    }
    public Long numeroCampanas(Integer numCampana){

        contarCampana=logicaCampana.obtenerPorEstablecimiento(numCampana);
        return contarCampana;
    }


    public void dateStart () {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer month = localDate.getMonthValue();
        Integer year = localDate.getYear();
        System.err.println(mes.getIdmes());
        mesIni=mes.getIdmes();
        if (month.equals(mesIni) && (year.equals(yearvalue))) {
            System.err.println(mes.getIdmes());
            System.err.println(yearvalue);
            System.err.println(localDate.getDayOfMonth());
            campana.setFechaInicio(date.from(Instant.now()));
        }else if ((mesIni < month) && (year.equals(yearvalue))){
            FacesUtil.mostrarMensajeError("Error fecha", "no puede elegir una fecha ya pasada");
        }
        else if ((mesIni < month) && (year>yearvalue)){
            campana.setFechaInicio(getDateStart(mesIni, yearvalue));
        }else if ((mesIni>= month) && (yearvalue!=null)){
            campana.setFechaInicio(getDateStart(mesIni, yearvalue));
        }
    }
    public void dateEnd (){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer month = localDate.getMonthValue();
        Integer year = localDate.getYear();
        System.err.println(mes.getIdmes());
        mesFin = mes.getIdmes();
        if (month.equals(mesFin) && (year.equals(yearvalueend))){
            System.err.println(mesFin);
            System.err.println(yearvalueend);
            System.err.println(localDate.getDayOfMonth());
            campana.setFechaFin(getDateEnd(mesFin, yearvalueend));
        }else if ((mesFin<month) && (year.equals(yearvalueend))){
            FacesUtil.mostrarMensajeInformativo("Operación Fallida", "No puede programar una fecha anterior a la actual");
        }else if ((mesFin<month) && (year>yearvalueend)){
            campana.setFechaFin(getDateEnd(mesFin, yearvalueend));
        }else if ((mesFin>= month) && (yearvalueend!=null)){
            campana.setFechaFin(getDateEnd(mesFin, yearvalueend));
        }
            }
    // permite el retorno del ultimo día de un mes X
   public Date getDateEnd(Integer m, Integer y) {
        Calendar calendar = Calendar.getInstance();
        // passing month-1 because 0-->jan, 1-->feb... 11-->dec
        calendar.set(y, m - 1, 1);
       calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date date = calendar.getTime();
       System.err.println(date);
        return date;
    }
    public Date getDateStart(Integer m, Integer y) {
        Calendar calendar = Calendar.getInstance();
        // passing month-1 because 0-->jan, 1-->feb... 11-->dec
        calendar.set(y, m - 1, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        Date date = calendar.getTime();
        System.err.println(date);
        return date;
    }

    // obtiene en entero el rango de horas de un establecimiento
    public Integer calcularHoras(Establecimiento est){
        Long tiempoInicial = est.getHoraInicio().getTime()/3600000;
        Long tiempoFinal = est.getHoraTermino().getTime()/3600000;

        Long resultado = (tiempoFinal-3) - (tiempoInicial-3);

        return resultado.intValue();
    }
    //inicializa campaña
    public String creaCampana(){
        campana = new Campana();
        ValorTT = 0;
        Integer count = contenidoslista.length;
        if (count.equals(0)){
            FacesUtil.mostrarMensajeError("Operación Fallida", "Debe seleccionar al menos 1 anuncio");
            return "crear";
        }else {
            System.out.println(contenidoslista.length);
            return "programar";
        }

    }
    //Setea programacion campaña
    public String programaCampana(Campana c){
        campana = c;
        campana.setNombrecampana(campana.getNombrecampana());
        campana.setFechaInicio(campana.getFechaInicio());
        campana.setFechaFin(campana.getFechaFin());
        campana.setEstado("Esperando Aprobacion");
        logicaCampana.guardarCampana(campana);


     return "subir";
    }

    public List<Totem> totemsEST(Integer idestablecimiento) {
        totemsPorEstablecimiento=logicaTotem.obtenerPorestablecimiento(idestablecimiento);
        return totemsPorEstablecimiento;
    }


   // retorna en entero la cantidad de pasadas segun
    //la hora de apertura y cierre del establecimiento, ademas de los slots disponibles
    public Integer calcularPasadas(Establecimiento est){

        Integer horas = calcularHoras(est);
        Integer cantidadPasadas = ((horas * 3600) / (10 * est.getSlots()));

        return cantidadPasadas;
    }
    // calcula valores parciales y totales
    public Integer calculator(Establecimiento est){
        pasadas=calcularPasadas(est);
        if (userSession.getUsuario().getIdUsuario().equals(est.getUsuario().getIdUsuario())) {
        //si el usuario es dueño del establecimiento no tendra costo
            valor = (pasadas * 0) * (dias.intValue() + 1);
            ValorTT = ValorTT + valor;
        return valor;
        }else {
            valor = (pasadas * est.getValor()) * (dias.intValue() + 1);
            ValorTT = ValorTT + valor;
        return valor;
        }
    }
    public Integer diasPasadas(Establecimiento est){
        pasadas=calcularPasadas(est);

        pasadasTotales=pasadas*(dias.intValue()+1);


    return pasadasTotales;
    }
    public String dateDiff() {

        if(campana.getFechaInicio()!=null && campana.getFechaFin()!=null)
        {
            //HH converts hour in 24 hours format (0-23), day calculation
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            Date d1 = null;
            Date d2 = null;
            System.out.println(campana.getFechaInicio());
            System.out.println(campana.getFechaFin().toString());

            try {
                d1 = campana.getFechaInicio();
                d2 = campana.getFechaFin();


                //in milliseconds
                long diff = d2.getTime() - d1.getTime();

                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                dias=diffDays;
                dateDiffValue=diffDays+1+"";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    return dateDiffValue;
    }
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        System.err.print(marker);
    }

    public String location(Totem t){
        totem = t;
        newCenter= totem.getLat()+","+totem.getLongi();
        advancedModel = new DefaultMapModel();
        advancedModel.addOverlay(new Marker(new LatLng(totem.getLat(), totem.getLongi()), totem.getEstablecimiento().getNombreEstablecimiento()
                , "http://www.google.com/mapfiles/dd-start.png"));
         advancedModel = null;
        System.err.println(totem.getLat() + "," + totem.getLongi() + "," + newCenter);
       return newCenter;

    }
    public void addMessage() {
        String summary = orientacion ? "Checked" : "Unchecked";
        if (summary.equals("Checked")){
            orienta="Vertical";
            System.out.println(orienta);

        }
        if (summary.equals("Unchecked")){
            orienta="Horizontal";
            System.out.println(orienta);
        }

    }
    public void filtrarestablecimiento(){

        if (orienta.equals("")){
            orienta=null;

        }
        System.out.println("pasando");
        System.out.println(orienta);

        System.out.println(empresa);
        System.out.println(comuna);
        System.out.println(categoria);
        filtrar = logicaEstablecimiento.obtenerFiltro(orienta, empresa, comuna, provincia, region, categoria);

    }
    //filtra lista de provincias segun region
    public void provincias(){

        provinciasList = logicaProvincias.obtenerConRealacion(region.getRegion_id());

    }
    //filtra lista de comunas segun provincia
    public void comunas(){

        comunasList = logicaComunas.obtenerConRealacion(provincia.getProvincia_id());
    }
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "css" + File.separator + "img" + File.separator + "tools-01.png";

        pdf.add(Image.getInstance(logo));
    }

    //Getter and Setter
    public Long getContarCampanas() {
        return contarCampanas;
    }

    public void setContarCampanas(Long contarCampanas) {
        this.contarCampanas = contarCampanas;
    }

    public Marker getMarker() {
        return marker;
    }

    public String getNewCenter() {
        return newCenter;
    }

    public void setNewCenter(String newCenter) {
        this.newCenter = newCenter;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public Integer getFileUploadCount() {
        return fileUploadCount;
    }

    public void setFileUploadCount(Integer fileUploadCount) {
        this.fileUploadCount = fileUploadCount;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public Totem[] getTotemSelecionados() {
        return totemSelecionados;
    }

    public void setTotemSelecionados(Totem[] totemSelecionados) {
        this.totemSelecionados = totemSelecionados;
    }

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void setAdvancedModel(MapModel advancedModel) {
        this.advancedModel = advancedModel;
    }

    public List<Totem> getTotemsConrelacion() {
        return totemsConrelacion;
    }

    public void setTotemsConrelacion(List<Totem> totemsConrelacion) {
        this.totemsConrelacion = totemsConrelacion;
    }

    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    public Campana getCampana() {
        return campana;
    }

    public void setCampana(List<Campana> obtenerCampana) {
        this.campanas = obtenerCampana;
    }

    public List<Campana> getCampanas() {
        return campanas;
    }

    public void setCampanas(List<Campana> campanas) {
        this.campanas = campanas;
    }

    public List<Totem> getTotems() {
        return totems;
    }

    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }
    public Contenido getContenidosSelecionado() {
        return contenidosSelecionado;
    }

    public void setContenidosSelecionado(Contenido contenidosSelecionado) {
        this.contenidosSelecionado = contenidosSelecionado;
    }

    public List<Contenido> getContenidosSelecionados() {
        return contenidosSelecionados;
    }

    public void setContenidosSelecionados(List<Contenido> contenidosSelecionados) {
        this.contenidosSelecionados = contenidosSelecionados;
    }

    public List<Totem> getTotemCampana() {
        return totemCampana;
    }

    public void setTotemCampana(List<Totem> totemCampana) {
        this.totemCampana = totemCampana;
    }

    public String getDateDiffValue() {
        return dateDiffValue;
    }

    public void setDateDiffValue(String dateDiffValue) {
        this.dateDiffValue = dateDiffValue;
    }
    public List<Campana> getCampanaList() {
        return campanaList;
    }

    public void setCampanaList(List<Campana> campanaList) {
        this.campanaList = campanaList;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    public List<Totem> getTotemList() {
        return totemList;
    }

    public void setTotemList(List<Totem> totemList) {
        this.totemList = totemList;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;

    }

    public List<Ubicacion> getUbicacionList() {
        return ubicacionList;
    }

    public void setUbicacionList(List<Ubicacion> ubicacionList) {
        this.ubicacionList = ubicacionList;
    }

    public Tipototem getTipototem() {
        return tipototem;
    }

    public void setTipototem(Tipototem tipototem) {
        this.tipototem = tipototem;
    }

    public List<Tipototem> getTipototemList() {
        return tipototemList;
    }

    public void setTipototemList(List<Tipototem> tipototemList) {
        this.tipototemList = tipototemList;
    }

    public Integer getUbica() {
        return ubica;
    }

    public void setUbica(Integer ubica) {
        this.ubica = ubica;
    }

    public String getTipot() {
        return tipot;
    }

    public void setTipot(String tipot) {
        this.tipot = tipot;
    }

    public Long getDias() {
        return dias;
    }

    public Integer getPasadas() { return pasadas; }


    public void setPasadas(Integer pasadas) {
        this.pasadas = pasadas;
    }

    public void setDias(Long dias) {
        this.dias = dias;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public Establecimiento getEstablecimientoseleccionado() {
        return establecimientoseleccionado;
    }

    public void setEstablecimientoseleccionado(Establecimiento establecimientoseleccionado) {
        this.establecimientoseleccionado = establecimientoseleccionado;
    }

    public Long getContarTotem() {
        return contarTotem;
    }

    public void setContarTotem(Long contarTotem) {
        this.contarTotem = contarTotem;
    }

    public List<Totem> getTotemsPorEstablecimiento() {
        return totemsPorEstablecimiento;
    }

    public void setTotemsPorEstablecimiento(List<Totem> totemsPorEstablecimiento) {
        this.totemsPorEstablecimiento = totemsPorEstablecimiento;
    }

    public Long getContarCampana() {
        return contarCampana;
    }

    public void setContarCampana(Long contarCampana) {
        this.contarCampana = contarCampana;
    }

    public List<Meses> getMesesList() {
        return mesesList;
    }

    public void setMesesList(List<Meses> mesesList) {
        this.mesesList = mesesList;
            }

    public Meses getMes() {
        return mes;
    }

    public void setMes(Meses mes) {
        this.mes = mes;
    }

    public Integer getYearvalue() {
        return yearvalue;
    }

    public void setYearvalue(Integer yearvalue) {
        this.yearvalue = yearvalue;
    }

    public Integer getYearvalueend() {
        return yearvalueend;
    }

    public void setYearvalueend(Integer yearvalueend) {
        this.yearvalueend = yearvalueend;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isChkfecha() {
        return chkfecha;
    }

    public void setChkfecha(boolean chkfecha) {
        this.chkfecha = chkfecha;
    }

    public List<Campana> getCampanasvencidas() {
        return campanasvencidas;
    }

    public void setCampanasvencidas(List<Campana> campanasvencidas) {
        this.campanasvencidas = campanasvencidas;
    }

    public Integer getMesIni() {
        return mesIni;
    }

    public void setMesIni(Integer mesIni) {
        this.mesIni = mesIni;
    }

    public Integer getMesFin() {
        return mesFin;
    }

    public void setMesFin(Integer mesFin) {
        this.mesFin = mesFin;
    }

    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getPasadasTotales() {
        return pasadasTotales;
    }

    public void setPasadasTotales(Integer pasadasTotales) {
        this.pasadasTotales = pasadasTotales;
    }

    public List<Establecimiento> getFiltrar() {
        return filtrar;
    }

    public void setFiltrar(List<Establecimiento> filtrar) {
        this.filtrar = filtrar;
    }

    public String getOrienta() {
        return orienta;
    }

    public void setOrienta(String orienta) {
        this.orienta = orienta;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }


    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public Establecimiento[] getEstablecimientosLista() {
        return establecimientosLista;
    }

    public void setEstablecimientosLista(Establecimiento[] establecimientosLista) {
        this.establecimientosLista = establecimientosLista;
    }

    public List<Campestab> getCampestabList() {
        return campestabList;
    }

    public void setCampestabList(List<Campestab> campestabList) {
        this.campestabList = campestabList;
    }

    public boolean isOrientacion() {
        return orientacion;
    }

    public void setOrientacion(boolean orientacion) {
        this.orientacion = orientacion;
    }

    public Date getDateini() {
        return dateini;
    }

    public void setDateini(Date dateini) {
        this.dateini = dateini;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public Integer getValorTT() {
        return ValorTT;
    }

    public void setValorTT(Integer valorTT) {
        ValorTT = valorTT;
    }

    public Contenido[] getContenidoslista() {
        return contenidoslista;
    }

    public void setContenidoslista(Contenido[] contenidoslista) {
        this.contenidoslista = contenidoslista;
    }
    public List<Regiones> getRegionesList() {
        return regionesList;
    }

    public void setRegionesList(List<Regiones> regionesList) {
        this.regionesList = regionesList;
    }

    public List<Provincias> getProvinciasList() {
        return provinciasList;
    }

    public void setProvinciasList(List<Provincias> provinciasList) {
        this.provinciasList = provinciasList;
    }

    public List<Comunas> getComunasList() {
        return comunasList;
    }

    public void setComunasList(List<Comunas> comunasList) {
        this.comunasList = comunasList;
    }

    public Comunas getComuna() {
        return comuna;
    }

    public void setComuna(Comunas comuna) {
        this.comuna = comuna;
    }

    public Provincias getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincias provincia) {
        this.provincia = provincia;
    }

    public Regiones getRegion() {
        return region;
    }

    public void setRegion(Regiones region) {
        this.region = region;
    }

    public List<Campestab> getCampestabListAll() {
        return campestabListAll;
    }

    public void setCampestabListAll(List<Campestab> campestabListAll) {
        this.campestabListAll = campestabListAll;
    }

    public List<Usuario> getUsuariosL() {
        return usuariosL;
    }

    public void setUsuariosL(List<Usuario> usuariosL) {
        this.usuariosL = usuariosL;
    }

    public List<Establecimiento> getEstablecimientoscorreos() {
        return establecimientoscorreos;
    }

    public void setEstablecimientoscorreos(List<Establecimiento> establecimientoscorreos) {
        this.establecimientoscorreos = establecimientoscorreos;
    }

    public List<Comunas> getComunases() {
        return comunases;
    }

    public void setComunases(List<Comunas> comunases) {
        this.comunases = comunases;
    }

    public List<Campana> getContenidocampanas() {
        return contenidocampanas;
    }

    public void setContenidocampanas(List<Campana> contenidocampanas) {
        this.contenidocampanas = contenidocampanas;
    }
}
