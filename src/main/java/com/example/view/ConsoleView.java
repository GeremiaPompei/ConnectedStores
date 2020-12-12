package com.example.view;

import com.example.controller.Controller;

import java.util.Scanner;

/**
 * Classe che ha la responsabilità di costruire oggetti per la view principale con la quale l'utente può interagire.
 *
 * @author geremiapompei
 */
public class ConsoleView {

    /**
     * Metodo principale che non fa altro che mettere in esecuzione la view.
     *
     * @param controller oggetto che permette alla view di accedere al model.
     */
    public void start(Controller controller) {
        Scanner scanner = new Scanner(System.in);
        try {
            ConfigView config = new ConfigView(controller);
            config.init(scanner);
            controller.init();
            ClientView view = new ClientView(controller);
            view.start(scanner);
        } catch (Exception e) {
            System.err.println("Errore: [" + e.getMessage() + "]");
            System.err.println("L'applicazione ha fallito nell'esecuzione, riprova più tardi.");
        }
        scanner.close();
    }
}
