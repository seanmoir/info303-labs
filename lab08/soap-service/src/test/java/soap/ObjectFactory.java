
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the soap package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetShoppingList_QNAME = new QName("http://soap/", "getShoppingList");
    private final static QName _AddItemResponse_QNAME = new QName("http://soap/", "addItemResponse");
    private final static QName _ShoppingItem_QNAME = new QName("http://soap/", "shoppingItem");
    private final static QName _DeleteItemResponse_QNAME = new QName("http://soap/", "deleteItemResponse");
    private final static QName _ShoppingList_QNAME = new QName("http://soap/", "shoppingList");
    private final static QName _AddItem_QNAME = new QName("http://soap/", "addItem");
    private final static QName _DeleteItem_QNAME = new QName("http://soap/", "deleteItem");
    private final static QName _GetShoppingListResponse_QNAME = new QName("http://soap/", "getShoppingListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddItem }
     *
     */
    public AddItem createAddItem() {
        return new AddItem();
    }

    /**
     * Create an instance of {@link ShoppingList }
     *
     */
    public ShoppingList createShoppingList() {
        return new ShoppingList();
    }

    /**
     * Create an instance of {@link GetShoppingListResponse }
     *
     */
    public GetShoppingListResponse createGetShoppingListResponse() {
        return new GetShoppingListResponse();
    }

    /**
     * Create an instance of {@link DeleteItem }
     *
     */
    public DeleteItem createDeleteItem() {
        return new DeleteItem();
    }

    /**
     * Create an instance of {@link ShoppingItem }
     *
     */
    public ShoppingItem createShoppingItem() {
        return new ShoppingItem();
    }

    /**
     * Create an instance of {@link AddItemResponse }
     *
     */
    public AddItemResponse createAddItemResponse() {
        return new AddItemResponse();
    }

    /**
     * Create an instance of {@link GetShoppingList }
     *
     */
    public GetShoppingList createGetShoppingList() {
        return new GetShoppingList();
    }

    /**
     * Create an instance of {@link DeleteItemResponse }
     *
     */
    public DeleteItemResponse createDeleteItemResponse() {
        return new DeleteItemResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShoppingList }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "getShoppingList")
    public JAXBElement<GetShoppingList> createGetShoppingList(GetShoppingList value) {
        return new JAXBElement<GetShoppingList>(_GetShoppingList_QNAME, GetShoppingList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddItemResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addItemResponse")
    public JAXBElement<AddItemResponse> createAddItemResponse(AddItemResponse value) {
        return new JAXBElement<AddItemResponse>(_AddItemResponse_QNAME, AddItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShoppingItem }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "shoppingItem")
    public JAXBElement<ShoppingItem> createShoppingItem(ShoppingItem value) {
        return new JAXBElement<ShoppingItem>(_ShoppingItem_QNAME, ShoppingItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteItemResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "deleteItemResponse")
    public JAXBElement<DeleteItemResponse> createDeleteItemResponse(DeleteItemResponse value) {
        return new JAXBElement<DeleteItemResponse>(_DeleteItemResponse_QNAME, DeleteItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShoppingList }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "shoppingList")
    public JAXBElement<ShoppingList> createShoppingList(ShoppingList value) {
        return new JAXBElement<ShoppingList>(_ShoppingList_QNAME, ShoppingList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddItem }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addItem")
    public JAXBElement<AddItem> createAddItem(AddItem value) {
        return new JAXBElement<AddItem>(_AddItem_QNAME, AddItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteItem }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "deleteItem")
    public JAXBElement<DeleteItem> createDeleteItem(DeleteItem value) {
        return new JAXBElement<DeleteItem>(_DeleteItem_QNAME, DeleteItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShoppingListResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://soap/", name = "getShoppingListResponse")
    public JAXBElement<GetShoppingListResponse> createGetShoppingListResponse(GetShoppingListResponse value) {
        return new JAXBElement<GetShoppingListResponse>(_GetShoppingListResponse_QNAME, GetShoppingListResponse.class, null, value);
    }

}
