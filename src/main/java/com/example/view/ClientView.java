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
    private Scanner scanner = new Scanner(System.in);
    private String address;
    private Function<Message, RestResponse> postRequest;
    
    public ClientView(Function<Message, RestResponse> postRequest, String address) {
        this.postRequest = postRequest;
        this.address = address;
    }
    
    public void start() {
        String input = "";
        while(input!="exit") {
            System.out.println("Opzioni: [ask, tell]");
            input = scanner.nextLine();
            switch(input) {
                case "tell": this.tellMenu(); break;
                case "ask" : this.askMenu(); break;
                case "exit": System.out.println("Goodbye!"); break;
                default: System.out.println("Opzione non disponibile");
            }
        }
    }
    
    public void tellMenu() {
        Map<String,String> map = new HashMap();
        map.put("Receiver","");
        map.put("Request","");
        map.put("Params","");
        String input = "";
        for(int i = 0; i < map.size();i++) {
            System.out.println("Inserisci "+new ArrayList(map.values()).get(i).toString()+": ");
            input = scanner.nextLine();
            map.replace(new ArrayList(map.values()).get(i).toString(), input);
            if(input=="exit") break;
        }
        if(input!="exit") {
            RestResponse sr=this.postRequest.apply(new Message(this.address, 
                    map.get("Receiver"), "tell", map.get("Request"), 
                    new ArrayList<String>(Arrays.asList(map.get("Params")
                    .split(" ")))));
            System.out.println(sr.getResponse());
        }
    }
    
    public void askMenu() {
        //this.postRequest.apply();
    }
}
