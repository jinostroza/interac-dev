package cl.interac.presentacion.totems;

import cl.interac.entidades.*;
import cl.interac.negocio.*;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import cl.interac.util.components.UserSession;
import cl.interac.util.services.FileUploader;
import com.lowagie.text.*;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Joaco on 24-04-2015.
 */
@ManagedBean

@Scope("flow")
public class MantenedorTotems implements Serializable {

    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaMeses logicaMeses;
    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaTipototem logicaTipototem;
    @Autowired
    private LogicaTotem logicaTotemConUsuario;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private UserSession userSession;
    @Autowired
    private LogicaUbicacion logicaUbicacion;
    @Autowired
    private LogicaMarcapantalla logicaMarcaPantalla;
    @Autowired
    private FileUploader fileUploader;
    private int fileUploadCount;
    private LineChartModel lineModel1;
    private MapModel simpleModel;
    private Marker marker;

    private List<Totem> totems;
    private List<Tipototem> tipototems;
    private List<Totem> totemConFiltro;
    private List<Ubicacion> ubicaciones;
    private List<Establecimiento> establecimientoList;
    private List<Establecimiento> establecimientoConfiltro;
    private Totem totem;
    private Marcapantalla marcapantalla;
    private Establecimiento establecimiento;
    private Tipototem tipototem;
    private List<Totem> totemPorUsuario;
    private Ubicacion ubicacion;
    private List<Marcapantalla> marcaPantallas;
    private Date date;
    private Meses meses;
    private List<Meses> mesesList;
    private String color="454545";

    @PostConstruct
    public void inicio() {

        totems = logicaTotem.obtenerConRelacion();
        establecimientoList = logicaEstablecimiento.obtenerTodos();
        tipototems = logicaTipototem.obtenerTodos();
        totemPorUsuario = logicaTotemConUsuario.obtenerPorUsuario(userSession.getUsuario().getUsername());
        ubicaciones = logicaUbicacion.obtenerTodas();
        marcaPantallas = logicaMarcaPantalla.obtenerTodos();
        mesesList = logicaMeses.obtenerTodos();
        totem = new Totem();

    }



    // Logica Vista
    public void agregarTotem(FileUploadEvent fue){
        try {
            String pathTemporal = fileUploader.subir(fue, "/jiu");
            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                totem.setImagen(pathTemporal);
            } else if ("produccion".equals(ambiente)) {
                String carpetaPrincipal = "jiu";
                String nombreArchivo = pathTemporal.substring(pathTemporal.lastIndexOf('.'));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");
                nombreArchivo = sdf.format(new Date()) + nombreArchivo;
                totem.setImagen(nombreArchivo);
                Files.copy(Paths.get(pathTemporal), Paths.get("/home/ec2-user/media/" + carpetaPrincipal + "/" + nombreArchivo));
            }

            totem.setImagen(pathTemporal);
            totem.setEstablecimiento(establecimiento);
            totem.setMarcaPantalla((marcapantalla));
            totem.setLat(establecimiento.getLat());
            totem.setLongi(establecimiento.getLongi());
            totem.setTipototem(tipototem);
            System.err.println("totem e: " + totem.getEstablecimiento());
            logicaTotem.guardar(totem);
            FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el Totem [" + totem.getNoserie() + "]");

            fileUploadCount = fileUploadCount + 1;

        } catch (IOException e) {
            return;
        }
    }


    /* public void agregarTotem() {

        totem.setEstablecimiento(establecimiento);
        totem.setMarcaPantalla((marcapantalla));
        totem.setLat(establecimiento.getLat());
        totem.setLongi(establecimiento.getLongi());
        totem.setTipototem(tipototem);
        System.err.println("totem e: " + totem.getEstablecimiento());

        logicaTotem.guardar(totem);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha creado el Totem [" + totem.getNoserie() + "]");
    }

    public void subirImagen(FileUploadEvent fue){

        try {
            String pathTemporal = fileUploader.subir(fue, "/jiu");
            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                totem.setImagen(pathTemporal);
            } else if ("produccion".equals(ambiente)) {
                String carpetaPrincipal = "jiu";
                String nombreArchivo = pathTemporal.substring(pathTemporal.lastIndexOf('.'));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");
                nombreArchivo = sdf.format(new Date()) + nombreArchivo;
                totem.setImagen(nombreArchivo);
                Files.copy(Paths.get(pathTemporal), Paths.get("/home/ec2-user/media/" + carpetaPrincipal + "/" + nombreArchivo));
            }

            totem.setImagen(pathTemporal);
            fileUploadCount = fileUploadCount + 1;

        } catch (IOException e) {
            return;
        }
    } */



    public void editarTotem(Totem t){
        totem = t;
        totem.setEstablecimiento(totem.getEstablecimiento());
        totem.setMarcaPantalla(totem.getMarcaPantalla());
        totem.setTipototem(totem.getTipototem());
        logicaTotem.guardar(totem);
        totems = logicaTotem.obtenerConRelacion();
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha Lobeznisado el Totem [" + totem.getNoserie() + "]");
    }

    public void eliminarTotem(Totem totem) {
        logicaTotem.eliminarTotem(totem);
        FacesUtil.mostrarMensajeInformativo("Operación Exitosa", "Se ha sobornado el Totem [" + totem.getNoserie() + "]");
    }
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "css" + File.separator + "img" + File.separator + "interaclogo.jpg";

        pdf.add(Image.getInstance(logo));
    }
    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    public LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    //Getters y Setters

    public List<Totem> getTotemConFiltro() {
        return totemConFiltro;
    }
    public void setTotemConFiltro(List<Totem> totemConFiltro) {
        this.totemConFiltro = totemConFiltro;
    }

    public List<Totem> getTotems() { return totems; }
    public void setTotems(List<Totem> totems) {
        this.totems = totems;
    }

    public Totem getTotem() {
        return totem;
    }
    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public List<Establecimiento> getEstablecimientoList() { return establecimientoList; }
    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;

    }

    public List<Establecimiento> getEstablecimientoConfiltro() {
        return establecimientoConfiltro;
    }
    public void setEstablecimientoConfiltro(List<Establecimiento> establecimientoConfiltro) {
        this.establecimientoConfiltro = establecimientoConfiltro;
    }

    public Marker getMarker() {
        return marker;
    }
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public List<Tipototem> getTipototems() {
        return tipototems;
    }
    public void setTipototems(List<Tipototem> tipototems) {
        this.tipototems = tipototems;
    }

    public Tipototem getTipototem() {
        return tipototem;
    }
    public void setTipototem(Tipototem tipototem) {
        this.tipototem = tipototem;
    }

    public List<Totem> getTotemPorUsuario() {
        return totemPorUsuario;
    }
    public void setTotemPorUsuario(List<Totem> totemPorUsuario) {
        this.totemPorUsuario = totemPorUsuario;
    }

    public UserSession getUserSession() {
        return userSession;
    }
    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }
    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public List<Marcapantalla> getMarcaPantallas() { return marcaPantallas; }
    public void setMarcaPantallas(List<Marcapantalla> marcaPantallas) {
        this.marcaPantallas = marcaPantallas;
    }

    public Marcapantalla getMarcapantalla() {
        return marcapantalla;
    }
    public void setMarcapantalla(Marcapantalla marcapantalla) {
        this.marcapantalla = marcapantalla;
    }

    public Meses getMeses() {
        return meses;
    }

    public void setMeses(Meses meses) {
        this.meses = meses;
    }

    public List<Meses> getMesesList() {
        return mesesList;
    }

    public void setMesesList(List<Meses> mesesList) {
        this.mesesList = mesesList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }
}