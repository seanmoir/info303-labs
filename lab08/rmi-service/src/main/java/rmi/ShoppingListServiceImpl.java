package rmi;

import dao.ShoppingDAO;
import domain.ShoppingItem;
import domain.ShoppingList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShoppingListServiceImpl extends UnicastRemoteObject implements IRmiShoppingListService {

	private final ShoppingDAO dao = new ShoppingDAO();

	public ShoppingListServiceImpl() throws RemoteException {
		dao.addItem(new ShoppingItem("Bananas", "A bunch of bananas"));
		dao.addItem(new ShoppingItem("Apples", "A bag of apples"));
		dao.addItem(new ShoppingItem("Coffee", "Plunger grind"));
		dao.addItem(new ShoppingItem("Cheese", "Tasty"));
	}

	@Override
	public void addItem(ShoppingItem item) throws RemoteException {
		System.out.println("\n ** addItem called on RMI service **");
		System.out.println(item);
		dao.addItem(item);
	}

	@Override
	public void deleteItem(ShoppingItem item) throws RemoteException {
		System.out.println("\n ** deleteItem called on RMI service **");
		System.out.println(item);
		dao.delete(item);
	}

	@Override
	public ShoppingList getShoppingList() throws RemoteException {
		System.out.println("\n ** getShoppingList called on RMI service **");
		return dao.getList();
	}

}
