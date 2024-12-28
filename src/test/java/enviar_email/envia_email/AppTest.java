package enviar_email.envia_email;

import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {
	String username = "jcjavaweb@gmail.com";
	String password = "jlvg vikv almj gtzd";
	
	@Test
	public void send_email() throws Exception {
		ObjectSendEmail send_email = new ObjectSendEmail(
				"jcmedeiros04@gmail.com, jcjavaweb@gmail.com",
				"jc-dev",
				"E-mail received with success using Java!",
				"You have a new message in your email!");
		
		send_email.sendEmail();
	}
}



















