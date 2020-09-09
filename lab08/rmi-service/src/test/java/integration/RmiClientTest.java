package integration;

import domain.ShoppingItem;
import domain.ShoppingList;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import rmi.IRmiShoppingListService;

public class RmiClientTest {

	IRmiShoppingListService service;

	ShoppingItem testItem1 = new ShoppingItem("name1", "desc1");
	ShoppingItem testItem2 = new ShoppingItem("name2", "desc2");
	ShoppingItem testItem3 = new ShoppingItem("name3", "desc3");

	@Before
	public void setUp() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		service = (IRmiShoppingListService) registry.lookup("shopping");

		service.addItem(testItem1);
		service.addItem(testItem2);
	}

	@After
	public void tearDown() throws IOException {
		service.deleteItem(testItem1);
		service.deleteItem(testItem2);
		service.deleteItem(testItem3);
	}

	@Test
	public void testAddItem() throws IOException {
		service.addItem(testItem3);
		ShoppingList items = service.getShoppingList();
		assertThat(items.getItems(), hasItem(testItem3));
	}

	@Test
	public void testDeleteItem() throws IOException {
		service.deleteItem(testItem1);
		ShoppingList items = service.getShoppingList();
		assertThat(items.getItems(), not(hasItem(testItem1)));
	}


	@Test
	public void testGetAll() throws IOException {
		ShoppingList items = service.getShoppingList();
		assertThat(items.getItems(), hasItem(testItem1));
		assertThat(items.getItems(), hasItem(testItem2));
	}

}
