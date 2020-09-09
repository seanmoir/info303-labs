import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import java.io.IOException;

public class MockMailServer {

	public static void main(String[] args) throws IOException {

		String subject = "CamelTest";
		String body = "Testing 123";

		// create a mock server than can receive (SMTP) and host (IMAP)
		GreenMail greenMail = new GreenMail(ServerSetupTest.SMTP_IMAP);

		// add a user
		greenMail.setUser("test@localhost", "test@localhost", "password");

		// start the server
		greenMail.start();

		System.out.println("IMAP Server Ready on port " + greenMail.getImap().getPort());
		System.out.println("Press Enter to send an E-Mail...");

		// a loop for sending messages
		while (true) {
			System.in.read();  // wait for user to hit enter
			GreenMailUtil.sendTextEmailTest("test@localhost", "test@localhost", subject, body);
			System.out.println(" sent.");
			System.out.print("Press Enter to send an E-Mail...");
		}

	}

}
