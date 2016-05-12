package cl.interac.scheduled;

import cl.interac.entidades.Campana;
import cl.interac.entidades.Contenido;
import cl.interac.entidades.Contrato;
import cl.interac.entidades.Establecimiento;
import cl.interac.negocio.LogicaCampana;
import cl.interac.negocio.LogicaContrato;
import cl.interac.util.components.Constantes;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import cl.interac.util.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;


/**
 * Created by Joaco on 25/11/2015.
 */
@Component()
public class CronService {

    private List<Campana> campanasvencidas;
    private List<Contrato> contratos;
    private Long dias;


    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private LogicaContrato logicaContrato;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private Constantes constantes;


    @Scheduled(cron="*/60 * *  * * ?")
    public void eliminarFicheroProgramado(){
        campanasvencidas = logicaCampana.obtenerPorFecha(Date.from(Instant.now()));
        //elimina campañas terminadas
        try {
            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente)) {
                for (Campana ca : campanasvencidas){
                    System.err.println(ca.getIdcampana());
                    logicaCampana.eliminarCampana(ca);
                }

            }
            else if ("produccion".equals(ambiente)) {
                for (Campana ca : campanasvencidas){
                    for(Establecimiento et : ca.getEstablecimientoList()) {
                        String carpetaDestino = et.getCarpetaFtp();
                        for(Contenido co : ca.getContenidoList() ){
                            Files.delete(Paths.get("/home/ec2-user/media/" + carpetaDestino + "/" + co.getPath()));
                        }
                    }


                    logicaCampana.eliminarCampana(ca);
                }
            }

        }catch (Exception e){
            FacesUtil.mostrarMensajeInformativo("Operación Fallida", "Algo Ocurrio");
        }
    }

    @Scheduled(cron="0 0 8 * * *")
    public void notificarcontrato(){
        contratos = logicaContrato.obtenerTodos();
        String ambiente = propertyReader.get("ambiente");
        //notifica contratos por vencer
        if ("produccion".equals(ambiente)) {


                for (Contrato co : contratos) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = Date.from(Instant.now());
                        d2 = co.getFechaFin();


                        //in milliseconds
                        long diff = d2.getTime() - d1.getTime();

                        long diffSeconds = diff / 1000 % 60;
                        long diffMinutes = diff / (60 * 1000) % 60;
                        long diffHours = diff / (60 * 60 * 1000) % 24;
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        dias = diffDays + 1;
                        if (dias==7){
                            //cuerpo del mensaje

                            String mensajeAnunciante = new String(constantes.getContratos());
                            mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$id",String.valueOf(co.getIdcontrato()));
                            mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$cliente",co.getIdcliente().getUsername());
                            mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$empresa",co.getIdempresa().getNombreEmpresa());
                            mensajeAnunciante = mensajeAnunciante.replaceFirst("\\$empresa",co.getFechaFin().toString());

                            //correo
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                            String[] alertas = new String[1];
                            alertas[0]="contacto@interac.cl";
                             try {
                              Thread.sleep(6000);
                             mailSender.send(alertas, "Interac", mensajeAnunciante);
                            } catch (InterruptedException e) {
                              e.printStackTrace();
                            FacesUtil.mostrarMensajeInformativo("Error", "Fallo correo"+alertas );
                             }
                            }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }


