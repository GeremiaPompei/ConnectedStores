package com.example.model;

import java.util.List;

/**
 * Classe che ha la responsabilitò di creare oggetti richiesta rest.
 *
 * @author geremiapompei
 */
public class RestRequest {
    /**
     * Mittente della richiesta rest.
     */
    private String sender;
    /**
     * Destinatario della richiesta rest.
     */
    private String receiver;

    /**
     * Tipo della richiesta rest.
     */
    private String type;
    /**
     * Richiesta della richiesta rest.
     */
    private String request;
    /**
     * Parametri della richiesta rest.
     */
    private List<String> params;

    /**
     * Metodo costruttore.
     */
    public RestRequest() {

    }

    /**
     * Metodo costruttore.
     *
     * @param sender   Mittente con cui verrà inizializzata la rispettiva variabile di istanza.
     * @param receiver Destinatario con cui verrà inizializzata la rispettiva variabile di istanza.
     * @param type     Tipo con cui verrà inizializzata la rispettiva variabile di istanza.
     * @param request  Richiesta con cui verrà inizializzata la rispettiva variabile di istanza.
     * @param params   Parametri con cui verrà inizializzata la rispettiva variabile di istanza.
     */
    public RestRequest(String sender, String receiver, String type, String request,
                       List<String> params) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.request = request;
        this.params = params;
    }

    /**
     * Getter del mittente.
     *
     * @return Mittente.
     */
    public String getSender() {
        return this.sender;
    }

    /**
     * Setter del mittente.
     *
     * @param value Mittente sostituto.
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Getter del destinatario.
     *
     * @return Destinatario.
     */
    public String getReceiver() {
        return this.receiver;
    }

    /**
     * Setter del destinatario.
     *
     * @param value Destinatario sostituto.
     */
    public void setReceiver(String value) {
        this.receiver = value;
    }

    /**
     * Getter del tipo.
     *
     * @return Tipo.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter del tipo.
     *
     * @param value Tipo sostituto.
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Getter della richiesta.
     *
     * @return Richiesta.
     */
    public String getRequest() {
        return this.request;
    }

    /**
     * Setter della richiesta.
     *
     * @param value Richiesta sostituta.
     */
    public void setRequest(String value) {
        this.request = value;
    }

    /**
     * Getter dei parametri.
     *
     * @return Parametri.
     */
    public List<String> getParams() {
        return this.params;
    }

    /**
     * Setter dei parametri.
     *
     * @param value Parametri sostituti.
     */
    public void setParams(List<String> value) {
        this.params = value;
    }
}
