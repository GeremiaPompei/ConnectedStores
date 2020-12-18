package com.example;

import com.example.controller.Controller;
import com.example.view.ConsoleView;

/**
 * Classe Main che ha ha la responsabilità di far partire l'app.
 *
 * @author geremiapompei
 */
public class Main {
    /**
     * Metodo main.
     *
     * @param args argomenti del metodo main.
     */
    public static void main(String[] args) {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
        Controller controller = new Controller();
        ConsoleView view = new ConsoleView();
        view.start(controller);
    }
}
