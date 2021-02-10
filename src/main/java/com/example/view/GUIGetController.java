package com.example.view;

import com.example.controller.Controller;
import com.example.service.StringfyRec;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIGetController {
    @FXML
    TextField ipAddress;
    @FXML
    TextArea textArea;

    public void done() {
        textArea.setText(StringfyRec.stringOf(Controller.getInstance().getClient()
                .getRec("https://" + ipAddress.getText() + ":8080/")));
    }
}
