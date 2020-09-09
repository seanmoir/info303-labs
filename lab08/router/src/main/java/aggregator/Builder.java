package aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;
import org.apache.camel.util.toolbox.AggregationStrategies;

import java.util.ArrayList;

public class Builder extends RouteBuilder {
    /**
     * <b>Called on initialization to build the routes using the fluent builder syntax.</b>
     * <p/>
     * This is a central method for RouteBuilder implementations to implement
     * the routes using the Java fluent builder syntax.
     *
     * @throws Exception can be thrown during configuration
     */
    @Override
    public void configure() throws Exception {
        from("timer://timer?period=5000")
                .multicast()
                    .to("jms:queue:get-rmi-list", "jms:queue:get-soap-list");

        from("jms:queue:get-rmi-list")
                .to("rmi://localhost:1099/shopping?remoteInterfaces=rmi.IRmiShoppingListService&method=getShoppingList")
                .to("jms:queue:shopping-lists");

        from("jms:queue:get-soap-list")
                .to("cxf:http://localhost:8082/api/shopping?serviceClass=soap.ISoapShoppingListService&defaultOperationName=getShoppingList")
                .split().body()
                .to("jms:queue:shoppping-lists");

        from("jms:queue:shopping-lists")
                .split().simple("${body.getItems}")
                .to("jms:queue:shopping-items");

        from("jms:queue:shopping-items")
                .setProperty("hash", simple("${body.name}"))
                .idempotentConsumer(exchangeProperty("hash"), MemoryIdempotentRepository.memoryIdempotentRepository(100))
                .skipDuplicate(true)
                .marshal().json(JsonLibrary.Gson)
                .multicast()
                .to("jms:queue:items-json", "jms:queue:chunk");

        from("jms:queue:items-json")
                .removeHeader("*")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost:8081/api/shopping");


        from("jms:queue:chunk")
                .aggregate(constant(true), AggregationStrategies.flexible().accumulateInCollection(ArrayList.class)).completionSize(4)
                .to("jms:queue:chunk-aggregated");

        from("imap://localhost?username=test@localhost"
                + "&port=3143"
                + "&password=password"
                + "&consumer.delay=5000"
                + "&searchTerm.subject=303email")
                .log("Found new E-Mail: ${body}")
                .to("jms:queue:items-json");
    }
}
