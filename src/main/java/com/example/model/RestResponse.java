package com.example.model;

/**
 * Classe che ha la responsabilità di creare oggetti contenenti l'effettiva risposta ad una richiesta rest.
 *
 * @param <T> Tipo di oggetto della risposta contenuta.
 * @author geremiapompei
 */
public class RestResponse<T> {
    /**
     * Oggetto generico della risposta.
     */
    private T response;

    /**
     * Metodo costruttore.
     */
    public RestResponse() {
    }

    /**
     * Metodo costruttore
     *
     * @param response Risposta che inizializza la variabile d'istanza.
     */
    public RestResponse(T response) {
        this.response = response;
    }

    /**
     * Getter della risposta.
     *
     * @return Risposta contenuta.
     */
    public T getResponse() {
        return this.response;
    }

    /**
     * Setter della risposta.
     *
     * @param value Valore assegnato alla risposta.
     */
    public void setResponse(T value) {
        this.response = value;
    }
}
