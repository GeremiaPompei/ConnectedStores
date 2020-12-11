/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geremiapompei
 */
package com.example;

import com.example.controller.Controller;
import com.example.view.ConfigView;
import com.example.server.ServerService;
import com.example.client.ClientService;
import com.example.view.ConsoleView;

import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        Controller controller = new Controller();
        ConsoleView view = new ConsoleView();
        view.start(controller);
    }
}
