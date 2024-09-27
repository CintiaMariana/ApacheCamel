package com.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class StartApp {
	public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("jetty:http://0.0.0.0:8080/send")
                    .process(exchange -> {
                        String message = exchange.getIn().getBody(String.class);
                        System.out.println("Mensagem Recebida: " + message);
                        exchange.getOut().setBody("Mensagem Recebida: " + message);
                    });
            }
        });

        context.start();
        System.out.println("Servidor Camel foi iniciado");
        Thread.sleep(1000000000);
        context.stop();
    }
}