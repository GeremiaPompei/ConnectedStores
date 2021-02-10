package com.example;

import com.example.view.GUIConfigStart;
import javafx.application.Application;

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
        Application.launch(GUIConfigStart.class);
    }
}
