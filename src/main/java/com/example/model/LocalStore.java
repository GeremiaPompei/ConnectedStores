package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe singleton utile per creare e gestire l'oggetto store locale.
 *
 * @author geremiapompei
 */
public class LocalStore {
    /**
     * Risorse dello store locale.
     */
    private List<Resource> resources;
    /**
     * Unica istanza di tale classe
     */
    private static LocalStore istance;

    /**
     * Metodo costruttore.
     */
    private LocalStore() {
        this.resources = new ArrayList<>();
    }

    /**
     * Metodo utile per ottenere la singola istanza dello store locale.
     *
     * @return
     */
    public static LocalStore getIstance() {
        if (istance == null)
            istance = new LocalStore();
        return istance;
    }

    /**
     * Getter di una risorsa.
     *
     * @param id Id della risorsa ricercata.
     * @return Risorsa ricercata.
     */
    public Resource getResource(int id) {
        return resources.stream().filter((res) -> res.getId() == id)
                .findAny()
                .orElse(null);
    }

    /**
     * Getter delle risorse.
     *
     * @return
     */
    public List<Resource> getResources() {
        return resources;
    }

    /**
     * Metodo per aggiungere una risorsa alla lista delle risorse.
     *
     * @param resource Risorsa da aggiungere.
     */
    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    /**
     * Metodo per rimuovere una risorsa alla lista delle risorse.
     *
     * @param id Risorsa da aggiungere.
     * @return Riscontro positivo o negativo.
     */
    public boolean removeResource(int id) {
        if (getResource(id) != null) {
            this.resources = this.resources.stream()
                    .filter((res) -> res.getId() != id).collect(Collectors.toList());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo utile per fornire la rappresentazione a stringa dell'oggetto creato da tale classe.
     *
     * @return Stringa rappresentante l'oggetto.
     */
    @Override
    public String toString() {
        return "LocalStore{" +
                "resources=" + resources +
                '}';
    }
}
