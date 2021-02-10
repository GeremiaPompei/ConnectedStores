package com.example.service;

import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.util.Stack;

public class NotificationManager {
    private static NotificationManager notification;

    public static NotificationManager getInstance() {
        if (notification == null)
            notification = new NotificationManager();
        return notification;
    }

    private Stack<String> notifications;
    private TextArea textArea;

    private NotificationManager() {
        this.notifications = new Stack<>();
    }

    public void push(String notification) {
        notifications.push(LocalDateTime.now() + " -> " + notification);
        textArea.setText("");
        notifications.forEach(n -> textArea.setText(n + "\n" + textArea.getText()));
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
