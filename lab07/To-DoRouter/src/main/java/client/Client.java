package client;

import domain.ToDoItem;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class Client {
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
        camel.setStreamCaching(false);


        // create message producer
        ProducerTemplate producer = camel.createProducerTemplate();

        // context must be started before we can send messages
        camel.start();


        ToDoItem a = new ToDoItem("a", "an object named a", false);
        ToDoItem b = new ToDoItem("b", "an object named b", true);

        producer.sendBody("jms:queue:q1", a);
        producer.sendBody("jms:queue:q1", b);
    }
}
