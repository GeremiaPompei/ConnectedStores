/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.util.List;

/**
 * Message è la classe che ha la responsabilità di creare oggetti messaggio con 
 * un formato specifico;
 * @author geremiapompei
 */
public class RestRequest {
    private String sender;
    private String receiver;
    private String type;
    private String request;
    private List<String> params;
    
    public RestRequest() {
        
    }
    
    public RestRequest(String sender, String receiver, String type, String request,
                       List<String> params) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.request = request;
        this.params = params;
    }
    
    public String getSender() {
        return this.sender;
    }
    
    public void setSender(String value) {
        this.sender = value;
    }
    
    public String getReceiver() {
        return this.receiver;
    }
    
    public void setReceiver(String value) {
        this.receiver = value;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String value) {
        this.type = value;
    }
    
    public String getRequest() {
        return this.request;
    }
    
    public void setRequest(String value) {
        this.request = value;
    }
    
    public List<String> getParams() {
        return this.params;
    }
    
    public void setParams(List<String> value) {
        this.params = value;
    }
}
