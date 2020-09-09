package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import domain.ShoppingItem;
import domain.ShoppingList;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class RestClientTests {

	Retrofit retrofit = new Retrofit.Builder()
			  .baseUrl("http://localhost:8081/api/")
			  .addConverterFactory(GsonConverterFactory.create())
			  .build();

	IShoppingListService service = retrofit.create(IShoppingListService.class);

	ShoppingItem testItem1 = new ShoppingItem("name1", "desc1");
	ShoppingItem testItem2 = new ShoppingItem("name2", "desc2");
	ShoppingItem testItem3 = new ShoppingItem("name3", "desc3");

	@Before
	public void setUp() throws IOException {
		service.addItem(testItem1).execute();
		service.addItem(testItem2).execute();
	}

	@After
	public void tearDown() throws IOException {
		service.deleteItem(testItem1.getName()).execute();
		service.deleteItem(testItem2.getName()).execute();
		service.deleteItem(testItem3.getName()).execute();
	}

	@Test
	public void testAddItem() throws IOException {
		service.addItem(testItem3).execute();
		ShoppingList items = service.getShoppingList().execute().body();
		assertThat(items.getItems(), hasItem(testItem3));
	}

	@Test
	public void testDeleteItem() throws IOException {
		service.deleteItem(testItem1.getName()).execute();
		ShoppingList items = service.getShoppingList().execute().body();
		assertThat(items.getItems(), not(hasItem(testItem1)));
	}


	@Test
	public void testGetAll() throws IOException {
		ShoppingList items = service.getShoppingList().execute().body();
		assertThat(items.getItems(), hasItem(testItem1));
		assertThat(items.getItems(), hasItem(testItem2));
	}

}
