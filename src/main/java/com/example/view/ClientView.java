/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.view;
import java.util.Scanner;
import java.util.*;
import java.util.function.*;
import com.example.model.*;

/**
 *
 * @author geremiapompei
 */
public class ClientView {
    private String address;
    private Function<Message, RestResponse> postRequest;
    
    public ClientView(Function<Message, RestResponse> postRequest, String address) {
        this.postRequest = postRequest;
        this.address = address;
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Opzioni: [ask, tell]");
            String in = scanner.nextLine();
            List<String> input = Arrays.asList(in.split(" "));
            String command = input.get(0);
            input.remove(0);
            switch(command.trim()) {
                case "tell": this.tellMenu(input); break;
                case "ask" : this.askMenu(input); break;
                case "exit": System.out.println("Goodbye!"); break;
                default: System.out.println("Opzione non disponibile");
            }
            if(command.equalsIgnoreCase("exit")) break;
        }
        scanner.close();
    }
    
    public void tellMenu(List<String> input) {
        input.forEach(s->System.out.println(s));
        RestResponse rr = this.postRequest.apply(new Message(this.address,
                input.get(0),"tell",input.get(1),input));
        System.out.println("Res -> "+rr.getResponse());
    }
    
    public void askMenu(List<String> input) {
        //this.postRequest.apply();
    }
}
