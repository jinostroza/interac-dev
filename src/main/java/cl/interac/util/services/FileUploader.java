package cl.interac.util.services;

import cl.interac.util.components.Constantes;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.io.*;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

/**
 * Created by claudio on 06-11-14.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FileUploader {
    @Autowired
    private Constantes constantes;
    public String subir(UploadedFile archivo, String relativePath) {
        try {
            String path = constantes.getPathArchivos() + relativePath;
            new File(path).mkdirs();
            path += "/" + archivo.getFileName();
            new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = archivo.getInputstream();
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
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String subir(FileUploadEvent fue, String relativePath) {
        try {
            String path = constantes.getPathArchivos() + relativePath;
            new File(path).mkdirs();
            path += "/" + fue.getFile().getFileName();
            new File(path);

            FileOutputStream fileOutputStream = new FileOutputStream(path);
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
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
