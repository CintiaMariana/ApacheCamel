package com.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.RuntimeCamelException;

import java.io.IOException;
import java.net.ServerSocket;

public class StartApp {
    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();
        int port = 8080;

 
        if (!isPortAvailable(port)) {
            System.out.println("Erro: A porta " + port + " já está em uso. Não foi possível iniciar o servidor.");
            return;
        }

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("jetty:http://0.0.0.0:" + port + "/send")
                        .process(exchange -> {
                            String message = exchange.getIn().getBody(String.class);
                            System.out.println("Mensagem Recebida: " + message);
                            exchange.getOut().setBody("Mensagem Recebida: " + message);
                        });
                }
            });

            context.start();
            System.out.println("Servidor Camel foi iniciado na porta " + port);
            Thread.sleep(1000000000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                context.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
