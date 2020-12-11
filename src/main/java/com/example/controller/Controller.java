package com.example.controller;

import com.example.client.ClientService;
import com.example.model.LocalStore;
import com.example.model.RestRequest;
import com.example.model.RestResponse;
import com.example.server.ServerService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    private ServerService server;
    private ClientService client;
    private LocalStore localStore = LocalStore.getIstance();
    private String address;

    public void init(String address) throws ExecutionException, InterruptedException {
        this.address = address;
        ExecutorService exec = Executors.newCachedThreadPool();
        this.server = exec.submit(new ServerService(address)).get();
        this.client = exec.submit(new ClientService(address)).get();
        exec.shutdown();
    }

    public RestResponse doRequest(RestRequest request) {
        return this.client.postRequest(request);
    }

    public void stopServer() {
        this.server.close();
    }

    public LocalStore getLocalStore() {
        return localStore;
    }

    public String getAddress() {
        return address;
    }
}
