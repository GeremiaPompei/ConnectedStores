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
import javax.ws.rs.core.MediaType;
import com.example.model.*;
import com.example.view.*;
import java.util.ArrayList;
/**
 *
 * @author geremiapompei
 */
public class ClientService implements Runnable {
    private String senderAddress;
    
    public ClientService(String address) {
        this.senderAddress = address;
    }
    
    private RestResponse postRequest(Message message){
        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(message.getReceiver()).path("api");
        Response response = target.request().post(Entity.json(message));
        return response.readEntity(RestResponse.class);
    }
    
    public void run() {
        ClientView view = 
                new ClientView((mex)-> this.postRequest(mex),this.senderAddress);
        view.start();
    }
}
