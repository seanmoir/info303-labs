package dao;

import domain.ShoppingItem;
import domain.ShoppingList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingDAO {

	private static final Map<String, ShoppingItem> items = new HashMap<>();

	public ShoppingList getList() {
		return new ShoppingList(items.values());
	}

	public void addItem(ShoppingItem item) {
		items.put(item.getName(), item);
	}

	public ShoppingItem getByName(String itemName) {
		return items.get(itemName);
	}

	public void delete(ShoppingItem item) {
		items.remove(item.getName());
	}

	public boolean exists(String itemName) {
		return items.containsKey(itemName);
	}
}
