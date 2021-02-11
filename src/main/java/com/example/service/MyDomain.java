package com.example.service;

/**
 * Servizio singleton utile per gestire il dominio dei servizi client e server.
 */
public class MyDomain {
    /**
     * Unica istanza della classe myDomain.
     */
    private static MyDomain myDomain;

    /**
     * Metodo utile per ottenere l'unica istanza di myDomain.
     *
     * @return
     */
    public static MyDomain getInstance() {
        if (myDomain == null)
            myDomain = new MyDomain();
        return myDomain;
    }

    /**
     * Metodo costruttore.
     */
    private MyDomain() {
        address = "/localhost";
    }

    /**
     * Indirizzo ip di myDomain.
     */
    private String address;

    /**
     * Metodo utile per ottenere il dominio derivato dall'indirizzo ip di myDomain.
     *
     * @return Dominio derivato dall'indirizzo ip di myDomain.
     */
    public String getDomain() {
        return "https:/" + this.address + ":8080/";
    }

    /**
     * Metodo utile per modificare l'indirizzo ip di myDomain.
     *
     * @param address Nuovo indirizzo ip.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
