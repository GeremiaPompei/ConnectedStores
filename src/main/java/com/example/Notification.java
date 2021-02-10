package com.example;

import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.util.Stack;

public class Notification {
    private static Notification notification;

    public static Notification getInstance() {
        if (notification == null)
            notification = new Notification();
        return notification;
    }

    private Stack<String> notifications;
    private TextArea textArea;

    private Notification() {
        this.notifications = new Stack<>();
    }

    public void push(String nw) {
        notifications.push(LocalDateTime.now() + " -> " + nw);
        textArea.setText("");
        notifications.forEach(n -> textArea.setText(n + "\n" + textArea.getText()));
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
