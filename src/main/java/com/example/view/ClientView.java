package com.example.view;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

import com.example.controller.Controller;
import com.example.model.RestRequest;
import com.example.model.RestResponse;

/**
 * Classe che ha la responsabilità di costruire oggetti per fornire la vista del client con cui può interagire l'utente.
 *
 * @author geremiapompei
 */
public class ClientView {
    /**
     * Variabile utile per l'interazione con il model.
     */
    private Controller controller;

    /**
     * Metodo costruttore.
     *
     * @param controller Inizializza la rispettiva variabile d'istanza.
     */
    public ClientView(Controller controller) {
        this.controller = controller;
    }

    /**
     * Metodo utile per avviare la view per l'interazione con l'utente.
     *
     * @param scanner Oggetto utile a prelevare le informazioni scritte dall'utente.
     */
    public void start(Scanner scanner) {
        while (true) {
            try {
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
                        break;
                    case "push":
                    case "remove":
                        this.tell(receiver, params, command);
                        break;
                    case "get-all":
                    case "get":
                        this.ask(receiver, params, command);
                        break;
                    case "help":
                        ConsolePrinter.printClient("\n   > Opzioni: [push, get-all, get , remove, local-store, " +
                                "help, exit]\n   > opzione indirizzoReceiver request [params...]");
                        break;
                    case "exit":
                        controller.stopServer();
                        ConsolePrinter.printClient("Goodbye!");
                        break;
                    default:
                        ConsolePrinter.printClient("Opzione non disponibile. Per vedere la lista delle opzioni " +
                                "digitare il comando\n   > help");
                }
                if (command.equalsIgnoreCase("exit")) break;
            } catch (Exception e) {
                ConsolePrinter.printClient("Opzione non disponibile");
            }
        }
        scanner.close();
    }

    /**
     * Metodo privato utile per inviare richieste rest di tipo tell.
     *
     * @param receiver Indirizzo di chi riceve il messaggio.
     * @param params   Parametri del messaggio, sono argomenti della richiesta.
     * @param command  Comando da parte del client che specifica la funzionalità richiesta al server.
     */
    private void tell(String receiver, List<String> params, String command) {
        this.miniController(receiver, params, command, "tell");
    }

    /**
     * Metodo privato utile per inviare richieste rest di tipo ask.
     *
     * @param receiver Indirizzo di chi riceve il messaggio.
     * @param params   Parametri del messaggio, sono argomenti della richiesta.
     * @param command  Comando da parte del client che specifica la funzionalità richiesta al server.
     */
    private void ask(String receiver, List<String> params, String command) {
        this.miniController(receiver, params, command, "ask");
    }

    /**
     * Metodo privato che ha la funzionalità di mini controllore che gestisce l'invio dei messaggi e la gestione delle
     * risposte.
     *
     * @param receiver Indirizzo di chi riceve il messaggio.
     * @param params   Parametri del messaggio, sono argomenti della richiesta.
     * @param command  Comando da parte del client che specifica la funzionalità richiesta al server.
     * @param type     Può essere o tell (risposta con un boolean) o ask (risposta con un oggetto).
     */
    private void miniController(String receiver, List<String> params, String command, String type) {
        RestRequest restRequest =
                new RestRequest(this.controller.getSenderAddress(), "http://" + receiver + ":8080/",
                        type, command, params);
        RestResponse rr = this.controller.doRequest(restRequest);
        print(command, rr);
    }

    /**
     * Metodo utile per la stampa a console dei risultati delle risposte.
     *
     * @param str Stringa da stampare.
     * @param rr  Risposta rest contenente un oggetto generico.
     */
    private void print(String str, RestResponse rr) {
        ConsolePrinter.printClient(str + " -> " + rr.getResponse());
    }
}
