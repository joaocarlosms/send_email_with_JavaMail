package enviar_email.envia_email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ObjectSendEmail {
	private String username = "jcjavaweb@gmail.com";
	private String password = "jlvg vikv almj gtzd";
	private String listRecipients;
	private String nameSender;
	private String subjectEmail;
	private String textEmail;
	
	public ObjectSendEmail() { 
		this.listRecipients = "jcmedeiros04@gmail.com, jcjavaweb@gmail.com";
		this.nameSender = "";
		this.subjectEmail = "";
		this.textEmail = "";
	}
	
	public ObjectSendEmail(String listRecipients, String nameSender, String subjectEmail, String textEmail) {
		this.listRecipients = listRecipients;
		this.nameSender = nameSender;
		this.subjectEmail = subjectEmail;
		this.textEmail = textEmail;
	}

	public void sendEmail(Boolean messageContentHtml) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
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
			
			Address[] toUser = InternetAddress.parse(listRecipients);
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username, nameSender));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(subjectEmail);
			
			if(messageContentHtml) {
				message.setContent(textEmail, "text/html; charset=UTF-8");
			} else {
				message.setText(textEmail);
			}	
			
			Transport.send(message);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
