package com.example.view.GUIView;

import com.example.MyDomain;
import com.example.server.ServerService;
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
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class GUIConfigController implements Initializable {
    private ObservableList<String> olAddresses;

    @FXML
    ChoiceBox<String> addresses;

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
            e.printStackTrace();
        }
    }

    @FXML
    public void selectAddress(Event event) {
        try {
            if (addresses.getValue() == null)
                throw new Exception();
            MyDomain.getInstance().setAddress(addresses.getValue());
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/GUIMain.fxml"));
            stage.setTitle("RESTJersey [ " + MyDomain.getInstance().getDomain() + " ]");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
