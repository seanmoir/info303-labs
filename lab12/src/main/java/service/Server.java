package service;

import dao.CatalogueDAO;
import graphql.InventoryWiring;
import io.jooby.Jooby;
import io.jooby.graphql.GraphQLModule;
import io.jooby.graphql.GraphiQLModule;
import io.jooby.json.GsonModule;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Server extends Jooby {

	public Server() {
		CatalogueDAO dao = new CatalogueDAO();

		install(new GsonModule());
		install(new GraphQLModule(new InventoryWiring(dao).getWiring()));
		install(new GraphiQLModule());
		get("/", (ctx) -> ctx.sendRedirect("/graphql"));
	}

	public static void main(String[] args) throws IOException {

		Server server = new Server();

		CompletableFuture.runAsync(() -> {
			server.start();
		});

		server.onStarted(() -> {
			System.out.println("\nServer ready. Press Enter to stop service.");
		});

		System.in.read();
		server.stop();
		System.exit(0);
	}

}
