package cl.interac.util.services;


import cl.interac.util.components.Constantes;
import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * Created by pclucho on 27-10-2015.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MailSender {

    private Properties properties;
    @Autowired
    private Constantes constantes;

    public void send() {

        final Email email = new Email();

        email.setFromAddress("contacto", "contacto@interac.cl");
        email.setSubject("ejemplo correo interosky");
        email.addRecipient("colivares","claudiopololivares@gmail.com", Message.RecipientType.TO);

        email.setTextHTML("<img style='background-color:red;'  src='http://www.interac.cl/wp-content/themes/theme-interac/img/logo/interac-blanco.png'>" +
                "<b>We should meet up!</b><img src='cid:wink2'>");

// embed images and include downloadable attachments


        Mailer mailer = new Mailer("mx1.nixiweb.com",587,"contacto@interac.cl","interac2015",TransportStrategy.SMTP_TLS);
        mailer.sendMail(email);

    }
}


