package enviar_email.envia_email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	String username = "jcjavaweb@gmail.com";
	String password = "jlvg vikv almj gtzd";
	
	@Test
	public void send_email() throws AddressException {
		Properties properties = new Properties();
		
		try {
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			
			Address[] toUser = InternetAddress.parse("jcmedeiros04@gmail.com, jcjavaweb@gmail.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("E-mail enviado com sucesso!");
			message.setText("HELLO WORLD!!! BIBLICAAAAAL!");
			
			Transport.send(message);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}



















