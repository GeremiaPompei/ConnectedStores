package com.example.view;

import com.example.service.MyDomain;
import com.example.service.NotificationManager;
import com.example.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIViewController implements Initializable {

    private Controller controller;
    @FXML
    Label notification;

    @FXML
    TextArea serverArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = Controller.getInstance();
        controller.init();
        NotificationManager.getInstance().setTextArea(serverArea);
    }

    @FXML
    public void get() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/GUIGet.fxml"));
            stage.setTitle("Get REC [ " + MyDomain.getInstance().getDomain() + " ]");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }

    @FXML
    public void post() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/GUIPost.fxml"));
            stage.setTitle("Post REC [ " + MyDomain.getInstance().getDomain() + " ]");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            notification.setText(e.getMessage());
        }
    }
}
