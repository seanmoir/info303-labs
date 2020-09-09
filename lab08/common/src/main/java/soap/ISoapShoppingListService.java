package soap;

import domain.ShoppingItem;
import domain.ShoppingList;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ISoapShoppingListService {
	void addItem(@WebParam(name = "item") ShoppingItem item);
	void deleteItem(@WebParam(name = "item") ShoppingItem item);
	ShoppingList getShoppingList();
}
