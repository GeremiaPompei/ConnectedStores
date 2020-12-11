package com.example.server;

import com.example.view.ConsolePrinter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ProcessingException;
import java.util.concurrent.*;
import java.lang.Exception;
import java.net.URI;

public class ServerService implements Callable<ServerService> {
    private String senderAddress;
    private HttpServer server;
    
    public ServerService(String address) {
        this.senderAddress = address;
    }
    
    private HttpServer startServer() throws IllegalArgumentException {
        final ResourceConfig rc = new ResourceConfig().packages("com.example.server");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(this.senderAddress), rc);
    }
    
    private void start() {
        this.server = this.startServer();
        ConsolePrinter.printServer("Server started: " + this.senderAddress);
    }

    public void close() {
        this.server.stop();
    }
    
    public ServerService call() throws IllegalArgumentException {
        this.start();
        return this;
    }
}

