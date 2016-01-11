package cl.interac.security;

import org.springframework.security.core.token.Sha512DigestUtils;

import java.security.MessageDigest;

/**
 * Created by claudio on 11-11-14.
 */
public class SHA512 {
    public static String sha512(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
