package cl.interac.security;

import org.springframework.security.core.token.Sha512DigestUtils;

/**
 * Created by claudio on 11-11-14.
 */
public class SHA512 {
    public static String encode(String texto) {

        return Sha512DigestUtils.shaHex(texto);
    }
}
