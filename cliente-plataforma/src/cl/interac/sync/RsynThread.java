package cl.interac.sync;

import cl.interac.util.ConfigReader;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by claudio on 24-08-15.
 */
public class RsynThread implements Runnable {
    @Override
    public void run() {
        ConfigReader cr = new ConfigReader();
        String dataDir = cr.get("dataDir");

        FtpClient ftpClient = new FtpClient(cr.get("server"), Integer.parseInt(cr.get("port")), cr.get("user"), cr.get("pass"));
        ftpClient.connect();

        // nos aseguramos que exista el directorio de data
        (new File(dataDir)).mkdirs();

        try {
            String dir = "./";
            for (FTPFile file : ftpClient.ls(dir)) {
                File localFile = new File(dataDir + file.getName());
                if (localFile.exists()) {
                    Calendar remoteCalendar = ftpClient.lastUpdate(dir + file.getName());

                    if (remoteCalendar == null) continue;
                    Calendar localCalendar = Calendar.getInstance();
                    localCalendar.setTimeInMillis(localFile.lastModified());

                    if (remoteCalendar.after(localCalendar) ||  file.getSize() != localFile.length()) {
                        ftpClient.get(file, localFile);
                    }
                } else
                    ftpClient.get(file, localFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        (new Thread(new RsynThread())).start();
    }
}
