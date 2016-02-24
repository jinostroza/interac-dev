package cl.interac.presentacion.ubicacion;

import cl.interac.entidades.Comunas;
import cl.interac.entidades.Provincias;
import cl.interac.entidades.Regiones;
import cl.interac.entidades.Ubicacion;
import cl.interac.negocio.LogicaComunas;
import cl.interac.negocio.LogicaProvincias;
import cl.interac.negocio.LogicaRegiones;
import cl.interac.negocio.LogicaUbicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 27-04-2015.
 */

@Component
@Scope("view")
public class MantenedorUbicacion implements Serializable {

    private Ubicacion ubicacion;
    private Regiones region;
    private Provincias provincia;
    private Comunas comuna;
    private List<Regiones> regionesList;
    private List<Provincias> provinciasList;
    private List<Comunas> comunasList;

    @Autowired
    private LogicaUbicacion logicaUbicacion;
    @Autowired
    private LogicaRegiones logicaRegiones;
    @Autowired
    private LogicaProvincias logicaProvincias;
    @Autowired
    private LogicaComunas logicaComunas;

    private List<Ubicacion> obtenerUbicacion;

    public void inicio() {

        ubicacion = new Ubicacion();
        regionesList = logicaRegiones.obtenerTodas();
        provinciasList = logicaProvincias.obtenerTodas();
        comunasList = logicaComunas.obtenerTodas();
    }

    public void guardarUb() {
        logicaUbicacion.guardar(ubicacion);
    }

    public void provincias(){

        provinciasList = logicaProvincias.obtenerConRealacion(region.getRegion_id());

    }
    public void comunas(){

        comunasList = logicaComunas.obtenerConRealacion(provincia.getProvincia_id());
    }

    public List<Ubicacion> getObtenerUbicacion() {
        return obtenerUbicacion;
    }

    public void setObtenerUbicacion(List<Ubicacion> obtenerUbicacion) {
        this.obtenerUbicacion = obtenerUbicacion;
    }

    // getters and setters
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Regiones> getRegionesList() {
        return regionesList;
    }

    public void setRegionesList(List<Regiones> regionesList) {
        this.regionesList = regionesList;
    }

    public List<Provincias> getProvinciasList() {
        return provinciasList;
    }

    public void setProvinciasList(List<Provincias> provinciasList) {
        this.provinciasList = provinciasList;
    }

    public List<Comunas> getComunasList() {
        return comunasList;
    }

    public void setComunasList(List<Comunas> comunasList) {
        this.comunasList = comunasList;
    }

    public Comunas getComuna() {
        return comuna;
    }

    public void setComuna(Comunas comuna) {
        this.comuna = comuna;
    }

    public Provincias getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincias provincia) {
        this.provincia = provincia;
    }

    public Regiones getRegion() {
        return region;
    }

    public void setRegion(Regiones region) {
        this.region = region;
    }
}
