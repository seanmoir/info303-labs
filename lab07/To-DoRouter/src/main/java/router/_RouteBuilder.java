package router;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class _RouteBuilder extends RouteBuilder {

    @Override
    public void configure()  {

        from("jms:queue:q1")
                .setProperty("prop1").constant("ABC")
                .setHeader("head1").constant("XYZ")
                .log("${body}")
                .to("jms:queue:sort")
                .to("jms:queue:q2");
        from("jms:queue:q2")
                .log("body: ${body}")
                .log("prop1: ${exchangeProperty.prop1}")
                .log("head1: ${header.head1}")
                .filter().simple("${body.urgent} == 'true'")
                .to("jms:queue:q3");
        from("jms:queue:sort")
                .choice()
                    .when().simple("${body.urgent} == 'true'")
                        .to("jms:queue:urgent")
                    .otherwise()
                        .to("jms:queue:not-urgent");
        from("jms:queue:urgent")
                // convert to JSON using marshal method
                .marshal().json(JsonLibrary.Gson)

                // ensure the message body is a string
                .convertBodyTo(String.class)

                // send to a queue that expects JSON
                .to("jms:queue:urgent-json");
        from("jms:queue:not-urgent")
                // convert to JSON using marshal method
                .marshal().json(JsonLibrary.Gson)

                // ensure the message body is a string
                .convertBodyTo(String.class)

                // send to a queue that expects JSON
                .to("jms:queue:not-urgent-json");

        //exposing router through web servlet
        from("jetty:http://localhost:9000/messages")
                .setExchangePattern(ExchangePattern.InOnly)
                .to("jms:queue:messages");

    }

}