package enviar_email.envia_email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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

	public void sendEmailAndAttachmentFile(Boolean messageContentHtml) {
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
			
			/*PARTE 1 do e-mail que é o texto e a descrição do e-mail*/
			MimeBodyPart bodyEmail = new MimeBodyPart();
			
			if(messageContentHtml) {
				bodyEmail.setContent(textEmail, "text/html; charset=UTF-8");
			} else {
				bodyEmail.setText(textEmail);
			}	
			
			/*PARTE 2 do e-mail são os anexos em pdf*/
			MimeBodyPart attachmentEmail = new MimeBodyPart();
			
			/*Onde é passado o simulador de PDF passamos o arquivo gravado no database ou local*/
			attachmentEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simulateSendPDF(), "application/pdf")));
			attachmentEmail.setFileName("Javamail.pdf");
			
			/*Aqui juntamos o conteudo do email -> bodyEmail + attachmentEmail*/
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyEmail);
			multipart.addBodyPart(attachmentEmail);
			
			message.setContent(multipart);
			
			Transport.send(message);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Esse metodo utilizando o iText simula um PDF ou qualquer arquivo que possa ser enviado pelo email.
	 * Você pode pegar um arquivo do seu banco de dados base64, byte[], StreamFile.
	 * 
	 * Retorna um PDF em branco com o texto do paragrafo escrito dentro do metodo.
	 * */
	private FileInputStream simulateSendPDF() throws Exception {
		Document document = new Document();
		File file = new File("AnexoJava.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Esse é o conteúdo do PDF, HELLO WORLD"));
		document.close();
		
		return new FileInputStream(file);
	}
}










