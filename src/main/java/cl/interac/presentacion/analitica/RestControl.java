package cl.interac.presentacion.analitica;

import cl.interac.entidades.Analitica;
import cl.interac.negocio.LogicaAnalitica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by mary on 04/08/2016.
 */
@RestController
public class RestControl {

    private Analitica analitica;
    @Autowired
    private LogicaAnalitica logicaAnalitica;


    //-------------------toda la analitica--------------------------------------------------------

    @RequestMapping(value = "/all/", method = RequestMethod.GET )
    public ResponseEntity<List<Analitica>> obtenerTodos() {
        List<Analitica> users = logicaAnalitica.obtenerTodos();
        if(users.isEmpty()){
            return new ResponseEntity<List<Analitica>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Analitica>>(users, HttpStatus.OK);
    }

//-------------------Insertar analitica--------------------------------------------------------

    @RequestMapping(value = "/all/", method = RequestMethod.POST)
    public ResponseEntity<Void> insertData(@RequestBody Analitica analitica, UriComponentsBuilder ucBuilder) {

       logicaAnalitica.guardar(analitica);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/all/{id}").buildAndExpand(analitica.getId_analitica()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/totem/",method = RequestMethod.POST)
    public void processJason(@RequestParam("json") String json){
        analitica.setExpresion(json);
        logicaAnalitica.guardar(analitica);
        return ;
    }



    public Analitica getAnalitica() {
        return analitica;
    }

    public void setAnalitica(Analitica analitica) {
        this.analitica = analitica;
    }
}
