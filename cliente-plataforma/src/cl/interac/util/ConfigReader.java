package cl.interac.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by claudio on 24-08-15.
 */
public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        URL location = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        try {
            properties.load(new FileInputStream(location.getFile() + "config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("No se ha encontrado el archivo de configuración");
            System.exit(0);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
