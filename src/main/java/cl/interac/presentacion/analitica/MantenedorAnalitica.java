package cl.interac.presentacion.analitica;

import cl.interac.entidades.Analitica;
import cl.interac.negocio.LogicaAnalitica;
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

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private StreamedContent chart;

    @PostConstruct
    public void inicio() {
        analiticas = logicaAnalitica.obtenerTodos();
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

        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);

        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);
        pieModel2.set("Brand 4", 421);

        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }



    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "css" + File.separator + "img" + File.separator + "interaclogo.jpg";

        pdf.add(Image.getInstance(logo));
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
}
