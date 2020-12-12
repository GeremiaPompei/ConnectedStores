package com.example.view;

/**
 * Classe che ha la responsabilità di fornire metodi statici di utilità per la stampa a console.
 *
 * @author geremiapompei
 */
public class ConsolePrinter {
    /**
     * Variabile contenente la stringa per colorare la scritta del client.
     */
    private static final String CLIENT = "\u001B[36m";
    /**
     * Variabile contenente la stringa per colorare la scritta del server.
     */
    private static final String SERVER = "\u001B[33m";
    /**
     * Variabile contenente la stringa terminatrice per la colorazione della stampa a console.
     */
    private static final String RESET = "\033[0m";


    /**
     * Metodo utile per la stampa a console da parte del client.
     *
     * @param str Stringa passata da stampare.
     */
    public static void printClient(String str) {
        System.out.println(CLIENT + "[CLIENT] : " + str + RESET);
    }

    /**
     * Metodo utile per la stampa a console da parte del server.
     *
     * @param str Stringa passata da stampare.
     */
    public static void printServer(String str) {
        System.out.println(SERVER + "[SERVER] : " + str + RESET);
    }

    /**
     * Metodo utile per la stampa a console da parte del server.
     *
     * @param str    Stringa passata da stampare.
     * @param sender Sender del messaggio inviato al server.
     */
    public static void printServer(String str, String sender) {
        System.out.println(SERVER + "Request from " + sender + RESET);
        printServer(str);
        System.out.print(" > ");
    }

}
