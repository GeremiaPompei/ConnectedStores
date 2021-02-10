package com.example.controller;

import com.example.MyDomain;
import com.example.client.ClientService;
import com.example.server.ServerService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    private static Controller controller;

    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }

    private Controller() {
    }

    private ServerService server;
    private ClientService client;

    public void init() {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            server = exec.submit(() -> new ServerService(MyDomain.getInstance().getDomain())).get();
            client = exec.submit(() -> new ClientService(MyDomain.getInstance().getDomain())).get();
            exec.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerService getServer() {
        return server;
    }

    public ClientService getClient() {
        return client;
    }
}
