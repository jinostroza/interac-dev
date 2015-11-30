package cl.interac.presentacion.campana;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.scheduled.CronService;
import cl.interac.util.components.*;
import cl.interac.util.services.FileUploader;
import cl.interac.util.services.MailSender;
import org.primefaces.event.FileUploadEvent;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * Created by luis on 25-04-2015.
 */

@Component
@Scope("flow")
public class MantenedorCampana implements Serializable {

    // manejo manual

    private List<Campana> campanas;
    private List<Campana> campanasvencidas;
    private List<Campana> campanaList;
    private List<Totem> totems;
    private List<Tipototem> tipototemList;
    private Tipototem tipototem;
    private Campana campana;
    private Integer precio = 360;
    private Integer pasadas;
    private Integer valor;
    private Long dias;
    private String retor;
    private String end1;
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
    private Contenido contenido;
    private List<Contenido> contenidos;
    private List<Categoria> categoriaList;
    private Categoria categoria;
    private Establecimiento establecimiento;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientos;
    private Establecimiento establecimientoseleccionado;
    private Ubicacion ubicacion;
    private List<Ubicacion> ubicacionList;
    private MapModel advancedModel;
    private Contenido contenidosSelecionado;
    private List<Contenido> contenidosSelecionados;
    private String dateDiffValue;
    private Marker marker;
    private String newCenter;
    private Integer ubica;
    private String tipot="";
    private Long contarCampanas;
    private Integer yearvalue;
    private Integer yearvalueend;
    private Integer mesIni;
    private Integer mesFin;
    private Date date;
    private boolean chkfecha ;




    @Autowired
    private MailSender mailSender;
    @Autowired
    private Constantes constantes;
    @Autowired
    private LogicaCategoria logicaCategoria;
    @Autowired
    private LogicaUsuario logicaUsuario;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private LogicaMeses logicaMeses;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private FileUploader fileUploader;
    private int fileUploadCount;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaUbicacion logicaUbicacion;
    @Autowired
    private LogicaTipototem logicaTipototem;


    public void inicio() {
        contarCampanas = logicaCampana.obtenerPorNumero(userSession.getUsuario().getUsername());
        campanasvencidas = logicaCampana.obtenerPorFecha(Date.from(Instant.now()));
        usuarios = logicaUsuario.obtenerTodos();
        categoriaList = logicaCategoria.obtenerTodos();
        establecimientoList=logicaEstablecimiento.obtenerTodos();
        establecimientos=logicaEstablecimiento.obbtenerPorTotem();
        ubicacionList=logicaUbicacion.obtenerTodas();
        tipototemList=logicaTipototem.obtenerTodos();
        totemsConrelacion = logicaTotem.obtenerConRelacion();
        totems = logicaTotem.obtenerPorUsuario(userSession.getUsuario().getUsername());
        campanas = logicaCampana.obtenerPorUsuario(userSession.getUsuario().getUsername());
        contenidos = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
        campanaList= logicaCampana.obtenerLasCampanasDeLosTotems(userSession.getUsuario().getUsername());
        totemCampana = logicaTotem.obtenerDeCampana(userSession.getUsuario().getUsername());
        usuarios = logicaUsuario.obtenerTodos();
        mesesList = logicaMeses.obtenerTodos();

    }

