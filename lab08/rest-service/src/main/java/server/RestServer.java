package server;

import dao.ShoppingDAO;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.json.Gzon;
import resource.ShoppingItemResource;
import resource.ShoppingListResource;

public class RestServer extends Jooby {

	public RestServer() {
		ShoppingDAO dao = new ShoppingDAO();

		use(new Gzon());
		use(new ShoppingItemResource(dao));
		use(new ShoppingListResource(dao));
		use(new ApiTool().swagger());
	}

	public static void main(String[] args) throws Exception {

		RestServer server = new RestServer();

		server.port(8081);

		CompletableFuture.runAsync(() -> {
			server.start();
		});

		server.onStarted(() -> {
			System.out.println("Press Enter to stop service.");
		});

		System.in.read();
		System.exit(0);
	}

}
