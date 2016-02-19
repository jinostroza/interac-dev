package cl.interac.scheduled;

import cl.interac.entidades.Campana;
import cl.interac.entidades.Contenido;
import cl.interac.entidades.Establecimiento;
import cl.interac.negocio.LogicaCampana;
import cl.interac.util.components.FacesUtil;
import cl.interac.util.components.PropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.List;


/**
 * Created by Joaco on 25/11/2015.
 */
@Component()
public class CronService {

    private List<Campana> campanasvencidas;



    @Autowired
    private LogicaCampana logicaCampana;
    @Autowired
    private PropertyReader propertyReader;


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
}
