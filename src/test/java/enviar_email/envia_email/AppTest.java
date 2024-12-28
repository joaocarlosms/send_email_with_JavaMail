package enviar_email.envia_email;

import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Test
	public void send_email() throws Exception {
		StringBuilder stringBuilderTextEmail = new StringBuilder();
		
		stringBuilderTextEmail.append("Olá, <br/></br>");
		stringBuilderTextEmail.append("Aqui está o seu código de confirmação para realizar o login:<br/></br>");
		stringBuilderTextEmail.append("<b>123456</b><br/></br>");
		stringBuilderTextEmail.append("<span style=\"font-size:8px\">Ass.: jc dev Java</span>");
		
		ObjectSendEmail send_email = new ObjectSendEmail(
				"jcmedeiros04@gmail.com, jcjavaweb@gmail.com",
				"jc-dev",
				"Código de confirmação para login",
				stringBuilderTextEmail.toString());
		
		send_email.sendEmail(true);
	}
}



















