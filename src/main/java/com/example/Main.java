package com.example;

import com.example.service.Controller;
import com.example.view.GUIConfigStart;
import javafx.application.Application;

/**
 * Classe main che lancia l'interfaccia grafica.
 */
public class Main {
    /**
     * Metodo main.
     *
     * @param args Argomenti in input.
     */
    public static void main(String[] args) {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
        Application.launch(GUIConfigStart.class);
        Controller.getInstance().getServer().stop();
    }
}
