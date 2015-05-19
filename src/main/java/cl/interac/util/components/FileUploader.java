package cl.interac.util.components;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;


/**
 * Created by claudio on 19-05-15.
 */
@Component
@Scope("application")
public class FileUploader {
    @Autowired
    Constantes constantes;

    public Object subir(FileUploadEvent fue) {
        try {
            String ruta = constantes.getPathArchivos();

            // creamos el fichero en la BD
            // acá hacemos algo obviamente

            // lo copia a la ruta del server
            File result = new File(ruta);
            result.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(result.getAbsolutePath() + "/algunID");
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = fue.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            return null; // aca lo podemos cambiar por el entity del documento
        } catch (IOException e) {
            e.printStackTrace();
            FacesUtil.mostrarMensajeError("No se ha subido el fichero", "Error interno, consulte con el administrador");
            return null;
        }
    }

    public Object subir(UploadedFile uf) {
        String ruta = constantes.getPathArchivos();
        // creamos el fichero en la BD
        // acá hacemos algo obviamente

        // lo copia a la ruta del server
        File result = new File(ruta);
        result.mkdirs();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(result.getAbsolutePath() + "/algunID");
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = uf.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            return null; // aca lo podemos cambiar por el entity del documento
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
