package cl.interac.presentacion.analitica;

import cl.interac.entidades.Analitica;
import cl.interac.negocio.LogicaAnalitica;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joaco on 22/08/2016.
 */
@Controller
@Scope("session")
@RequestMapping("/export")
public class PdfExport {
    @Autowired
    private LogicaAnalitica logicaAnalitica;

    private Analitica analitica;
    private List<Analitica> analiticaList;
       private static final Logger log =
                LoggerFactory.getLogger(PdfExport.class);

        private static final String PARAMETER_ITEM_NAME = "itemName";
        private static final String PARAMETER_TYPE = "type";
        private static final String VALUE_TYPE_PDF = "pdf";
        private static final String VALUE_TYPE_XLS = "xls";

        private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE =
                new HashMap<String, String>();
        static {
            FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
            FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
        }



        @RequestMapping(value = "", method = RequestMethod.GET)
        public void export(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String fileType = request.getParameter(PARAMETER_TYPE);
            log.info("Exporting {} report", fileType);

            response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
            OutputStream out = response.getOutputStream();
            try {
                JasperReportBuilder jrb = createJasperReport();

                if (VALUE_TYPE_PDF.equals(fileType)) {
                    jrb.toPdf(out);
                } else if (VALUE_TYPE_XLS.equals(fileType)) {
                    jrb.toExcelApiXls(out);
                }
            } catch (DRException e) {
                throw new ServletException(e);
            }
            out.close();
        }

        private JasperReportBuilder createJasperReport() {
            // Here use DynamicReports API to build a report
            // and fill it with a JRDataSource.
            // I used SearchController session bean
            // to get required search results data.
            JasperReportBuilder report = DynamicReports.report();
            report
                    .columns(
                            Columns.column("Customer Id", "id", DataTypes.integerType()).setHorizontalAlignment(HorizontalAlignment.LEFT),
                            Columns.column("First Name", "first_name", DataTypes.stringType()),
                            Columns.column("Last Name", "last_name", DataTypes.stringType()),
                            Columns.column("Date", "date", DataTypes.dateType()).setHorizontalAlignment(HorizontalAlignment.LEFT))
                            //title of the report
                    .title(
                            Components.text("SimpleReportExample")
                                    .setHorizontalAlignment(HorizontalAlignment.CENTER))
                    .pageFooter(Components.pageXofY())//show page number on the page footer
                    .setDataSource(createDataSource());

            try {
                //show the report
                report.show();

                //export the report to a pdf file
                String userHome = System.getProperty("user.home");
                report.toPdf(new FileOutputStream(userHome+"/report.pdf"));
            } catch (DRException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            return report ;
        }

    private JRDataSource createDataSource() {

        DRDataSource dataSource = new DRDataSource("fecha", "modulo", "image" ,"edad","genero","expresion","fecha_ini","fecha_fin");

        for (Analitica an : analiticaList){
            dataSource.add(an.getCamara_date(), an.getModulo(), an.getImagen(),an.getEdad(),an.getGenero(),an.getSlider_inicio(),an.getSlider_fin());
        }

        return dataSource;

    }
    }

