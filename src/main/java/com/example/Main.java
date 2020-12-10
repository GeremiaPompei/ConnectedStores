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

import com.example.view.Config;
import com.example.server.ServerService;
import com.example.client.ClientService;
import java.util.ArrayList;
import com.example.model.*;
import java.util.concurrent.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String [] args) {
        Config config = new Config();
        config.init();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(new ServerService(config.getAddress()));
        exec.submit(new ClientService());
        exec.shutdown();
    }
}
