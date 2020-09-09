package client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import soap.ShoppingItem;
import soap.ShoppingList;
import soap.ISoapShoppingListService;
import soap.SoapShoppingListService;

public class SoapClientTest {

	ISoapShoppingListService service;

	ShoppingItem testItem1;
	ShoppingItem testItem2;
	ShoppingItem testItem3;

	@Before
	public void setUp() throws RemoteException, NotBoundException {
		service = new SoapShoppingListService().getShoppingListPort();

		testItem1 = new ShoppingItem();
		testItem1.setName("name1");
		testItem1.setDescription("desc1");

		testItem2 = new ShoppingItem();
		testItem2.setName("name2");
		testItem2.setDescription("desc2");

		testItem3 = new ShoppingItem();
		testItem3.setName("name3");
		testItem3.setDescription("desc3");

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
		ShoppingList list = service.getShoppingList();

		List<ShoppingItem> items = list.getItems();

		boolean found = false;

		for (ShoppingItem item : items) {
			if (item.getName().equals(testItem3.getName())) {
				found = true;
			}
		}

		assertThat(found, equalTo(true));
	}

	@Test
	public void testDeleteItem() throws IOException {
		service.deleteItem(testItem1);
		ShoppingList list = service.getShoppingList();

		// the generated domain classes do not have hashCode/equals methods so we are forced to search the collection
		List<ShoppingItem> items = list.getItems();
		for (ShoppingItem item : items) {
			if (item.getName().equals(testItem1.getName())) {
				fail();
			}
		}
	}

	@Test
	public void testGetAll() throws IOException {
		ShoppingList list = service.getShoppingList();

		int found = 0;

		List<ShoppingItem> items = list.getItems();

		for (ShoppingItem item : items) {

			if (item.getName().equals(testItem1.getName())
					  || item.getName().equals(testItem2.getName())) {
				found++;
			}
		}

		assertThat(found, equalTo(2));
	}

}
