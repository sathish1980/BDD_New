package Javaprogramstest;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class emailcheck {



    public static void main(String args[]) {
    	 String to = "sathish.kumar15@gds.ey.in";//change accordingly  
         String from = "sathish.kumar15@gds.ey.in";//change accordingly  
         String host = "10.168.5.241";//or IP address  
     
        //Get the session object  
         Properties properties = System.getProperties();  
         properties.setProperty("mail.smtp.host", host);  
         Session session = Session.getDefaultInstance(properties);  
     
        //compose the message  
         try{  
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(from));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
            message.setSubject("Ping");  
            message.setText("Hello, this is example of sending email  ");  
     
            // Send message  
            Transport.send(message);  
            System.out.println("message sent successfully....");  
    }
         catch(Exception e)
         {
        	 System.out.println(e);
        	 
         }

}
}
