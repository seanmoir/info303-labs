
package soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getShoppingListResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="getShoppingListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://soap/}shoppingList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getShoppingListResponse", propOrder = {
    "_return"
})
public class GetShoppingListResponse {

    @XmlElement(name = "return")
    protected ShoppingList _return;

    /**
     * Gets the value of the return property.
     *
     * @return
     *     possible object is
     *     {@link ShoppingList }
     *
     */
    public ShoppingList getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value
     *     allowed object is
     *     {@link ShoppingList }
     *
     */
    public void setReturn(ShoppingList value) {
        this._return = value;
    }

}
