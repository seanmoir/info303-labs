package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {

	public static void main(String[] args) throws Exception {

		IRmiShoppingListService service = new ShoppingListServiceImpl();

		System.out.println("\nRMI Service creating registry on port 1099.");

		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("shopping", service);

		System.out.println("\nReady.  Press Enter to Shutdown.");
		System.in.read();
		System.exit(0);
	}
}
