/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author geremiapompei
 */
public class Response<T> {
    T response;
    
    public Response(T response) {
        this.response = response;
    }
    
    public T getResponse() {
        return this.response;
    }
    
    public void setResponse(T value) {
        this.response = value;
    }
}
