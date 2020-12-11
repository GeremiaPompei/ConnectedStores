package com.example.view;

public class ConsolePrinter {
    private static final String CLIENT = "\u001B[36m";
    private static final String SERVER = "\u001B[33m";
    private static final String RESET = "\033[0m";

    public static void printClient(String s) {
        System.out.println(CLIENT + "[CLIENT] : " + s + RESET);
    }

    public static void printServer(String s) {
        System.out.print("\n" + SERVER + "[SERVER] : " + s + RESET + "\n > ");
    }

    public static void printServer(String s, String sender) {
        System.out.print(SERVER + "Request from " + sender + RESET);
        printServer(s);
    }

}
