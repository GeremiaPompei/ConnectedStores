package com.example.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.lang.Exception;
import java.net.URI;

public class Server {
    
    public HttpServer startServer(String address) {
        final ResourceConfig rc = new ResourceConfig().packages("com.example.server");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(address), rc);
    }
    
    public void start(String address) {
        final HttpServer server;
        try {
            server = startServer(address);
            System.out.println(String.format("Jersey app started.\nHit enter to stop it...", address));
            System.in.read();
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

