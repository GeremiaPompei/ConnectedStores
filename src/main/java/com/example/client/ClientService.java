package com.example.client;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.model.RecEntity;
import it.mynext.iaf.nettrs.Rec;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

/**
 * Classe utile a creare oggetti client che permettono di inviare richieste rest.
 *
 * @author geremiapompei
 */
public class ClientService {

    public boolean postRec(RecEntity rec, String receiver) {
        Client client = configClient();
        WebTarget target = client.target(receiver).path("api").path("post");
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(rec, MediaType.APPLICATION_JSON));
        return response.readEntity(Boolean.class);
    }

    public Rec getRec(String receiver) {
        Client client = configClient();
        WebTarget target = client.target(receiver).path("api").path("get");
        Response response = target.request().get();
        return response.readEntity(Rec.class);
    }

    private Client configClient() {
        final ClientConfig clientConfig = new ClientConfig().connectorProvider(new HttpUrlConnectorProvider());
        SslConfigurator sslConfig = SslConfigurator.newInstance()
                .keyStoreFile("./ssl/myKeyStore.jks")
                .keyStorePassword("password")
                .trustStoreFile("./ssl/myTrustStore.jts")
                .trustStorePassword("password");
        final SSLContext sslContext = sslConfig.createSSLContext();
        return ClientBuilder.newBuilder().withConfig(clientConfig).sslContext(sslContext).build();
    }
}
