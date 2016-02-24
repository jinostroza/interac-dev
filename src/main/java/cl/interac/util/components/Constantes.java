package cl.interac.util.components;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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


    private String headerCorreo = "<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta name=\"viewport\" content=\"width=device-width\">\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "<title>Really Simple HTML Email Template</title>\n" +
            "<style>\n" +
            "/* -------------------------------------\n" +
            "    GLOBAL\n" +
            "------------------------------------- */\n" +
            "* {\n" +
            "  font-family: \"Helvetica Neue\", \"Helvetica\", Helvetica, Arial, sans-serif;\n" +
            "  font-size: 100%;\n" +
            "  line-height: 1.6em;\n" +
            "  margin: 0;\n" +
            "  padding: 0;\n" +
            "}\n" +
            "img {\n" +
            "  max-width: 600px;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "body {\n" +
            "  -webkit-font-smoothing: antialiased;\n" +
            "  height: 100%;\n" +
            "  -webkit-text-size-adjust: none;\n" +
            "  width: 100% !important;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    ELEMENTS\n" +
            "------------------------------------- */\n" +
            "a {\n" +
            "  color: #348eda;\n" +
            "}\n" +
            ".btn-primary {\n" +
            "  Margin-bottom: 10px;\n" +
            "  width: auto !important;\n" +
            "}\n" +
            ".btn-primary td {\n" +
            "  background-color: #E52721; \n" +
            "  border-radius: 25px;\n" +
            "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif; \n" +
            "  font-size: 14px; \n" +
            "  text-align: center;\n" +
            "  vertical-align: top; \n" +
            "}\n" +
            ".btn-primary td a {\n" +
            "  background-color: #348eda;\n" +
            "  border: solid 1px #348eda;\n" +
            "  border-radius: 25px;\n" +
            "  border-width: 10px 20px;\n" +
            "  display: inline-block;\n" +
            "  color: #ffffff;\n" +
            "  cursor: pointer;\n" +
            "  font-weight: bold;\n" +
            "  line-height: 2;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            ".last {\n" +
            "  margin-bottom: 0;\n" +
            "}\n" +
            ".first {\n" +
            "  margin-top: 0;\n" +
            "}\n" +
            ".padding {\n" +
            "  padding: 10px 0;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    BODY\n" +
            "------------------------------------- */\n" +
            "table.body-wrap {\n" +
            "  padding: 20px;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "table.body-wrap .container {\n" +
            "  border: 1px solid #f0f0f0;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    FOOTER\n" +
            "------------------------------------- */\n" +
            "table.footer-wrap {\n" +
            "  clear: both !important;\n" +
            "  width: 100%;  \n" +
            "}\n" +
            ".footer-wrap .container p {\n" +
            "  color: #666666;\n" +
            "  font-size: 12px;\n" +
            "  \n" +
            "}\n" +
            "table.footer-wrap a {\n" +
            "  color: #999999;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    TYPOGRAPHY\n" +
            "------------------------------------- */\n" +
            "h1, \n" +
            "h2, \n" +
            "h3 {\n" +
            "  color: #111111;\n" +
            "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif;\n" +
            "  font-weight: 200;\n" +
            "  line-height: 1.2em;\n" +
            "  margin: 40px 0 10px;\n" +
            "}\n" +
            "h1 {\n" +
            "  font-size: 36px;\n" +
            "}\n" +
            "h2 {\n" +
            "  font-size: 28px;\n" +
            "}\n" +
            "h3 {\n" +
            "  font-size: 22px;\n" +
            "}\n" +
            "p, \n" +
            "ul, \n" +
            "ol {\n" +
            "  font-size: 14px;\n" +
            "  font-weight: normal;\n" +
            "  margin-bottom: 10px;\n" +
            "}\n" +
            "ul li, \n" +
            "ol li {\n" +
            "  margin-left: 5px;\n" +
            "  list-style-position: inside;\n" +
            "}\n" +
            "/* ---------------------------------------------------\n" +
            "    RESPONSIVENESS\n" +
            "------------------------------------------------------ */\n" +
            "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
            ".container {\n" +
            "  clear: both !important;\n" +
            "  display: block !important;\n" +
            "  Margin: 0 auto !important;\n" +
            "  max-width: 600px !important;\n" +
            "}\n" +
            "/* Set the padding on the td rather than the div for Outlook compatibility */\n" +
            ".body-wrap .container {\n" +
            "  padding: 20px;\n" +
            "}\n" +
            "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
            ".content {\n" +
            "  display: block;\n" +
            "  margin: 0 auto;\n" +
            "  max-width: 600px;\n" +
            "}\n" +
            "/* Let's make sure tables in the content area are 100% wide */\n" +
            ".content table {\n" +
            "  width: 100%;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "\n" +
            "<body bgcolor=\"#f6f6f6\">\n" +
            "\n" +
            "<!-- body -->\n" +
            "<table class=\"body-wrap\" bgcolor=\"#f6f6f6\">\n" +
            "  <tr>\n" +
            "    <td></td>\n" +
            "    <td class=\"container\" bgcolor=\"#FFFFFF\">\n" +
            "\n" +
            "      <!-- content -->\n" +
            "      <div class=\"content\">\n" +
            "      <table>\n" +
            "        <tr>\n" +
            "          <td>\n" +
            "\n" +
            "          \n" +
            "         \n" +
            "            <h1>Se ha subido Tu campa&ntilde;a</h1>\n" +
            "            <p>Una vez subida tu campa&ntilde;a debes esperar la aprobaci&oacute;n del Esablecimiento</p>\n" +
            "           \n" +
            "            <!-- button -->\n" +
            "            <table class=\"btn-primary\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "              <tr>\n" +
            "                <td>\n" +
            "                  <img src=\"http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png\"/>\n" +
            "                </td>\n" +
            "              </tr>\n" +
            "            </table>\n" +
            "            <!-- /button -->\n" +
            "            <table class=\"body-wrap\">\n" +
            "               <tr>\n"  +
            "                   <td>\n " +
            "                    <h3>ID</h3>\n" +
            "                 </td>\n" +
            "                 <td>\n" +
            "                    <h3>Descripci&oacute;n Campa&ntilde;a</h3>\n" +
            "                 </td>\n" +
            "                  <td>\n" +
            "                      <h3>Valor Total</h3>\n" +
            "                  </td>  \n" +
            "                  <td>\n" +
            "                     <p></p>\n" +
            "                   </td>\n" +
            "               </tr>\n" +
            "               <tr>\n" +
            "                  <td>" +
            "                    <p>$id</p>" +
            "                 </td>\n "+
            "                <td>\n" +
            "                  <p>$nombrecampana</p>\n" +
            "                </td>\n" +
            "                <td>\n" +
            "                  <p>$valortotal</p>\n" +
            "                </td>\n" +
            "               </tr>\n" +
            "               \n" +
            "            \n" +
            "            </table>\n" +
            "          \n" +
            "          </td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "      </div>\n" +
            "      <!-- /content -->\n" +
            "      \n" +
            "    </td>\n" +
            "    <td></td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "<!-- /body -->\n" +
            "\n" +
            "<!-- footer -->\n" +
            "<table class=\"footer-wrap\">\n" +
            "  <tr>\n" +
            "    <td></td>\n" +
            "    <td class=\"container\">\n" +
            "      \n" +
            "      <!-- content -->\n" +
            "      <div class=\"content\">\n" +
            "        <table>\n" +
            "          <tr>\n" +
            "            <td align=\"center\">\n" +
            "              \n" +
            "      <p>Telefono (56-32) 227 41 51</p>\n" +
            "      <p>contacto@interac.cl Brasil con Freire 2147, Facultad de Ingeniería PUCV, Valparaíso - Chile</p>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "      <!-- /content -->\n" +
            "      \n" +
            "    </td>\n" +
            "    <td></td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "<!-- /footer -->\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    private String alertas="<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta name=\"viewport\" content=\"width=device-width\">\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "<title>Really Simple HTML Email Template</title>\n" +
            "<style>\n" +
            "/* -------------------------------------\n" +
            "    GLOBAL\n" +
            "------------------------------------- */\n" +
            "* {\n" +
            "  font-family: \"Helvetica Neue\", \"Helvetica\", Helvetica, Arial, sans-serif;\n" +
            "  font-size: 100%;\n" +
            "  line-height: 1.6em;\n" +
            "  margin: 0;\n" +
            "  padding: 0;\n" +
            "}\n" +
            "img {\n" +
            "  max-width: 600px;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "body {\n" +
            "  -webkit-font-smoothing: antialiased;\n" +
            "  height: 100%;\n" +
            "  -webkit-text-size-adjust: none;\n" +
            "  width: 100% !important;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    ELEMENTS\n" +
            "------------------------------------- */\n" +
            "a {\n" +
            "  color: #348eda;\n" +
            "}\n" +
            ".btn-primary {\n" +
            "  Margin-bottom: 10px;\n" +
            "  width: auto !important;\n" +
            "}\n" +
            ".btn-primary td {\n" +
            "  background-color: #E52721; \n" +
            "  border-radius: 25px;\n" +
            "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif; \n" +
            "  font-size: 14px; \n" +
            "  text-align: center;\n" +
            "  vertical-align: top; \n" +
            "}\n" +
            ".btn-primary td a {\n" +
            "  background-color: #348eda;\n" +
            "  border: solid 1px #348eda;\n" +
            "  border-radius: 25px;\n" +
            "  border-width: 10px 20px;\n" +
            "  display: inline-block;\n" +
            "  color: #ffffff;\n" +
            "  cursor: pointer;\n" +
            "  font-weight: bold;\n" +
            "  line-height: 2;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            ".last {\n" +
            "  margin-bottom: 0;\n" +
            "}\n" +
            ".first {\n" +
            "  margin-top: 0;\n" +
            "}\n" +
            ".padding {\n" +
            "  padding: 10px 0;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    BODY\n" +
            "------------------------------------- */\n" +
            "table.body-wrap {\n" +
            "  padding: 20px;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "table.body-wrap .container {\n" +
            "  border: 1px solid #f0f0f0;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    FOOTER\n" +
            "------------------------------------- */\n" +
            "table.footer-wrap {\n" +
            "  clear: both !important;\n" +
            "  width: 100%;  \n" +
            "}\n" +
            ".footer-wrap .container p {\n" +
            "  color: #666666;\n" +
            "  font-size: 12px;\n" +
            "  \n" +
            "}\n" +
            "table.footer-wrap a {\n" +
            "  color: #999999;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    TYPOGRAPHY\n" +
            "------------------------------------- */\n" +
            "h1, \n" +
            "h2, \n" +
            "h3 {\n" +
            "  color: #111111;\n" +
            "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif;\n" +
            "  font-weight: 200;\n" +
            "  line-height: 1.2em;\n" +
            "  margin: 40px 0 10px;\n" +
            "}\n" +
            "h1 {\n" +
            "  font-size: 36px;\n" +
            "}\n" +
            "h2 {\n" +
            "  font-size: 28px;\n" +
            "}\n" +
            "h3 {\n" +
            "  font-size: 22px;\n" +
            "}\n" +
            "p, \n" +
            "ul, \n" +
            "ol {\n" +
            "  font-size: 14px;\n" +
            "  font-weight: normal;\n" +
            "  margin-bottom: 10px;\n" +
            "}\n" +
            "ul li, \n" +
            "ol li {\n" +
            "  margin-left: 5px;\n" +
            "  list-style-position: inside;\n" +
            "}\n" +
            "/* ---------------------------------------------------\n" +
            "    RESPONSIVENESS\n" +
            "------------------------------------------------------ */\n" +
            "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
            ".container {\n" +
            "  clear: both !important;\n" +
            "  display: block !important;\n" +
            "  Margin: 0 auto !important;\n" +
            "  max-width: 600px !important;\n" +
            "}\n" +
            "/* Set the padding on the td rather than the div for Outlook compatibility */\n" +
            ".body-wrap .container {\n" +
            "  padding: 20px;\n" +
            "}\n" +
            "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
            ".content {\n" +
            "  display: block;\n" +
            "  margin: 0 auto;\n" +
            "  max-width: 600px;\n" +
            "}\n" +
            "/* Let's make sure tables in the content area are 100% wide */\n" +
            ".content table {\n" +
            "  width: 100%;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "\n" +
            "<body bgcolor=\"#f6f6f6\">\n" +
            "\n" +
            "<!-- body -->\n" +
            "<table class=\"body-wrap\" bgcolor=\"#f6f6f6\">\n" +
            "  <tr>\n" +
            "    <td></td>\n" +
            "    <td class=\"container\" bgcolor=\"#FFFFFF\">\n" +
            "\n" +
            "      <!-- content -->\n" +
            "      <div class=\"content\">\n" +
            "      <table>\n" +
            "        <tr>\n" +
            "          <td>\n" +
            "\n" +
            "          \n" +
            "         \n" +
            "            <h1>Tienes una Campa&ntilde;a Pendiente</h1>\n" +
            "            <p>Un Anunciante quiere publicar en tu Establecimiento</p>\n" +
            "           \n" +
            "            <!-- button -->\n" +
            "            <table class=\"btn-primary\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "              <tr>\n" +
            "                <td>\n" +
            "                  <img src=\"http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png\"/>\n" +
            "  \n" +
            "                </td>\n" +
            "              </tr>\n" +
            "            </table>\n" +
            "            <!-- /button -->\n" +
            "            \n" +
            "              <p>Telefono (56-32) 227 41 51</p>\n" +
            "      <p>contacto@interac.cl Brasil con Freire 2147, Facultad de Ingeniería PUCV, Valparaíso - Chile</p>\n" +
            "          </td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "      </div>\n" +
            "      <!-- /content -->\n" +
            "      \n" +
            "    </td>\n" +
            "    <td></td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "<!-- /body -->\n" +
            "\n" +
            "<!-- footer -->\n" +
            "<table class=\"footer-wrap\">\n" +
            "  <tr>\n" +
            "    <td></td>\n" +
            "    <td class=\"container\">\n" +
            "      \n" +
            "      <!-- content -->\n" +
            "      <div class=\"content\">\n" +
            "        <table>\n" +
            "          <tr>\n" +
            "            <td align=\"center\">\n" +
            "   \n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "      <!-- /content -->\n" +
            "      \n" +
            "    </td>\n" +
            "    <td></td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "<!-- /footer -->\n" +
            "\n" +
            "</body>\n" +
            "</html>" ;

    private String aprobar="<!doctype html>\n" +
              "<html>\n" +
              "<head>\n" +
              "<meta name=\"viewport\" content=\"width=device-width\">\n" +
              "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
              "<title>Really Simple HTML Email Template</title>\n" +
              "<style>\n" +
              "/* -------------------------------------\n" +
              "    GLOBAL\n" +
              "------------------------------------- */\n" +
              "* {\n" +
              "  font-family: \"Helvetica Neue\", \"Helvetica\", Helvetica, Arial, sans-serif;\n" +
              "  font-size: 100%;\n" +
              "  line-height: 1.6em;\n" +
              "  margin: 0;\n" +
              "  padding: 0;\n" +
              "}\n" +
              "img {\n" +
              "  max-width: 600px;\n" +
              "  width: 100%;\n" +
              "}\n" +
              "body {\n" +
              "  -webkit-font-smoothing: antialiased;\n" +
              "  height: 100%;\n" +
              "  -webkit-text-size-adjust: none;\n" +
              "  width: 100% !important;\n" +
              "}\n" +
              "/* -------------------------------------\n" +
              "    ELEMENTS\n" +
              "------------------------------------- */\n" +
              "a {\n" +
              "  color: #348eda;\n" +
              "}\n" +
              ".btn-primary {\n" +
              "  Margin-bottom: 10px;\n" +
              "  width: auto !important;\n" +
              "}\n" +
              ".btn-primary td {\n" +
              "  background-color: #E52721; \n" +
              "  border-radius: 25px;\n" +
              "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif; \n" +
              "  font-size: 14px; \n" +
              "  text-align: center;\n" +
              "  vertical-align: top; \n" +
              "}\n" +
              ".btn-primary td a {\n" +
              "  background-color: #348eda;\n" +
              "  border: solid 1px #348eda;\n" +
              "  border-radius: 25px;\n" +
              "  border-width: 10px 20px;\n" +
              "  display: inline-block;\n" +
              "  color: #ffffff;\n" +
              "  cursor: pointer;\n" +
              "  font-weight: bold;\n" +
              "  line-height: 2;\n" +
              "  text-decoration: none;\n" +
              "}\n" +
              ".last {\n" +
              "  margin-bottom: 0;\n" +
              "}\n" +
              ".first {\n" +
              "  margin-top: 0;\n" +
              "}\n" +
              ".padding {\n" +
              "  padding: 10px 0;\n" +
              "}\n" +
              "/* -------------------------------------\n" +
              "    BODY\n" +
              "------------------------------------- */\n" +
              "table.body-wrap {\n" +
              "  padding: 20px;\n" +
              "  width: 100%;\n" +
              "}\n" +
              "table.body-wrap .container {\n" +
              "  border: 1px solid #f0f0f0;\n" +
              "}\n" +
              "/* -------------------------------------\n" +
              "    FOOTER\n" +
              "------------------------------------- */\n" +
              "table.footer-wrap {\n" +
              "  clear: both !important;\n" +
              "  width: 100%;  \n" +
              "}\n" +
              ".footer-wrap .container p {\n" +
              "  color: #666666;\n" +
              "  font-size: 12px;\n" +
              "  \n" +
              "}\n" +
              "table.footer-wrap a {\n" +
              "  color: #999999;\n" +
              "}\n" +
              "/* -------------------------------------\n" +
              "    TYPOGRAPHY\n" +
              "------------------------------------- */\n" +
              "h1, \n" +
              "h2, \n" +
              "h3 {\n" +
              "  color: #111111;\n" +
              "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif;\n" +
              "  font-weight: 200;\n" +
              "  line-height: 1.2em;\n" +
              "  margin: 40px 0 10px;\n" +
              "}\n" +
              "h1 {\n" +
              "  font-size: 36px;\n" +
              "}\n" +
              "h2 {\n" +
              "  font-size: 28px;\n" +
              "}\n" +
              "h3 {\n" +
              "  font-size: 22px;\n" +
              "}\n" +
              "p, \n" +
              "ul, \n" +
              "ol {\n" +
              "  font-size: 14px;\n" +
              "  font-weight: normal;\n" +
              "  margin-bottom: 10px;\n" +
              "}\n" +
              "ul li, \n" +
              "ol li {\n" +
              "  margin-left: 5px;\n" +
              "  list-style-position: inside;\n" +
              "}\n" +
              "/* ---------------------------------------------------\n" +
              "    RESPONSIVENESS\n" +
              "------------------------------------------------------ */\n" +
              "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
              ".container {\n" +
              "  clear: both !important;\n" +
              "  display: block !important;\n" +
              "  Margin: 0 auto !important;\n" +
              "  max-width: 600px !important;\n" +
              "}\n" +
              "/* Set the padding on the td rather than the div for Outlook compatibility */\n" +
              ".body-wrap .container {\n" +
              "  padding: 20px;\n" +
              "}\n" +
              "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
              ".content {\n" +
              "  display: block;\n" +
              "  margin: 0 auto;\n" +
              "  max-width: 600px;\n" +
              "}\n" +
              "/* Let's make sure tables in the content area are 100% wide */\n" +
              ".content table {\n" +
              "  width: 100%;\n" +
              "}\n" +
              "</style>\n" +
              "</head>\n" +
              "\n" +
              "<body bgcolor=\"#f6f6f6\">\n" +
              "\n" +
              "<!-- body -->\n" +
              "<table class=\"body-wrap\" bgcolor=\"#f6f6f6\">\n" +
              "  <tr>\n" +
              "    <td></td>\n" +
              "    <td class=\"container\" bgcolor=\"#FFFFFF\">\n" +
              "\n" +
              "      <!-- content -->\n" +
              "      <div class=\"content\">\n" +
              "      <table>\n" +
              "        <tr>\n" +
              "          <td>\n" +
              "\n" +
              "          \n" +
              "         \n" +
              "            <h1>Se ha aprobado tu campa&ntilde;a</h1>\n" +
              "            <p>se aprobo tu contenido para ser publicitado</p>\n" +
              "           \n" +
              "            <!-- button -->\n" +
              "            <table class=\"btn-primary\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
              "              <tr>\n" +
              "                <td>\n" +
              "                  <img src=\"http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png\"/>\n" +
              "                </td>\n" +
              "              </tr>\n" +
              "            </table>\n" +
              "            <!-- /button -->\n" +
              "            <table class=\"body-wrap\">\n" +
              "               <tr>\n" +
              "                <td>\n" +
              "                   <h3>ID</h3>\n" +
              "                </td>\n" +
              "                 <td>\n" +
              "                    <h3>Establecimiento</h3>\n" +
              "                 </td>\n" +
              "                  <td>\n" +
              "                      <h3>Nro Pantallas</h3>\n" +
              "                  </td>  \n" +
              "                  <td>" +
              "                    <h3>Valor Mensual</h3>" +
              "                  </td>" +
              "                  <td>\n" +
              "                     <h3>Valor de Campa&ntilde;a</h3>\n" +
              "                  </td>\n" +
              "               </tr>\n" +
              "               <tr>\n" +
              "                <td>\n" +
              "                  <p>$id</p>\n" +
              "                </td> \n" +
              "                <td>\n" +
              "                  <p>$establecimiento</p>\n" +
              "                </td>\n" +
              "                <td>\n" +
              "                  <p>$numerodePantallas</p>\n" +
              "                </td>\n" +
              "                  <td>" +
              "                    <p>$mensualvalor</p>" +
              "                  </td>" +
              "                <td>\n" +
              "                  <p>$valordecampanas</p>\n" +
              "                </td>\n" +
              "               </tr>\n" +
              "               \n" +
              "            \n" +
              "            </table>\n" +
              "          \n" +
              "          </td>\n" +
              "        </tr>\n" +
              "      </table>\n" +
              "      </div>\n" +
              "      <!-- /content -->\n" +
              "      \n" +
              "    </td>\n" +
              "    <td></td>\n" +
              "  </tr>\n" +
              "</table>\n" +
              "<!-- /body -->\n" +
              "\n" +
              "<!-- footer -->\n" +
              "<table class=\"footer-wrap\">\n" +
              "  <tr>\n" +
              "    <td></td>\n" +
              "    <td class=\"container\">\n" +
              "      \n" +
              "      <!-- content -->\n" +
              "      <div class=\"content\">\n" +
              "        <table>\n" +
              "          <tr>\n" +
              "            <td align=\"center\">\n" +
              "              \n" +
              "      <p>Telefono (56-32) 227 41 51</p>\n" +
              "      <p>contacto@interac.cl Brasil con Freire 2147, Facultad de Ingeniería PUCV, Valparaíso - Chile</p>\n" +
              "            </td>\n" +
              "          </tr>\n" +
              "        </table>\n" +
              "      </div>\n" +
              "      <!-- /content -->\n" +
              "      \n" +
              "    </td>\n" +
              "    <td></td>\n" +
              "  </tr>\n" +
              "</table>\n" +
              "<!-- /footer -->\n" +
              "\n" +
              "</body>\n" +
              "</html> ";

        // Plantilla rechazar
        private String rechazar="<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width\">\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<title>Really Simple HTML Email Template</title>\n" +
                "<style>\n" +
                "/* -------------------------------------\n" +
                "    GLOBAL\n" +
                "------------------------------------- */\n" +
                "* {\n" +
                "  font-family: \"Helvetica Neue\", \"Helvetica\", Helvetica, Arial, sans-serif;\n" +
                "  font-size: 100%;\n" +
                "  line-height: 1.6em;\n" +
                "  margin: 0;\n" +
                "  padding: 0;\n" +
                "}\n" +
                "img {\n" +
                "  max-width: 600px;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "body {\n" +
                "  -webkit-font-smoothing: antialiased;\n" +
                "  height: 100%;\n" +
                "  -webkit-text-size-adjust: none;\n" +
                "  width: 100% !important;\n" +
                "}\n" +
                "/* -------------------------------------\n" +
                "    ELEMENTS\n" +
                "------------------------------------- */\n" +
                "a {\n" +
                "  color: #348eda;\n" +
                "}\n" +
                ".btn-primary {\n" +
                "  Margin-bottom: 10px;\n" +
                "  width: auto !important;\n" +
                "}\n" +
                ".btn-primary td {\n" +
                "  background-color: #E52721; \n" +
                "  border-radius: 25px;\n" +
                "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif; \n" +
                "  font-size: 14px; \n" +
                "  text-align: center;\n" +
                "  vertical-align: top; \n" +
                "}\n" +
                ".btn-primary td a {\n" +
                "  background-color: #348eda;\n" +
                "  border: solid 1px #348eda;\n" +
                "  border-radius: 25px;\n" +
                "  border-width: 10px 20px;\n" +
                "  display: inline-block;\n" +
                "  color: #ffffff;\n" +
                "  cursor: pointer;\n" +
                "  font-weight: bold;\n" +
                "  line-height: 2;\n" +
                "  text-decoration: none;\n" +
                "}\n" +
                ".last {\n" +
                "  margin-bottom: 0;\n" +
                "}\n" +
                ".first {\n" +
                "  margin-top: 0;\n" +
                "}\n" +
                ".padding {\n" +
                "  padding: 10px 0;\n" +
                "}\n" +
                "/* -------------------------------------\n" +
                "    BODY\n" +
                "------------------------------------- */\n" +
                "table.body-wrap {\n" +
                "  padding: 20px;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "table.body-wrap .container {\n" +
                "  border: 1px solid #f0f0f0;\n" +
                "}\n" +
                "/* -------------------------------------\n" +
                "    FOOTER\n" +
                "------------------------------------- */\n" +
                "table.footer-wrap {\n" +
                "  clear: both !important;\n" +
                "  width: 100%;  \n" +
                "}\n" +
                ".footer-wrap .container p {\n" +
                "  color: #666666;\n" +
                "  font-size: 12px;\n" +
                "  \n" +
                "}\n" +
                "table.footer-wrap a {\n" +
                "  color: #999999;\n" +
                "}\n" +
                "/* -------------------------------------\n" +
                "    TYPOGRAPHY\n" +
                "------------------------------------- */\n" +
                "h1, \n" +
                "h2, \n" +
                "h3 {\n" +
                "  color: #111111;\n" +
                "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif;\n" +
                "  font-weight: 200;\n" +
                "  line-height: 1.2em;\n" +
                "  margin: 40px 0 10px;\n" +
                "}\n" +
                "h1 {\n" +
                "  font-size: 36px;\n" +
                "}\n" +
                "h2 {\n" +
                "  font-size: 28px;\n" +
                "}\n" +
                "h3 {\n" +
                "  font-size: 22px;\n" +
                "}\n" +
                "p, \n" +
                "ul, \n" +
                "ol {\n" +
                "  font-size: 14px;\n" +
                "  font-weight: normal;\n" +
                "  margin-bottom: 10px;\n" +
                "}\n" +
                "ul li, \n" +
                "ol li {\n" +
                "  margin-left: 5px;\n" +
                "  list-style-position: inside;\n" +
                "}\n" +
                "/* ---------------------------------------------------\n" +
                "    RESPONSIVENESS\n" +
                "------------------------------------------------------ */\n" +
                "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
                ".container {\n" +
                "  clear: both !important;\n" +
                "  display: block !important;\n" +
                "  Margin: 0 auto !important;\n" +
                "  max-width: 600px !important;\n" +
                "}\n" +
                "/* Set the padding on the td rather than the div for Outlook compatibility */\n" +
                ".body-wrap .container {\n" +
                "  padding: 20px;\n" +
                "}\n" +
                "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
                ".content {\n" +
                "  display: block;\n" +
                "  margin: 0 auto;\n" +
                "  max-width: 600px;\n" +
                "}\n" +
                "/* Let's make sure tables in the content area are 100% wide */\n" +
                ".content table {\n" +
                "  width: 100%;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body bgcolor=\"#f6f6f6\">\n" +
                "\n" +
                "<!-- body -->\n" +
                "<table class=\"body-wrap\" bgcolor=\"#f6f6f6\">\n" +
                "  <tr>\n" +
                "    <td></td>\n" +
                "    <td class=\"container\" bgcolor=\"#FFFFFF\">\n" +
                "\n" +
                "      <!-- content -->\n" +
                "      <div class=\"content\">\n" +
                "      <table>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "\n" +
                "          \n" +
                "         \n" +
                "            <h1>El cliente ha rechazado tu campa&ntilde;a</h1>\n" +
                "            <p>por favor pongase en contacto con el cliente para mas detalles</p>\n" +
                "           \n" +
                "            <!-- button -->\n" +
                "            <table class=\"btn-primary\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "              <tr>\n" +
                "                <td>\n" +
                "                  <img src=\"http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png\"/>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "            <!-- /button -->\n" +
                "            <table class=\"body-wrap\">\n" +
                "               <tr>\n" +
                "                <td>\n" +
                "                   <h3>ID</h3>\n" +
                "                </td>\n" +
                "                 <td>\n" +
                "                    <h3>Establecimiento</h3>\n" +
                "                 </td>\n" +
                "                  <td>\n" +
                "                      <h3>Motivo de rechazo</h3>\n" +
                "                  </td>  \n" +
                "                  <td>" +
                "                    <h3>Comentarios</h3>" +
                "                  </td>" +
                "                  </td>  \n" +
                "                  <td>" +
                "                    <h3>Valor campaña</h3>" +
                "                  </td>" +
                "               </tr>\n" +
                "               <tr>\n" +
                "                <td>\n" +
                "                  <p>$id</p>\n" +
                "                </td> \n" +
                "                <td>\n" +
                "                  <p>$establecimiento</p>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                  <p>$razonRechazo</p>\n" +
                "                </td>\n" +
                "                  <td>" +
                "                    <p>$comentarios</p>" +
                "                  </td>" +
                "                  <td>" +
                "                    <p>$valor</p>" +
                "                  </td>" +
                "               </tr>\n" +
                "               \n" +
                "            \n" +
                "            </table>\n" +
                "          \n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      </div>\n" +
                "      <!-- /content -->\n" +
                "      \n" +
                "    </td>\n" +
                "    <td></td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<!-- /body -->\n" +
                "\n" +
                "<!-- footer -->\n" +
                "<table class=\"footer-wrap\">\n" +
                "  <tr>\n" +
                "    <td></td>\n" +
                "    <td class=\"container\">\n" +
                "      \n" +
                "      <!-- content -->\n" +
                "      <div class=\"content\">\n" +
                "        <table>\n" +
                "          <tr>\n" +
                "            <td align=\"center\">\n" +
                "              \n" +
                "      <p>Telefono (56-32) 227 41 51</p>\n" +
                "      <p>contacto@interac.cl Brasil con Freire 2147, Facultad de Ingeniería PUCV, Valparaíso - Chile</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "      <!-- /content -->\n" +
                "      \n" +
                "    </td>\n" +
                "    <td></td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<!-- /footer -->\n" +
                "\n" +
                "</body>\n" +
                "</html> ";

        private String registro="<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta name=\"viewport\" content=\"width=device-width\">\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "<title>Really Simple HTML Email Template</title>\n" +
            "<style>\n" +
            "/* -------------------------------------\n" +
            "    GLOBAL\n" +
            "------------------------------------- */\n" +
            "* {\n" +
            "  font-family: \"Helvetica Neue\", \"Helvetica\", Helvetica, Arial, sans-serif;\n" +
            "  font-size: 100%;\n" +
            "  line-height: 1.6em;\n" +
            "  margin: 0;\n" +
            "  padding: 0;\n" +
            "}\n" +
            "img {\n" +
            "  max-width: 600px;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "body {\n" +
            "  -webkit-font-smoothing: antialiased;\n" +
            "  height: 100%;\n" +
            "  -webkit-text-size-adjust: none;\n" +
            "  width: 100% !important;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    ELEMENTS\n" +
            "------------------------------------- */\n" +
            "a {\n" +
            "  color: #348eda;\n" +
            "}\n" +
            ".btn-primary {\n" +
            "  Margin-bottom: 10px;\n" +
            "  width: auto !important;\n" +
            "}\n" +
            ".btn-primary td {\n" +
            "  background-color: #E52721; \n" +
            "  border-radius: 25px;\n" +
            "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif; \n" +
            "  font-size: 14px; \n" +
            "  text-align: center;\n" +
            "  vertical-align: top; \n" +
            "}\n" +
            ".btn-primary td a {\n" +
            "  background-color: #348eda;\n" +
            "  border: solid 1px #348eda;\n" +
            "  border-radius: 25px;\n" +
            "  border-width: 10px 20px;\n" +
            "  display: inline-block;\n" +
            "  color: #ffffff;\n" +
            "  cursor: pointer;\n" +
            "  font-weight: bold;\n" +
            "  line-height: 2;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            ".last {\n" +
            "  margin-bottom: 0;\n" +
            "}\n" +
            ".first {\n" +
            "  margin-top: 0;\n" +
            "}\n" +
            ".padding {\n" +
            "  padding: 10px 0;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    BODY\n" +
            "------------------------------------- */\n" +
            "table.body-wrap {\n" +
            "  padding: 20px;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "table.body-wrap .container {\n" +
            "  border: 1px solid #f0f0f0;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    FOOTER\n" +
            "------------------------------------- */\n" +
            "table.footer-wrap {\n" +
            "  clear: both !important;\n" +
            "  width: 100%;  \n" +
            "}\n" +
            ".footer-wrap .container p {\n" +
            "  color: #666666;\n" +
            "  font-size: 12px;\n" +
            "  \n" +
            "}\n" +
            "table.footer-wrap a {\n" +
            "  color: #999999;\n" +
            "}\n" +
            "/* -------------------------------------\n" +
            "    TYPOGRAPHY\n" +
            "------------------------------------- */\n" +
            "h1, \n" +
            "h2, \n" +
            "h3 {\n" +
            "  color: #111111;\n" +
            "  font-family: \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif;\n" +
            "  font-weight: 200;\n" +
            "  line-height: 1.2em;\n" +
            "  margin: 40px 0 10px;\n" +
            "}\n" +
            "h1 {\n" +
            "  font-size: 36px;\n" +
            "}\n" +
            "h2 {\n" +
            "  font-size: 28px;\n" +
            "}\n" +
            "h3 {\n" +
            "  font-size: 22px;\n" +
            "}\n" +
            "p, \n" +
            "ul, \n" +
            "ol {\n" +
            "  font-size: 14px;\n" +
            "  font-weight: normal;\n" +
            "  margin-bottom: 10px;\n" +
            "}\n" +
            "ul li, \n" +
            "ol li {\n" +
            "  margin-left: 5px;\n" +
            "  list-style-position: inside;\n" +
            "}\n" +
            "/* ---------------------------------------------------\n" +
            "    RESPONSIVENESS\n" +
            "------------------------------------------------------ */\n" +
            "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
            ".container {\n" +
            "  clear: both !important;\n" +
            "  display: block !important;\n" +
            "  Margin: 0 auto !important;\n" +
            "  max-width: 600px !important;\n" +
            "}\n" +
            "/* Set the padding on the td rather than the div for Outlook compatibility */\n" +
            ".body-wrap .container {\n" +
            "  padding: 20px;\n" +
            "}\n" +
            "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
            ".content {\n" +
            "  display: block;\n" +
            "  margin: 0 auto;\n" +
            "  max-width: 600px;\n" +
            "}\n" +
            "/* Let's make sure tables in the content area are 100% wide */\n" +
            ".content table {\n" +
            "  width: 100%;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "\n" +
            "<body bgcolor=\"#f6f6f6\">\n" +
            "\n" +
            "<!-- body -->\n" +
            "<table class=\"body-wrap\" bgcolor=\"#f6f6f6\">\n" +
            "  <tr>\n" +
            "    <td></td>\n" +
            "    <td class=\"container\" bgcolor=\"#FFFFFF\">\n" +
            "\n" +
            "      <!-- content -->\n" +
            "      <div class=\"content\">\n" +
            "      <table>\n" +
            "        <tr>\n" +
            "          <td>\n" +
            "\n" +
            "          \n" +
            "         \n" +
            "            <h1>Bienvenido a Interac</h1>\n" +
            "            \n" +
            "           \n" +
            "            <!-- button -->\n" +
            "            <table class=\"btn-primary\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "              <tr>\n" +
            "                <td>\n" +
            "                  <img src=\"http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png\"/>\n" +
            "                </td>\n" +
            "              </tr>\n" +
            "            </table>\n" +
            "            <!-- /button -->\n" +
            "            <p>verifica tu correo haciendo click aqui ---><a href=\"$url\">Interac</a></p> \n" +
            "          </td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "      </div>\n" +
            "      <!-- /content -->\n" +
            "      \n" +
            "    </td>\n" +
            "    <td></td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "<!-- /body -->\n" +
            "\n" +
            "<!-- footer -->\n" +
            "<table class=\"footer-wrap\">\n" +
            "  <tr>\n" +
            "    <td></td>\n" +
            "    <td class=\"container\">\n" +
            "      \n" +
            "      <!-- content -->\n" +
            "      <div class=\"content\">\n" +
            "        <table>\n" +
            "          <tr>\n" +
            "            <td align=\"center\">\n" +
            "              \n" +
            "      <p>Telefono (56-32) 227 41 51</p>\n" +
            "      <p>contacto@interac.cl Brasil con Freire 2147, Facultad de Ingeniería PUCV, Valparaíso - Chile</p>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "      <!-- /content -->\n" +
            "      \n" +
            "    </td>\n" +
            "    <td></td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "<!-- /footer -->\n" +
            "\n" +
            "</body>\n" +
            "</html>";

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

    public String getAprobar() {
        return aprobar;
    }

    public void setAprobar(String aprobar) {
        this.aprobar = aprobar;
    }

    public String getRechazar() { return rechazar; }

    public void setRechazar(String rechazar) { this.rechazar = rechazar; }

    public String getRegistro() { return registro; }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}