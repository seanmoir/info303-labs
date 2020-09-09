package replicator;

import domain.ShoppingItem;
import domain.ShoppingList;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class ReplicatingBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:json-list")
                .unmarshal().json(JsonLibrary.Gson, ShoppingList.class)
                .to("jms:queue:list");

        from("jms:queue:list")
                .split().simple("${body.getItems}")
                .multicast()
                    .to("jms:queue:rmi", "jms:queue:soap", "jms:queue:rest");

        from("jms:queue:rest")
                .removeHeaders("*") // remove headers to stop them being sent to the service
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .marshal()
                .json(JsonLibrary.Gson)
                .to("http://localhost:8081/api/shopping");
                //.to("jms:queue:http-response");

        from("jms:queue:rmi")
                .to("rmi://localhost:1099/shopping?remoteInterfaces=rmi.IRmiShoppingListService&method=addItem");

        from("jms:queue:soap")
                .to("cxf:http://localhost:8082/api/shopping?serviceClass=soap.ISoapShoppingListService&defaultOperationName=addItem");


    }
}
