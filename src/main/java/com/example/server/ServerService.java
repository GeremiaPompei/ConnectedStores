package com.example.server;

import com.example.view.ConsolePrinter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.concurrent.Callable;
import java.net.URI;

/**
 * Classe che ha la responsabilità di costruire oggetti server. Tali oggetti permettono di far partire e far terminare
 * l'esecuzione di un server.
 *
 * @author geremiapompei
 */
public class ServerService implements Callable<ServerService> {
    /**
     * Variabile che tiene traccia del server del Sender.
     */
    private String senderAddress;
    /**
     * Variabile che tiene traccia dell'istanza del server.
     */
    private HttpServer server;

    /**
     * Metodo costruttore.
     *
     * @param address Indirizzo per l'inizializzazione dell'indirizzo del server.
     */
    public ServerService(String address) {
        this.senderAddress = address;
    }

    /**
     * Metodo utile a far partire l'esecuzione di un server.
     */
    private void start() {
        SSLContextConfigurator sslCtxConf = new SSLContextConfigurator();
        sslCtxConf.setKeyStoreFile("./ssl/mykeystoreServer.jks");
        sslCtxConf.setKeyStorePass("password");
        sslCtxConf.setTrustStoreFile("./ssl/myTrustStoreServer.jtr");
        sslCtxConf.setTrustStorePass("password");
        final ResourceConfig rc = new ResourceConfig().packages("com.example.server");
        this.server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(this.senderAddress),
                rc,
                true,
                new SSLEngineConfigurator(sslCtxConf, false, false, false));
        ConsolePrinter.printServer("Server started: " + this.senderAddress);
    }

    /**
     * Metodo utile a far terminare l'esecuzione di un server.
     */
    public void close() {
        this.server.stop();
    }

    /**
     * Metodo sovrascritto utile per far diventare tale server eseguibile in un thread e parallelizzarlo rispetto ad
     * altre unità computazionali.
     *
     * @return Tale server per tenerne traccia.
     */
    @Override
    public ServerService call() {
        this.start();
        return this;
    }
}

