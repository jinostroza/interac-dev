package cl.interac.util.components;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author colivares
 */
@Component
@Scope("application")
public class Constantes implements Serializable {
    private String pathArchivos;

    private String correo = "contacto@interac.cl";
    private String claveCorreo = "interac2015";
    private String servidorCorreo = "mx1.nixiweb.com";
    private Integer puertoCorreo = 587;
    private String headerCorreo = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title></title>\n" +
            "     <style type=\"text/css\">\n" +
            "      body{\n" +
            "        background:#dddddd;\n" +
            "      }\n" +
            "\n" +
            "      #header{\n" +
            "        width:70%;\n" +
            "        height:300px ;\n" +
            "        background-color:red; \n" +
            "        }\n" +
            "       #banner{\n" +
            "        background-color:white;\n" +
            "        margin-left:100px; \n" +
            "        font: Arial;\n" +
            "        font-size:18pt;\n" +
            "       } \n" +
            "      #tabla {\n" +
            "        border: solid 1px;\n" +
            "        background-color: #f4f4f4;\n" +
            "        font: Arial;\n" +
            "      }\n" +
            "     \n" +
            "       tr td{\n" +
            "        border: solid 1px;\n" +
            "         font-family:Arial;\n" +
            "\n" +
            "      }\n" +
            "      h1{\n" +
            "        color:white;\n" +
            "        font:150% Arial ;\n" +
            "      }\n" +
            "\n" +
            "     </style>\n" +
            "    \n" +
            " <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
            "\n" +
            "</head>\n"+
            "<body >\n"+
            "    <div id=\"hearder\" style='background-color:red;'>\n" +
            "        <img style='background-color:red;' \n" +
            "         src='http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png'>\n" +
            "         <h1>RESUMEN EJEMPLO</h1>\n" +
            "    </div> \n"+
            "\n" +
            "    <table id=\"tabla\" width=\"60%\"   height=\"500px\">\n" +
            "       <tr >\n" +
            "          <td colspan=\"3px\">\n" +
            "             <h2>Descripcion de su Campaña</h2>\n" +
            "          </td>\n" +
            "       </tr>\n" +
            "       \n" +
            "         <tr>\n" +
            "             <td>Rango de campaña</td>\n" +
            "             <td>Desde:$fechainicio - hasta:$fechafin  Total de dias : </td> \n" +
            "             <td>$dias</td>                        \n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "             <td>Pasadas</td> \n" +
            "             <td>Pasadas Por Dias</td>\n" +
            "             <td>$pasadas</td>      \n" +
            "\n" +
            "        </tr> \n" +
            "        <tr >\n" +
            "            <td >Pantallas</td>                     \n" +
            "             <td>Numero de pantallas</td>                        \n" +
            "            <td >$cantPant</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "        <tr>\n" +
            "            <td>Precio</td>\n" +
            "            <td>precio por pasada</td>\n" +
            "            <td>$$precio</td>\n" +
            "        </tr>  \n" +
            "\n" +
            "        <tr>\n" +
            "            <td>      </td>\n" +
            "            <td>Total</td>  \n" +
            "\n" +
            "            <td>$$total</td>\n" +
            "        </tr>\n" +
            "\n" +
            "       \n" +
            "\n" +
            "</table>\n" +
            "      <a width=\"300px\" height=\"300px\" >\n" +
            "      <img src=\"http://logovector.net/wp-content/uploads/2011/05/270184-webpay-logo.jpg\"/>\n" +
            "      </a> \n" +
            "   \n" +
            "</body>\n" +
            "</html>";




    private String alertas="<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title></title>\n" +
            "     <style type=\"text/css\">\n" +
            "      body{\n" +
            "        background:#dddddd;\n" +
            "      }\n" +
            "\n" +
            "      #header{\n" +
            "        width:70%;\n" +
            "        height:300px ;\n" +
            "        background-color:red; \n" +
            "        }\n" +
            "       #banner{\n" +
            "        background-color:white;\n" +
            "        margin-left:100px; \n" +
            "        font: Arial;\n" +
            "        font-size:18pt;\n" +
            "       } \n" +
            "      #tabla {\n" +
            "        border: solid 1px;\n" +
            "        background-color: #f4f4f4;\n" +
            "        font: Arial;\n" +
            "      }\n" +
            "     \n" +
            "       tr td{\n" +
            "        border: solid 1px;\n" +
            "         font-family:Arial;\n" +
            "\n" +
            "      }\n" +
            "      h1{\n" +
            "        color:white;\n" +
            "        font:150% Arial ;\n" +
            "      }\n" +
            "\n" +
            "     </style>\n" +
            "    \n" +
            " <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
            "\n" +
            "</head>\n" +
            "<body >\n" +
            "    <div id=\"hearder\" style='background-color:red;'>\n" +
            "        <img style='background-color:red;' \n" +
            "         src='http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png'>\n" +
            "         <h1>RESUMEN EJEMPLO</h1>\n" +
            "    </div>" +
            "<table width=\"500px\" height=\"250px\" background-color=\"#e7e7e7\" >\n" +
            "    <tr>\n" +
            "        <th colspan=\"2\">Detalles<th>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>Fecha</td>\n" +
            "        <td>$fecha</td>        \n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>Pantallas</td>\n" +
            "        <td>$pantallas</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>Pasadas</td>\n" +
            "        <td>$pasada</td>\n" +
            "    </tr>\n" +
            "\n" +
            "</table> " +
            "</body>" ;





    public Constantes() {
        ProxyFactory pf = new ProxyFactory(this);
        pf.addAdvice(new MethodInterceptor() {

            public Object invoke(MethodInvocation mi) throws Throwable {
                if (mi.getMethod().getName().startsWith("set"))
                    throw new UnsupportedOperationException("Constantes: Estás intentando modificar un valor constante");
                return null;
            }
        });
        pathArchivos = System.getProperty("catalina.home") + "/static/interac/tmp";
    }

    public String getPathArchivos() {
        return pathArchivos;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClaveCorreo() {
        return claveCorreo;
    }

    public void setClaveCorreo(String claveCorreo) {
        this.claveCorreo = claveCorreo;
    }

    public String getServidorCorreo() {
        return servidorCorreo;
    }

    public void setServidorCorreo(String servidorCorreo) {
        this.servidorCorreo = servidorCorreo;
    }

    public Integer getPuertoCorreo() {
        return puertoCorreo;
    }

    public void setPuertoCorreo(Integer puertoCorreo) {
        this.puertoCorreo = puertoCorreo;
    }

    public String getHeaderCorreo() {
        return headerCorreo;
    }

    public void setHeaderCorreo(String headerCorreo) {
        this.headerCorreo = headerCorreo;
    }
    public String getAlertas() {
        return alertas;
    }

    public void setAlertas(String alertas) {
        this.alertas = alertas;
    }
}