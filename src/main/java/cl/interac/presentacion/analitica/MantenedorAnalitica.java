package cl.interac.presentacion.analitica;

import cl.interac.entidades.Analitica;
import cl.interac.entidades.Contenido;
import cl.interac.entidades.Establecimiento;
import cl.interac.entidades.Totem;
import cl.interac.negocio.LogicaAnalitica;
import cl.interac.negocio.LogicaContenido;
import cl.interac.negocio.LogicaEstablecimiento;
import cl.interac.negocio.LogicaTotem;
import cl.interac.util.components.UserSession;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.lowagie.text.*;
import org.primefaces.model.chart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Joaco on 11-05-2016.
 */

@Component
@Scope("flow")
public class MantenedorAnalitica implements Serializable
{

    @Autowired
    private LogicaAnalitica logicaAnalitica;
    @Autowired
    private LogicaEstablecimiento logicaEstablecimiento;
    @Autowired
    private LogicaTotem logicaTotem;
    @Autowired
    private LogicaContenido logicaContenido;
    @Autowired
    private UserSession userSession;

    private Analitica analitica;
    private List<Analitica> analiticas ;
    private List<Analitica> analiticastotems ;
    private List<Establecimiento> establecimientoList;
    private List<Contenido> contenidoList;
    private List<Totem> totemList;
    private Establecimiento establecimiento;
    private Totem totem ;
    private Contenido contenido ;
    private Long hombres;
    private Long mujeres;
    private Integer IdEstablecimiento;
    private Integer idTotem;
    private String path_imagen;
    private Date fecha_filtro;
    private Date fecini;
    private Date fecend;
    private String  fecha;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private BarChartModel barModel;

    @PostConstruct
    public void inicio() {
         establecimientoList = logicaEstablecimiento.obtenerPorUsuario(userSession.getUsuario().getUsername());
        //totemList = logicaTotem.obtenerPorUsuario(userSession.getUsuario().getUsername()); todos los totems del usuario
        contenidoList = logicaContenido.obtenContenido(userSession.getUsuario().getUsername());
        analiticas = logicaAnalitica.obtenerTodos();
        contenido = new Contenido();
        totem = new Totem();
        analitica = new Analitica();
        createPieModels();
    }

    //filtra lista de totems segun establecimiento
    public void totems(){

        totemList = logicaTotem.obtenerPorestablecimiento(establecimiento.getIdEstablecimiento());

    }
    public String  contar_filtro(){


        if (totem !=null && contenido!=null){


            analiticas=logicaAnalitica.contotem(totem.getIdtotem(), contenido.getPath(),fecini,fecend);

        }
        else if(totem==null && contenido!=null){

            analiticas=logicaAnalitica.contenidoA(contenido.getPath(),fecini,fecend);

        }
        else if (totem!=null && contenido==null){
            analiticas=logicaAnalitica.totemA(totem.getIdtotem(),fecini,fecend);
        }
        else{
            analiticas.clear();

            for (Totem t:totemList) {
                analiticastotems = logicaAnalitica.totemA(t.getIdtotem(),fecini,fecend);
                analiticas.addAll(analiticastotems);
            }
        }

        int hom = 0;
        int muj = 0;
        int fel = 0;
        int tri = 0;
        int eno = 0;
        int neu = 0;
        int sor = 0;
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;
        int s4 = 0;
        int s5 = 0;
        int s6 = 0;
        int s7 = 0;
           for (Analitica a:analiticas){
                if (a.getGenero().equals("Hombre ")){
                    hom=hom+ 1;
                }else {
                    muj=muj+1;
                }
               if (a.getExpresion().equals("Feliz ")){
                   fel=fel+1;
               }else if (a.getExpresion().equals("Triste ") ){
                   tri=tri+1;
               }else if (a.getExpresion().equals("Enojado ")){
                   eno=eno+1;
               }else if (a.getExpresion().equals("Neutral ")){
                   neu=neu+1;
               }else {
                   sor=sor+1;
               }

               if (a.getEdad()<15){
                   s1=s1+1;
               }else if (a.getEdad()>=15 && a.getEdad()<25){
                   s2=s2+1;
               }else if (a.getEdad()>=25 && a.getEdad()<35){
                   s3=s3+1;
               }else if (a.getEdad()>=35 && a.getEdad()<45){
                   s4=s4+1;
               }else if (a.getEdad()>=45 && a.getEdad()<55){
                   s5=s5+1;
               }else if (a.getEdad()>=55 && a.getEdad()<65){
                   s6=s6+1;
               }else {
                   s7=s7+1;
               }

            }
        createPieModel1(hom,muj);
        createPieModel2(fel,tri,eno,sor,neu);
        createBarModel(s1,s2,s3,s4,s5,s6,s7);
        return "sub";
    }



    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private void createPieModels() {
        createPieModel1(0,0);
        createPieModel2(0, 0, 0, 0, 0);
        createBarModel(0,0,0,0,0,0,0);

    }

    private void createPieModel1(int hom , int muj) {
        pieModel1 = new PieChartModel();
        System.out.println(hombres);
        System.out.println(mujeres);

        pieModel1.set("Hombres : "+hom, hom);
        pieModel1.set("Mujeres : "+ muj, muj);

        pieModel1.setTitle("Genero");
        pieModel1.setLegendPosition("w");
        pieModel1.setShowDataLabels(true);
    }

