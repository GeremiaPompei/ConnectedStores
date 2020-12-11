/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import com.example.model.*;
import com.example.view.*;

import java.util.concurrent.Callable;

/**
 *
 * @author geremiapompei
 */
public class ClientService implements Callable<ClientService> {
    private String senderAddress;
    
    public ClientService(String address) {
        this.senderAddress = address;
    }
    
    public RestResponse postRequest(RestRequest restRequest){
        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(restRequest.getReceiver()).path("api");
        Response response = target.request().post(Entity.json(restRequest));
        return response.readEntity(RestResponse.class);
    }
    
    public ClientService call() {
        return this;
    }
}
