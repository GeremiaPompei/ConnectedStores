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
 * @author geremiapompei
 */
public class ClientView {
    private LocalStore localStore = LocalStore.getIstance();
    private String address;
    private Function<RestRequest, RestResponse> postRequest;

    public ClientView(Function<RestRequest, RestResponse> postRequest, String address) {
        this.postRequest = postRequest;
        this.address = address;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                printClientFormat("Opzioni: [push, get-all, get ,remove, help, exit]");
                System.out.print(" > ");
                String in = scanner.nextLine();
                String [] singCmd = {in, "", ""};
                List<String> input = Arrays.asList(in.contains(" ")?in.split(" "):singCmd);
                String command = input.get(0).toLowerCase().trim();
                String receiver = input.get(1);
                List<String> params = input.subList(2, input.size());
                switch (command) {
                    case "push":
                        this.tell(receiver, params, command);
                        break;
                    case "get-all":
                        this.ask(receiver, params, command);
                        break;
                    case "get":
                        this.ask(receiver, params, command);
                        break;
                    case "remove":
                        this.tell(receiver, params, command);
                        break;
                    case "help":
                        printClientFormat(" > opzione indirizzoReceiver request [params...]");
                        break;
                    case "exit":
                        printClientFormat("Goodbye!");
                        break;
                    default:
                        printClientFormat("Opzione non disponibile");
                }
                if (command.equalsIgnoreCase("exit")) break;
            } catch (Exception e) {
                e.printStackTrace();
                printClientFormat("Opzione non disponibile");
            }
        }
        scanner.close();
    }

    private void tell(String receiver, List<String> params, String command) {
        miniController(receiver, params, command, "tell");
    }

    private void ask(String receiver, List<String> params, String command) {
        miniController(receiver, params, command, "ask");
    }

    private void miniController(String receiver, List<String> params, String command, String type) {
        RestRequest restRequest = new RestRequest(this.address, receiver, type, command, params);
        RestResponse<TestResource> rr = this.postRequest.apply(restRequest);
        print(command, rr);
    }

    private void print(String s, RestResponse rr) {
        printClientFormat(s + " -> " + rr.getResponse().toString());
        printClientFormat(localStore.toString());
    }

    private void printClientFormat(String s) {
        System.out.println("[CLIENT] : " + s);
    }
}
