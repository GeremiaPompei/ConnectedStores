package com.example.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.util.concurrent.*;
import java.lang.Exception;
import java.net.URI;

public class ServerService implements Runnable {
    private String senderAddress;
    
    public ServerService(String address) {
        this.senderAddress = address;
    }
    
    private HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.example.server");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(this.senderAddress), rc);
    }
    
    private void start() {
        final HttpServer server;
        try {
            server = this.startServer();
            System.out.println("Server started: "+this.senderAddress);
            System.in.read();
            //server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        this.start();
    }
}

