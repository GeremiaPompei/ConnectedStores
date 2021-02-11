package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe utile a lanciare l'esecuzione della prima interfaccia grafica.
 */
public class GUIConfigStart extends Application {
    /**
     * Metodo utile a lanciare la prima interfaccia grafica di configurazione.
     *
     * @param stage Stage su cui avere il file fxml della prima interfaccia grafica.
     * @throws Exception Eccezione dovuta al lancio della GUI.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUIConfig.fxml"));
        stage.setTitle("RESTJersey - config");
        stage.setScene(new Scene(root, 600, 60));
        stage.show();
    }
}