    public void subir(FileUploadEvent fue) {


        contenido = new Contenido();

        try {
            String pathTemporal = fileUploader.subir(fue,"/contenido");

            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                contenido.setPath(pathTemporal);

            }else if ("produccion".equals(ambiente)) {
                String carpetaPrincipal = "demo2";

                String nombreArchivo = pathTemporal.substring(pathTemporal.lastIndexOf('.'));


                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");

                nombreArchivo = sdf.format(new Date()) + nombreArchivo;
                Files.copy(Paths.get(pathTemporal), Paths.get("/home/ec2-user/media/"+carpetaPrincipal+"/"+ nombreArchivo));
                contenido.setPath(nombreArchivo);
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

        logicaContenido.guardar(contenido);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha editado el Contenido [" + contenido.getIdcontenido() + "]");

      return irCrear(c);

    }
    public String subepucv(Contenido c) {
        contenido = c;
        contenido.setCategoria(categoria);

        logicaContenido.guardar(contenido);
        FacesUtil.mostrarMensajeInformativo("Operacion Exitosa", "Se ha Creado el Contenido [" + contenido.getIdcontenido() + "]");

        return "end2";

    }

    public String irPagar(Campana ca ){
        campana = ca ;
        return "paga";
    }

     public String irCrear(Contenido c) {
         contenido = c;
         campana = new Campana();
         return "subir";
    }

    public void  guardar() {
        //perisitencia
        campana.setContenido(contenido);
        System.err.print(contenido.getIdcontenido());

            campana.setEstado("Esperando Aprobacion");
            campana.setFechaCreacion(Date.from(Instant.now()));
            campana.setEstablecimiento(establecimientoseleccionado);
            campana.setPasadas(pasadas);
            campana.setNombrecampana(contenido.getNombrecont());
            logicaCampana.guardarCampana(campana);
        //cuerpo del mensaje
         String mensajeLocal = new String(constantes.getAlertas());
         String mensajeAnunciante = new String(constantes.getHeaderCorreo());
        mensajeAnunciante = mensajeAnunciante.replaceAll("\\$Id",String.valueOf(campana.getIdcampana()));
        mensajeAnunciante = mensajeAnunciante.replaceAll("\\$establecimiento",establecimientoseleccionado.getNombreEstablecimiento());
        mensajeAnunciante = mensajeAnunciante.replaceAll("\\$numerodePantallas",String.valueOf(establecimientoseleccionado.getNumeroPantallas()));
        mensajeAnunciante = mensajeAnunciante.replaceAll("\\$valormensual",String.valueOf(valor));
        mensajeAnunciante = mensajeAnunciante.replaceAll("\\$total",String.valueOf(valor));

   //correo
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
         String[] replicas = new String[0];
         String[] alertas = new String[0];
        alertas[0] = establecimientoseleccionado.getUsuario().getCorreo();
        replicas[0] = userSession.getUsuario().getCorreo();
        mailSender.send(replicas,"Interac",mensajeAnunciante);
        mailSender.send(replicas,"Interac",mensajeLocal);

            FacesUtil.mostrarMensajeInformativo("operacion exitosa", "se ha creado tu campaña");
    }

       public void eliminarFichero(Contenido conte){
        try {
            String ambiente = propertyReader.get("ambiente");


            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                logicaContenido.eliminarContenido(conte);
                FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha borrado la imagen");
            }else if ("produccion".equals(ambiente)) {
                logicaContenido.eliminarContenido(conte);
                Files.delete(Paths.get("/home/ec2-user/media/colivares/" + conte.getPath()));
                FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha borrado la imagen}");
            }

        }catch (Exception e){
            FacesUtil.mostrarMensajeInformativo("Operación Fallida","Algo Ocurrio");
        }
    }
    @Scheduled(cron="*/5 * * * * ?")
    public void demoServiceMethod()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
    }

    public String ver(int t){
        System.err.println("Totem:" + t);
        logicaCampana.obtenerPorIdConTotems(t);
        return "ver";
    }
    public Long numeroTotem(Integer numTotem){

        contarTotem=logicaTotem.obtenerPorNumero(numTotem);
        return contarTotem;


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
        }else if ((mes.getIdmes()< month) && (year.equals(yearvalue))){
            FacesUtil.mostrarMensajeInformativo("Error fecha", "no puede elegir una fecha ya pasada");
        }else if ((mes.getIdmes()>= month) && (yearvalue!=null)){
            campana.setFechaInicio(getDateStart(mes.getIdmes(),yearvalue));
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
            System.err.println(mes.getIdmes());
            System.err.println(yearvalueend);
            System.err.println(localDate.getDayOfMonth());
            campana.setFechaFin(getDateEnd(mes.getIdmes(), yearvalueend));
        }else if ((mes.getIdmes()<month) && (year.equals(yearvalueend))){
            FacesUtil.mostrarMensajeInformativo("Operación Fallida","No puede programar una fecha anterior a la actual");
        }else if ((mes.getIdmes()>= month) && (yearvalueend!=null)){
            campana.setFechaFin(getDateEnd(mes.getIdmes(),yearvalueend));
        }
            }
    // Función que permite el retorno del ultimo día de un mes X
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




    public List<Totem> totemsEST(Integer idestablecimiento) {
        totemsPorEstablecimiento=logicaTotem.obtenerPorestablecimiento(idestablecimiento);
        return totemsPorEstablecimiento;
    }

    public void calculator(){

         valor= (precio * 2)*(dias.intValue()+1);
    }

    public void dateDiff() {


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

    }
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        System.err.print(marker);
    }

    public String location(Totem t){
        totem = t;
        newCenter= totem.getLat()+","+totem.getLongi();
        advancedModel = new DefaultMapModel();
        advancedModel.addOverlay(new Marker(new LatLng(totem.getLat(),totem.getLongi()),totem.getEstablecimiento().getNombreEstablecimiento()
          , "http://www.google.com/mapfiles/dd-start.png"));
         advancedModel = null;
        System.err.println(totem.getLat()+","+totem.getLongi()+","+newCenter);
       return newCenter;

    }



   //getter and setter
    public Integer filterUbica(Ubicacion u){
        ubicacion=u;
        ubica=u.getIdubicacion();
        System.err.println("ID" + ubica);
        return ubica;
    }
    public String filterTipo(Tipototem tp){
        tipototem=tp;
        tipot=tp.getDestipo();
        System.err.println("ID" + tipot);
        return tipot;
    }
    public void diasPasadas(){

        pasadas=precio*(dias.intValue()+1);
        System.err.println(pasadas);


    }


    //getter and setter


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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
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

    public Integer getPasadas() {
        return pasadas;
    }

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
}










