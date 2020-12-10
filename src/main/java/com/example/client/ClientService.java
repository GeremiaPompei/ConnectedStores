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
import java.util.ArrayList;
/**
 *
 * @author geremiapompei
 */
public class ClientService implements Runnable {
    
    private RestResponse postRequest(Message message){
        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(message.getReceiver()).path("api");
        Response response = target.request().post(Entity.json(message));
        return response.readEntity(RestResponse.class);
    }
    
    public void run() {
        Message mex = new Message("http://192.168.1.67:8080/"
                    ,"http://192.168.1.223:8080/","tell","func",new ArrayList());
        RestResponse res = this.postRequest(mex);
        System.out.println(res.getResponse());
    }
}
