package cl.interac.util.components;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class Encriptador {

	private Cipher cipherDecrypt;
	private Cipher cipherEncrypt;

	public Encriptador() {
		try {
			String passPhrase = loadProperties("passEncrypt");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(passPhrase.getBytes("UTF8"));
			KeyGenerator kGen = KeyGenerator.getInstance("DESEDE");
			kGen.init(168, sr);
			Key key = kGen.generateKey();

			cipherEncrypt = Cipher.getInstance("DESEDE/ECB/PKCS5Padding");
			cipherEncrypt.init(Cipher.ENCRYPT_MODE, key);
			cipherDecrypt = Cipher.getInstance("DESEDE/ECB/PKCS5Padding");
			cipherDecrypt.init(Cipher.DECRYPT_MODE, key);
		} catch (UnsupportedEncodingException e) {
		} catch (NoSuchPaddingException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidKeyException e) {
		}
	}

	public String loadProperties(String key) {
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

	public String encriptar(String str) {
		try {
			return StringUtils.byte2hex(cipherEncrypt.doFinal(str.getBytes("UTF8")));
		} catch (UnsupportedEncodingException e) {
		} catch (BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		}

		return null;
	}

	public String desencriptar(String str) {
		try {
			return new String(cipherDecrypt.doFinal(StringUtils.hex2byte(str)), "UTF8");
		} catch (UnsupportedEncodingException e) {
		} catch (BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		}

		return null;
	}
}