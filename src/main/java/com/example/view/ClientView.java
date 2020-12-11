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
                printClientFormat("Opzioni: [push, pull, get ,remove, exit]\n > ");
                String in = scanner.nextLine();
                List<String> input = Arrays.asList(in.split(" "));
                String command = input.get(0);
                String receiver = input.get(1);
                List<String> params = input.subList(2, input.size());
                switch (command.toLowerCase().trim()) {
                    case "push":
                        this.push(receiver, params);
                        break;
                    case "get-all":
                        this.getAll(receiver, params);
                        break;
                    case "get":
                        this.get(receiver, params);
                        break;
                    case "remove":
                        this.remove(receiver, params);
                        break;
                    case "exit":
                        printClientFormat("Goodbye!");
                        break;
                    default:
                        printClientFormat("Opzione non disponibile");
                }
                if (command.equalsIgnoreCase("exit")) break;
            } catch (Exception e) {
                printClientFormat("Opzione non disponibile");
            }
        }
        scanner.close();
    }

    private void push(String receiver, List<String> params) {
        RestRequest restRequest = new RestRequest(this.address, receiver, "tell", "push", params);
        RestResponse<TestResource> rr = this.postRequest.apply(restRequest);
        print("push", rr);
    }

    private void getAll(String receiver, List<String> params) {
        RestRequest restRequest = new RestRequest(this.address, receiver, "ask", "get-all", params);
        RestResponse<TestResource> rr = this.postRequest.apply(restRequest);
        print("get-all", rr);
    }

    private void get(String receiver, List<String> params) {
        RestRequest restRequest = new RestRequest(this.address, receiver, "ask", "get", params);
        RestResponse<TestResource> rr = this.postRequest.apply(restRequest);
        print("get", rr);
    }

    private void remove(String receiver, List<String> params) {
        RestRequest restRequest = new RestRequest(this.address, receiver, "tell", "remove", params);
        RestResponse<Boolean> rr = this.postRequest.apply(restRequest);
        print("remove", rr);
    }

    private void print(String s, RestResponse rr) {
        printClientFormat(s + " -> " + rr.getResponse().toString());
        printClientFormat(localStore.toString());
    }

    private void printClientFormat(String s) {
        System.out.println("[CLIENT] : " + s);
    }
}
