import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

public class _RouteBuilder extends RouteBuilder {

    @Override
    public void configure()  {

        // routes go here
        //from("jms:queue:in")
        //        .multicast()
        //        .to("jms:queue:out1", "jms:queue:out2");

        from("jms:queue:q1")
                .setProperty("prop1").constant("ABC")
                .setHeader("head1").constant("XYZ")
                .to("jms:queue:q2");
        from("jms:queue:q2")
                .log("body: ${body}")
                .log("prop1: ${exchangeProperty.prop1}")
                .log("head1: ${header.head1}")
                .to("jms:queue:q3");

        //exposing router through web servlet
        from("jetty:http://localhost:9000/messages")
                .setExchangePattern(ExchangePattern.InOnly)
                .to("jms:queue:messages");

    }

}