    private void createPieModel2(int fel,int tri,int eno,int sor,int neu) {
        pieModel2 = new PieChartModel();

        pieModel2.set("Triste : "+tri, tri);
        pieModel2.set("Feliz : " + fel, fel);
        pieModel2.set("Enojado : " + eno, eno);
        pieModel2.set("Sorpresa : " + sor, sor);
        pieModel2.set("Neutral : " + neu, neu);


        pieModel2.setTitle("Expresion");
        pieModel2.setLegendPosition("w");
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
    private BarChartModel initBarModel(int s1,int s2,int s3,int s4,int s5,int s6,int s7) {
        BarChartModel model = new BarChartModel();

        ChartSeries seg01 = new ChartSeries();
        seg01.setLabel("Infantes 0 a 14 :"+s1);
        seg01.set("0-14", s1);

        ChartSeries seg02 = new ChartSeries();
        seg02.setLabel("Jovenes 15 a 24 :"+s2);
        seg02.set("15-25", s2);

        ChartSeries seg03 = new ChartSeries();
        seg03.setLabel("Joven-Adulto 25 a 34 :"+s3);
        seg03.set("25-35", s3);

        ChartSeries seg04 = new ChartSeries();
        seg04.setLabel("Adultos 35 a 44 :"+s4);
        seg04.set("35-44", s4);

        ChartSeries seg05 = new ChartSeries();
        seg05.setLabel("Adultos 45 a 54 :"+s5);
        seg05.set("45-54", s5);

        ChartSeries seg06 = new ChartSeries();
        seg06.setLabel("Adultos 55 a 64 :"+s6 );
        seg06.set("55-64", s6);

        ChartSeries seg07 = new ChartSeries();
        seg07.setLabel("Adultos 65+ :"+s7);
        seg07.set("65+", s7);

        model.addSeries(seg01);
        model.addSeries(seg02);
        model.addSeries(seg03);
        model.addSeries(seg04);
        model.addSeries(seg05);
        model.addSeries(seg06);
        model.addSeries(seg07);


        return model;
    }
    private void createBarModel(int s1,int s2,int s3,int s4,int s5,int s6,int s7) {
        barModel = initBarModel(s1, s2, s3, s4, s5, s6, s7);

        barModel.setTitle("Rango Etario");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Segmentos");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(50);
    }

    public Long fechaString (Date date){
        Long hora;
        hora = date.getTime();
        return hora;
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        Font f=new Font(Font.FontFamily.TIMES_ROMAN,50.0f,Font.UNDERLINE, BaseColor.RED);
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        Paragraph paragraph2 = new Paragraph("Reporte Analitica");

        paragraph2.setSpacingAfter(25);
        paragraph2.setSpacingBefore(25);
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        paragraph2.setIndentationLeft(50);
        paragraph2.setIndentationRight(50);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "css" + File.separator + "img" + File.separator + "interaclogo.jpg";
        pdf.addTitle("Reporte Modulo Analitica");
        pdf.add(Image.getInstance(logo));
        pdf.add(paragraph2);


    }



  //getter & setter


    public Integer getIdEstablecimiento() {
        return IdEstablecimiento;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        IdEstablecimiento = idEstablecimiento;
    }

    public String getPath_imagen() {
        return path_imagen;
    }

    public void setPath_imagen(String path_imagen) {
        this.path_imagen = path_imagen;
    }

    public Date getFecha_filtro() {
        return fecha_filtro;
    }

    public void setFecha_filtro(Date fecha_filtro) {
        this.fecha_filtro = fecha_filtro;
    }

    public Integer getIdtotem() {
        return idTotem;
    }

    public void setIdtotem(Integer idtotem) {
        this.idTotem = idtotem;
    }

    public Analitica getAnalitica() {
        return analitica;
    }

    public void setAnalitica(Analitica analitica) {
        this.analitica = analitica;
    }

    public List<Analitica> getAnaliticas() {
        return analiticas;
    }

    public void setAnaliticas(List<Analitica> analiticas) {
        this.analiticas = analiticas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    public Long getMujeres() {

        return mujeres;
    }

    public void setMujeres(Long mujeres) {
        this.mujeres = mujeres;
    }

    public Long getHombres() {

        return hombres;
    }

    public void setHombres(Long hombres) {
        this.hombres = hombres;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Totem getTotem() {
        return totem;
    }

    public void setTotem(Totem totem) {
        this.totem = totem;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    public List<Totem> getTotemList() {
        return totemList;
    }

    public void setTotemList(List<Totem> totemList) {
        this.totemList = totemList;
    }

    public Date getFecini() {
        return fecini;
    }

    public void setFecini(Date fecini) {
        this.fecini = fecini;
    }

    public Date getFecend() {
        return fecend;
    }

    public void setFecend(Date fecend) {
        this.fecend = fecend;
    }

    public List<Analitica> getAnaliticastotems() {
        return analiticastotems;
    }

    public void setAnaliticastotems(List<Analitica> analiticastotems) {
        this.analiticastotems = analiticastotems;
    }
}
