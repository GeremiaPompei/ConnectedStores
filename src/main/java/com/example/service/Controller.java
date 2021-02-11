package com.example.service;

import com.example.client.ClientService;
import com.example.server.ServerService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Servizio singleton utile a far partire le istanze client e server e gestirle.
 */
public class Controller {

    /**
     * Unica istanza di tale classe.
     */
    private static Controller controller;

    /**
     * Metodo utile a recuperare l'istanza del controller.
     *
     * @return Istanza del controller.
     */
    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }

    /**
     * Metodo costruttore.
     */
    private Controller() {
    }

    /**
     * Istanza del server.
     */
    private ServerService server;

    /**
     * Istanza del client.
     */
    private ClientService client;

    /**
     * Metodo utile a far partire in parallelo l'esecuzione di client e server.
     */
    public void init() {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            server = exec.submit(() -> new ServerService(MyDomain.getInstance().getDomain())).get();
            client = exec.submit(() -> new ClientService()).get();
            exec.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo utile per gestire il server.
     *
     * @return Istanza del server.
     */
    public ServerService getServer() {
        return server;
    }

    /**
     * Metodo utile per gestire il client.
     *
     * @return Istanza del client.
     */
    public ClientService getClient() {
        return client;
    }
}
