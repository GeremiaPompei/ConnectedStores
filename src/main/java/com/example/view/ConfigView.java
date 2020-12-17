package com.example.view;

import com.example.controller.Controller;

import java.util.Scanner;

/**
 * Classe che ha la responsabilità di costruire oggetti per fornire la vista della configurazione iniziale dell'app.
 *
 * @author geremiapompei
 */
public class ConfigView {
    /**
     * Variabile per l'interazione con il model.
     */
    private Controller controller;

    /**
     * Metodo costruttore.
     *
     * @param controller Inizializza la rispettiva variabile d'istanza.
     */
    public ConfigView(Controller controller) {
        this.controller = controller;
    }

    /**
     * Metodo che ha la responsabilità di inizializzare la configurazione facendo funzionare la vista a console.
     *
     * @param scanner Oggetto utile a prelevare le informazioni scritte dall'utente.
     */
    public void init(Scanner scanner) {
        while (this.controller.getSenderAddress().equals("")) {
            System.out.print("Inserisci indirizzo IP del Sender: ");
            this.controller.setSenderAddress(scanner.nextLine().trim());
        }
    }
}
