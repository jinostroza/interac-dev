package cl.interac.presentacion.analitica;

import cl.interac.entidades.Analitica;
import cl.interac.negocio.LogicaAnalitica;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.lowagie.text.*;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
@ManagedBean
@Component
@Scope("flow")
public class MantenedorAnalitica implements Serializable
{

    @Autowired
    private LogicaAnalitica logicaAnalitica;

    private Analitica analitica;
    private List<Analitica> analiticas;
    private Long hombres;
    private Long mujeres;
    private Long seg1;
    private Long seg2;
    private Long seg3;
    private Long seg4;
    private Long seg5;
    private Long seg6;
    private Long seg7;
    private Long audiencia;
    private String fecha;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private StreamedContent chart;

    @PostConstruct
    public void inicio() {
        analiticas = logicaAnalitica.obtenerTodosGenero();
        hombres = logicaAnalitica.countHombres();
        mujeres = logicaAnalitica.countMujeres();

        seg1 = logicaAnalitica.seg1();
        seg2 = logicaAnalitica.seg2();
        seg3 = logicaAnalitica.seg3();
        seg4 = logicaAnalitica.seg4();
        seg5 = logicaAnalitica.seg5();
        seg6 = logicaAnalitica.seg6();
        seg7 = logicaAnalitica.seg7();
        analitica = new Analitica();
        createPieModels();

    }


    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        System.out.println(hombres);
        System.out.println(mujeres);

        pieModel1.set("Hombres : "+hombres, hombres.intValue());
        pieModel1.set("Mujeres : " + mujeres, mujeres.intValue());

        pieModel1.setTitle("Genero");
        pieModel1.setLegendPosition("w");
        pieModel1.setShowDataLabels(true);
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Niños 0 a 15", seg1);
        pieModel2.set("Jovenes 15 a 25", seg2);
        pieModel2.set("Joven-Adulto 25 a 35", seg3);
        pieModel2.set("Adultos 35 a 45", seg4);
        pieModel2.set("Adultos 45 a 55", seg5);
        pieModel2.set("Adultos 55 a 65", seg6);
        pieModel2.set("Adulto Mayor 65+", seg7);

        pieModel2.setTitle("Rango Etario");
        pieModel2.setLegendPosition("w");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
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

    public Long getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(Long audiencia) {
        this.audiencia = audiencia;
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
}
