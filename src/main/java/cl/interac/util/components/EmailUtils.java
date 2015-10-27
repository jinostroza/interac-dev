package cl.interac.util.components;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {
	private static String user = "";
	private static String password = "";
		
	public static Boolean sendMail(String to, String subject, String body, String signature){
		String out = "contacto";
        Properties props = ConfigUtils.getEmailProperties(out);
        user = ConfigUtils.loadProperties(out+"_user");
        password = ConfigUtils.loadProperties(out+"_password");
                
        String message = body+"\n-- \n"+signature;
        
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        };
 
        Session session = Session.getInstance(props, auth);
        
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        try{
        	msg.setFrom(new InternetAddress(user));
	        InternetAddress[] toAddresses = { new InternetAddress(to)};
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(message);
        }catch(Exception e){
        	e.printStackTrace();
        	return false;
        }
        // sends the e-mail
        try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
	}
	public static Boolean sendMail(List<String> to, String subject, String body, String signature){
		String out = "contacto";
        Properties props = ConfigUtils.getEmailProperties(out);
        user = ConfigUtils.loadProperties(out+"_user");
        password = ConfigUtils.loadProperties(out+"_password");
                
        String message = body+"\n-- \n"+signature;
        
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        };
 
        Session session = Session.getInstance(props, auth);
        
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        for (int i = 0; i < to.size(); i++) {
        	
			//log.info(to.get(i));
        	 try{
             	msg.setFrom(new InternetAddress(user));
     	        InternetAddress[] toAddresses = { new InternetAddress(to.get(i))};
     	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
     	        msg.setSubject(subject);
     	        msg.setSentDate(new Date());
     	        msg.setText(message);
             }catch(Exception e){
             	e.printStackTrace();
             	return false;
             }
             // sends the e-mail
             try {
     			Transport.send(msg);
     		} catch (MessagingException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     			return false;
     		}
		}
       
        return true;
	}
	
	public static Boolean sendMailHtml(String to, String subject, String body){
		String out = "contacto";
        Properties props = ConfigUtils.getEmailProperties(out);
        user = ConfigUtils.loadProperties(out+"_user");
        password = ConfigUtils.loadProperties(out+"_password");
                
        String message = body;
        
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        };
 
        Session session = Session.getInstance(props, auth);
        
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        try{
        	msg.setContent(message, "text/html; charset=utf-8");
        	msg.setFrom(new InternetAddress(user));
	        InternetAddress[] toAddresses = { new InternetAddress(to)};
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        //msg.setText(message,"text/html; charset=utf-8");
        }catch(Exception e){
        	e.printStackTrace();
        	return false;
        }
        // sends the e-mail
        try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
	}	
}