package cl.interac.scheduled;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

import cl.interac.dao.CampanaDAO;
import cl.interac.util.components.*;
import cl.interac.entidades.Campana;
import cl.interac.negocio.LogicaCampana;
import cl.interac.util.components.FacesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


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

        try {
            String ambiente = propertyReader.get("ambiente");

            if ("desarrollo".equals(ambiente)) {
                // dentro del server siempre podra subir, no importa si es wintendo o linux
                for (Campana ca : campanasvencidas){
                    System.err.println(ca.getContenido().getIdcontenido());
                    logicaCampana.eliminarCampana(ca);
                }

            }
            else if ("produccion".equals(ambiente)) {
                for (Campana ca : campanasvencidas){
                    Files.delete(Paths.get("/home/ec2-user/media/demoPublicidad/" + ca.getContenido().getPath()));
                    logicaCampana.eliminarCampana(ca);
                }
            }

        }catch (Exception e){
            FacesUtil.mostrarMensajeInformativo("Operación Fallida", "Algo Ocurrio");
        }
    }
}
