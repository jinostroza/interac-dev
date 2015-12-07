package cl.interac.util.services;


import cl.interac.util.components.Constantes;
import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import java.util.List;
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

    public void send(String[] destinos,String asunto,String mensaje) {

        final Email email = new Email();
       try {
           for(int i=0;i < destinos.length; i++ ) {
               email.addRecipient("Interac", destinos[i], Message.RecipientType.TO);
           }
           email.setFromAddress("contacto", "contacto@interac.cl");
           email.setSubject(asunto);
           email.setTextHTML(mensaje);

           Mailer mailer = new Mailer("mx1.nixiweb.com", 587, "contacto@interac.cl", "interac2015", TransportStrategy.SMTP_TLS);

           mailer.sendMail(email);


       }catch (Exception e){
            e.printStackTrace();
        }
    }

}



