package com.example.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

/**
 * Classe che ha la responsabilità di costruire oggetti server. Tali oggetti permettono di far partire e far terminare
 * l'esecuzione di un server.
 *
 * @author geremiapompei
 */
public class ServerService {

    /**
     * Riferimento al server inizializzato tramite start.
     */
    private HttpServer server;

    /**
     * Metodo costruttore.
     *
     * @param address Indirizzo per l'inizializzazione dell'indirizzo del server.
     */
    public ServerService(String address) {
        this.start(address);
    }

    /**
     * Metodo utile a far partire l'esecuzione di un server.
     */
    private void start(String senderAddress) {
        SSLContextConfigurator sslCtxConf = new SSLContextConfigurator();
        sslCtxConf.setKeyStoreFile("./ssl/myKeyStore.jks");
        sslCtxConf.setKeyStorePass("password");
        sslCtxConf.setTrustStoreFile("./ssl/myTrustStore.jts");
        sslCtxConf.setTrustStorePass("password");
        final ResourceConfig rc = new ResourceConfig().packages("com.example.server");
        this.server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(senderAddress),
                rc,
                true,
                new SSLEngineConfigurator(sslCtxConf, false, false, false));
        System.out.println("Server started: " + senderAddress);
    }

    /**
     * Metodo utile a far terminare l'esecuzione del server.
     */
    public void stop() {
        this.server.stop();
    }
}

