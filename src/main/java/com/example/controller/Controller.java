package com.example.controller;

import com.example.client.ClientService;
import com.example.model.LocalStore;
import com.example.model.RestRequest;
import com.example.model.RestResponse;
import com.example.server.ServerService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe utile per creare oggetti controller dell'MVC.
 *
 * @author geremiapompei
 */
public class Controller {
    /**
     * Server del controller.
     */
    private ServerService server;
    /**
     * Client del controller.
     */
    private ClientService client;
    /**
     * Store locale del controller.
     */
    private LocalStore localStore = LocalStore.getIstance();
    /**
     * Indirizzo del mittente del controller.
     */
    private String senderAddress = "";

    /**
     * Metodo utile ad inizializzare il controller.
     *
     * @throws ExecutionException   Eccezione di esecuzione.
     * @throws InterruptedException Eccezione di conclusione.
     */
    public void init() throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        this.server = exec.submit(new ServerService(this.senderAddress)).get();
        this.client = exec.submit(new ClientService(this.senderAddress)).get();
        exec.shutdown();
    }

    /**
     * Metodo utile per dare una risposta rest presa in input una richiesta rest.
     *
     * @param request Richiesta rest inviata.
     * @return Risposta rest ricevuta.
     */
    public RestResponse doRequest(RestRequest request) {
        return this.client.postRequest(request);
    }

    /**
     * Metodo utile per fermare il server.
     */
    public void stopServer() {
        this.server.close();
    }

    /**
     * Getter dello store locale.
     *
     * @return Store locale.
     */
    public LocalStore getLocalStore() {
        return localStore;
    }

    /**
     * Getter dell'indirizzo del mittente.
     *
     * @return Indirizzo del mittente.
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * Setter dell'indirizzo del mittente.
     *
     * @param senderAddress Indirizzo del mittente sostituto.
     */
    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
}
