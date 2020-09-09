package chat;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws JMSException {
        /* initial setup */
        // create connection to JMS broker
        Connection connection = new ActiveMQConnectionFactory().createConnection();
        // start delivering and receiving messages
        connection.start();
        // create a JMS session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // create a new topic named "chat"
        Destination destination = session.createTopic("chat");

        /* receiving messages */
        // create a message consumer connected to the "chat" topic
        MessageConsumer consumer = session.createConsumer(destination);
        // add a message listener to process new messages as they arrive
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message msg) {
                TextMessage message = (TextMessage) msg;
                try {
                    System.out.println(message.getText());
                } catch (JMSException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /* sending messages */
        // create a variable for holding the user's nickname
        String username = "Sean";
        // create message producer
        MessageProducer producer = session.createProducer(destination);
        // use a scanner to read messages from the console
        Scanner scanner = new Scanner(System.in);
        // tell user that client is ready
        System.out.println("Ready to chat.");
        // loop forever reading and sending messages
        while (true) {
            System.out.println("Enter a message to send:");
            // create a text message
            TextMessage message = session.createTextMessage();
            // set text for message
            message.setText(username + ": " + scanner.nextLine());
            // send the message
            producer.send(message);
        }
    }
}
