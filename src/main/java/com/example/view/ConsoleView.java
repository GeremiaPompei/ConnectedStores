package com.example.view;

import com.example.controller.Controller;

public class ConsoleView {

    public void start(Controller controller) {
        try {
            ConfigView config = new ConfigView();
            config.init();
            controller.init(config.getAddress());
            ClientView view = new ClientView(controller);
            view.start();
        } catch (Exception e) {
            System.err.println("Errore.");
        }
    }
}
