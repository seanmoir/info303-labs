package rmi;

import domain.ShoppingItem;
import domain.ShoppingList;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmiShoppingListService extends Remote {
	void addItem(ShoppingItem item) throws RemoteException;
	void deleteItem(ShoppingItem item) throws RemoteException;
	ShoppingList getShoppingList() throws RemoteException;
}
