package com.example.client;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import it.mynext.iaf.nettrs.Rec;
import javafx.application.Application;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import java.util.concurrent.Callable;

/**
 * Classe utile a creare oggetti client che permettono di inviare richieste rest.
 *
 * @author geremiapompei
 */
public class ClientService {
    /**
     * Indirizzo del mittente.
     */
    private String senderAddress;

    /**
     * Metodo costruttore.
     *
     * @param address Indirizzo che permette di inizializzare la rispettiva variabile di istanza.
     */
    public ClientService(String address) {
        this.senderAddress = address;
    }

    public boolean postRec(Rec rec, String receiver) {
        final ClientConfig clientConfig = new ClientConfig().connectorProvider(new HttpUrlConnectorProvider());
        SslConfigurator sslConfig = SslConfigurator.newInstance()
                .keyStoreFile("./ssl/myKeyStore.jks")
                .keyStorePassword("password")
                .trustStoreFile("./ssl/myTrustStore.jts")
                .trustStorePassword("password");
        final SSLContext sslContext = sslConfig.createSSLContext();
        Client client = ClientBuilder.newBuilder().withConfig(clientConfig)
                .sslContext(sslContext).build();
        WebTarget target = client.target(receiver).path("api").path("post");
        Response response = target.request().post(Entity.json(rec));
        return response.readEntity(Boolean.class);
    }

    public Rec getRec(String receiver) {
        final ClientConfig clientConfig = new ClientConfig().connectorProvider(new HttpUrlConnectorProvider());
        SslConfigurator sslConfig = SslConfigurator.newInstance()
                .keyStoreFile("./ssl/myKeyStore.jks")
                .keyStorePassword("password")
                .trustStoreFile("./ssl/myTrustStore.jts")
                .trustStorePassword("password");
        final SSLContext sslContext = sslConfig.createSSLContext();
        Client client = ClientBuilder.newBuilder().withConfig(clientConfig)
                .sslContext(sslContext).build();
        WebTarget target = client.target(receiver).path("api").path("get");
        Response response = target.request().get();
        return response.readEntity(Rec.class);
    }
}
