package enviar_email.envia_email;

import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Test
	public void send_email() throws Exception {
		StringBuilder stringBuilderTextEmail = new StringBuilder();
		
		stringBuilderTextEmail.append("Parabéns, <br/></br>");
		stringBuilderTextEmail.append("Aqui está o seu certificado de conclusão do curso	! <br/></br>");
		stringBuilderTextEmail.append("<span style=\"font-size:8px\">Ass.: jc dev Java</span>");
		
		ObjectSendEmail send_email = new ObjectSendEmail(
				"jcmedeiros04@gmail.com, jcjavaweb@gmail.com",
				"jc-dev",
				"Código de confirmação para login" ,
				stringBuilderTextEmail.toString());
		
		send_email.sendEmailAndAttachmentFile(true);
	}
}



















