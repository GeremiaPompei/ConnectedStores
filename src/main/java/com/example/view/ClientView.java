/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.view;

import java.util.Scanner;
import java.util.*;
import java.util.function.*;

import com.example.controller.Controller;
import com.example.model.*;

/**
 * @author geremiapompei
 */
public class ClientView {
    private Controller controller;

    public ClientView(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                ConsolePrinter.printClient("Opzioni: [push, get-all, get , remove, local-store, help, exit]");
                System.out.print(" > ");
                String in = scanner.nextLine();
                String[] singCmd = {in, "", ""};
                List<String> input = Arrays.asList(in.contains(" ") ? in.split(" ") : singCmd);
                String command = input.get(0).toLowerCase().trim();
                String receiver = input.get(1);
                List<String> params = input.subList(2, input.size());
                switch (command) {
                    case "local-store":
                        ConsolePrinter.printClient(this.controller.getLocalStore().toString());
                    case "push":
                    case "remove":
                        this.tell(receiver, params, command);
                        break;
                    case "get-all":
                    case "get":
                        this.ask(receiver, params, command);
                        break;
                    case "help":
                        ConsolePrinter.printClient("\n   > opzione indirizzoReceiver request [params...]");
                        break;
                    case "exit":
                        controller.stopServer();
                        ConsolePrinter.printClient("Goodbye!");
                        break;
                    default:
                        ConsolePrinter.printClient("Opzione non disponibile");
                }
                if (command.equalsIgnoreCase("exit")) break;
            } catch (Exception e) {
                ConsolePrinter.printClient("Opzione non disponibile");
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
        RestRequest restRequest = new RestRequest(this.controller.getAddress(), receiver, type, command, params);
        RestResponse rr = this.controller.doRequest(restRequest);
        print(command, rr);
    }

    private void print(String s, RestResponse rr) {
        ConsolePrinter.printClient(s + " -> " + rr.getResponse());
    }
}
