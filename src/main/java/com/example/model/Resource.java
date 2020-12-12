package com.example.model;

/**
 * Classe che ha la responsabilità di creare oggetti risorsa.
 *
 * @author geremiapompei
 */
public class Resource {
    /**
     * Id della risorsa.
     */
    private int id;
    /**
     * Nome della risorsa.
     */
    private String name;
    /**
     * Descrizione della risorsa.
     */
    private String description;

    /**
     * Metodo costruttore.
     */
    public Resource() {
    }

    /**
     * Metodo costruttore.
     *
     * @param id          Id con cui verrà inizializzata la rispettiva variabile di istanza.
     * @param name        Nome con cui verrà inizializzata la rispettiva variabile di istanza.
     * @param description Descrizione con cui verrà inizializzata la rispettiva variabile di istanza.
     */
    public Resource(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Getter dell'id.
     *
     * @return Id.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter del nome.
     *
     * @return Nome
     */
    public String getName() {
        return name;
    }

    /**
     * Getter della descrizione.
     *
     * @return Descrizione.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter dell'id.
     *
     * @param id Id sostituto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter del nome.
     *
     * @param name Nome sostituto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter della descrizione.
     *
     * @param description Descrizione sostituta.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metodo utile per fornire la rappresentazione a stringa dell'oggetto creato da tale classe.
     *
     * @return Stringa rappresentante l'oggetto.
     */
    @Override
    public String toString() {
        return "TestResource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
