package soap;

import dao.ShoppingDAO;
import domain.ShoppingItem;
import domain.ShoppingList;
import javax.jws.WebService;

@WebService(endpointInterface = "soap.ISoapShoppingListService",
		serviceName = "SoapShoppingListService",
		portName = "ShoppingListPort")
public class ShoppingListServiceImpl implements ISoapShoppingListService {

	private final ShoppingDAO dao = new ShoppingDAO();

	public ShoppingListServiceImpl() {
		dao.addItem(new ShoppingItem("Eggs", "A dozen size 7"));
		dao.addItem(new ShoppingItem("Orange juice", "3 litre bottle"));
		dao.addItem(new ShoppingItem("Milk", "2 litre, light blue"));
		dao.addItem(new ShoppingItem("Baby spinach", "Fresh salad greens"));
	}

	@Override
	public void addItem(ShoppingItem item) {
		System.out.println("\n ** addItem called on SOAP service **");
		System.out.println(item);
		dao.addItem(item);
	}

	@Override
	public void deleteItem(ShoppingItem item) {
		System.out.println("\n ** deleteItem called on SOAP service **");
		System.out.println(item);
		dao.delete(item);
	}

	@Override
	public ShoppingList getShoppingList() {
		System.out.println("\n ** getShoppingList called on SOAP service **");
		return dao.getList();
	}

}
