package cl.interac.util.components;

import cl.interac.util.pojo.Encriptador;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
	
	/**
	 * Retorna valor de configuraci�n. <p>
	 * Retorna valor de configuraci�n correspondiente a la variable 'key', si no existe retorna null.
	 * @param key nombre de la variable de configuraci�n a buscar.
	 * @return String correspondiente al valor de configuraci�n de la llave key
	 */
	public static String loadProperties(String key) {
		Properties props = new Properties();
		InputStream is = null;
		try {
			is = Encriptador.class.getResourceAsStream("/configuraciones/main.properties");
			props.load(is);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return props.getProperty(key);
	}
	
	public static Properties getEmailProperties(String account){		
		Properties properties = new Properties();
        properties.put("mail.smtp.host", loadProperties(account+"_host"));
        properties.put("mail.smtp.port", loadProperties(account+"_port"));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "false");
		return properties;
	}
}
