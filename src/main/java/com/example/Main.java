package com.example;

import com.example.view.GUIConfigStart;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
        Application.launch(GUIConfigStart.class);
    }
}
