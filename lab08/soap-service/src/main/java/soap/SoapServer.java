package soap;

import javax.xml.ws.Endpoint;

public class SoapServer {

	public static void main(String[] args) throws Exception {
		ISoapShoppingListService service = new ShoppingListServiceImpl();

		System.out.println("\nSOAP Service starting on http://localhost:8082/api/shopping");

		Endpoint endpoint = Endpoint.publish("http://localhost:8082/api/shopping", service);

		System.out.println("\nReady on port 8082.  Press Enter to shutdown.");
		System.in.read();
		endpoint.stop();
	}
}
