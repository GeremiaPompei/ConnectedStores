package com.example.service;

import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.util.Stack;

/**
 * Servizio singleton utile per gestire le notifiche del server.
 */
public class NotificationManager {
    /**
     * Unica istanza di notificationManager.
     */
    private static NotificationManager notification;

    /**
     * Metodo utile per ottenere l'unica istanza di notificationManager.
     */
    public static NotificationManager getInstance() {
        if (notification == null)
            notification = new NotificationManager();
        return notification;
    }

    /**
     * Metodo costruttore.
     */
    private NotificationManager() {
        this.notifications = new Stack<>();
    }

    /**
     * Notifiche inviate dal server.
     */
    private Stack<String> notifications;

    /**
     * Area di testo dove vengono mostrate le notifiche.
     */
    private TextArea textArea;

    /**
     * Metodo utile per inviare notifiche.
     *
     * @param notification Notifica inviata.
     */
    public void push(String notification) {
        notifications.push(LocalDateTime.now() + " -> " + notification);
        textArea.setText("");
        notifications.forEach(n -> textArea.setText(n + "\n" + textArea.getText()));
    }

    /**
     * Metodo utile per settare il riferimento all'area di testo per mostrare le notifiche.
     *
     * @param textArea Area di testo su cui mostrare le notifiche.
     */
    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
