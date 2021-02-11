package com.example.view;

import com.example.service.MyDomain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Classe controller dell'interfaccia grafica GUIConfig.fxml.
 */
public class GUIConfigController implements Initializable {

    /**
     * Label utile per mostrare notifiche.
     */
    @FXML
    Label notification;
    /**
     * Lista degli indirizzi ip disponibili nel calcolatore.
     */
    private ObservableList<String> olAddresses;

    /**
     * Choice box con la quale scegliere l'indirizzo ip.
     */
    @FXML
    ChoiceBox<String> addresses;

    /**
     * Metodo inizializzatore.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            olAddresses = FXCollections.observableArrayList();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    olAddresses.add(inetAddress.toString());
                }
            }
            addresses.setItems(olAddresses);
        } catch (SocketException e) {
            notification.setText(e.getMessage());
        }
    }

    /**
     * Metodo utile a selezionare un indirizzo ip scelto nella choiceBox.
     *
     * @param event Evento di selezione.
     */
    @FXML
    public void selectAddress(Event event) {
        try {
            if (addresses.getValue() == null)
                throw new Exception("Seleziona un indirizzo ip.");
            MyDomain.getInstance().setAddress(addresses.getValue());
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/GUIMain.fxml"));
            stage.setTitle("RESTJersey [ " + MyDomain.getInstance().getDomain() + " ]");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }
}
