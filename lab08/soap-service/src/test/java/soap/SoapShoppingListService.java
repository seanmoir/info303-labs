
package soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "SoapShoppingListService", targetNamespace = "http://soap/", wsdlLocation = "http://localhost:8082/api/shopping?wsdl")
public class SoapShoppingListService
    extends Service
{

    private final static URL SOAPSHOPPINGLISTSERVICE_WSDL_LOCATION;
    private final static WebServiceException SOAPSHOPPINGLISTSERVICE_EXCEPTION;
    private final static QName SOAPSHOPPINGLISTSERVICE_QNAME = new QName("http://soap/", "SoapShoppingListService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8082/api/shopping?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOAPSHOPPINGLISTSERVICE_WSDL_LOCATION = url;
        SOAPSHOPPINGLISTSERVICE_EXCEPTION = e;
    }

    public SoapShoppingListService() {
        super(__getWsdlLocation(), SOAPSHOPPINGLISTSERVICE_QNAME);
    }

    public SoapShoppingListService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOAPSHOPPINGLISTSERVICE_QNAME, features);
    }

    public SoapShoppingListService(URL wsdlLocation) {
        super(wsdlLocation, SOAPSHOPPINGLISTSERVICE_QNAME);
    }

    public SoapShoppingListService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOAPSHOPPINGLISTSERVICE_QNAME, features);
    }

    public SoapShoppingListService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SoapShoppingListService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ISoapShoppingListService
     */
    @WebEndpoint(name = "ShoppingListPort")
    public ISoapShoppingListService getShoppingListPort() {
        return super.getPort(new QName("http://soap/", "ShoppingListPort"), ISoapShoppingListService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ISoapShoppingListService
     */
    @WebEndpoint(name = "ShoppingListPort")
    public ISoapShoppingListService getShoppingListPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap/", "ShoppingListPort"), ISoapShoppingListService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOAPSHOPPINGLISTSERVICE_EXCEPTION!= null) {
            throw SOAPSHOPPINGLISTSERVICE_EXCEPTION;
        }
        return SOAPSHOPPINGLISTSERVICE_WSDL_LOCATION;
    }

}
