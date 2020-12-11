/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

/**
 *
 * @author geremiapompei
 */
public class RestResponse {
    Object response;
    
    public RestResponse() {
        
    }
    
    public RestResponse(Object response) {
        this.response = response;
    }
    
    public Object getResponse() {
        return this.response;
    }
    
    public void setResponse(Object value) {
        this.response = value;
    }
}
