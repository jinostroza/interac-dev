package cl.interac.sync;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by claudio on 24-08-15.
 */
public class FtpClient {
    private FTPClient ftpClient;
    private String servidor;
    private int puerto;
    private String usuario;
    private String clave;

    public FtpClient(String servidor, int puerto, String usuario, String clave) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.usuario = usuario;
        this.clave = clave;
    }

    public boolean connect() {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(servidor, puerto);
            ftpClient.login(usuario, clave);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public FTPFile[] ls(String folder) {
        FTPFile[] archivos = null;
        try {
            archivos = ftpClient.listFiles(folder);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return archivos;
    }

    public boolean disconnect() {
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void get(FTPFile file, File localFile) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(localFile));

        ftpClient.retrieveFile(file.getName(), os);
        os.close();
    }

    public Calendar lastUpdate(String remotePath) throws IOException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = ftpClient.getModificationTime(remotePath);

        if (time == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(dateFormat.parse(time));
        return c;
    }
}
