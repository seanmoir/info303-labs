package replicator;

import com.google.gson.Gson;
import domain.ShoppingItem;
import domain.ShoppingList;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.ArrayList;
import java.util.Collection;

public class ReplicatingClient {
    public static void main(String[] args) throws Exception {
        // create default context
        CamelContext camel = new DefaultCamelContext();

        // register ActiveMQ as the JMS handler
        ActiveMQConnectionFactory activeMqFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        JmsComponent jmsComponent = JmsComponent.jmsComponent(activeMqFactory);
        camel.addComponent("jms", jmsComponent);

        // transfer the entire exchange, or just the body and headers?
        jmsComponent.setTransferExchange(false);

        // trust all classes being used to send serialised domain objects
        activeMqFactory.setTrustAllPackages(true);

        // turn exchange tracing on or off (false is off)
        camel.setTracing(false);

        // enable stream caching so that things like loggers don't consume the messages
        camel.setStreamCaching(true);


        // create message producer
        ProducerTemplate producer = camel.createProducerTemplate();

        // context must be started before we can send messages
        camel.start();

        Collection<ShoppingItem> items = new ArrayList<>();
        items.add(new ShoppingItem("test", "a test item"));
        items.add(new ShoppingItem("test1", "a testing item"));
        ShoppingList sl = new ShoppingList(items);

        Gson gson = new Gson();
        String json = gson.toJson(sl);

        // send a message
        producer.sendBody("jms:queue:json-list", json);
    }
}
