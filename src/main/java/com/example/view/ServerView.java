package com.example.view;

import com.example.model.LocalStore;
import com.example.model.RestRequest;
import com.example.model.Resource;

import java.util.List;

/**
 * Classe utile per costruire oggetti che servono agli oggetti RestService per eseguire diverse diverse azioni in base
 * alle chiamate rest e stampare risultati a console.
 *
 * @author geremiapompei
 */
public class ServerView {
    /**
     * Variabile che tiene traccia dello store locale.
     */
    private LocalStore localStore = LocalStore.getIstance();

    /**
     * Metodo utile per creare e caricare una risorsa in un certo store.
     *
     * @param request Richiesta rest contenente le informazioni per eseguire l'operazione.
     * @param command Stringa del comando scelto.
     * @return Valore indicante la buona o cattiva riuscita dell'operazione.
     */
    public boolean push(RestRequest request, String command) {
        try {
            int id = Integer.parseInt(request.getParams().get(0));
            String name = request.getParams().get(1);
            String description = request.getParams().subList(2, request.getParams().size())
                    .stream().reduce((x, y) -> x + " " + y).get();
            if (localStore.getResource(id) != null)
                localStore.removeResource(id);
            Resource resource = new Resource(id, name, description);
            localStore.addResource(resource);
            print(command, resource, request.getSender());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo utile per visualizzare tutte le risorsa di un certo store.
     *
     * @param request Richiesta rest contenente le informazioni per eseguire l'operazione.
     * @param command Stringa del comando scelto.
     * @return Resources dello store.
     */
    public List<Resource> getAll(RestRequest request, String command) {
        print(command, localStore.getResources(), request.getSender());
        return localStore.getResources();
    }


    /**
     * Metodo utile per visualizzare una risorsa di un certo store.
     *
     * @param request Richiesta rest contenente le informazioni per eseguire l'operazione.
     * @param command Stringa del comando scelto.
     * @return Resource dello store.
     */
    public Resource get(RestRequest request, String command) {
        int id = Integer.parseInt(request.getParams().get(0));
        print(command, localStore.getResource(id), request.getSender());
        return localStore.getResource(id);
    }


    /**
     * Metodo utile per rimuovere una risorsa da un certo store.
     *
     * @param request Richiesta rest contenente le informazioni per eseguire l'operazione.
     * @param command Stringa del comando scelto.
     * @return Valore indicante la buona o cattiva riuscita dell'operazione.
     */
    public boolean remove(RestRequest request, String command) {
        int id = Integer.parseInt(request.getParams().get(0));
        print(command, id, request.getSender());
        return localStore.removeResource(id);
    }

    /**
     * Metodo utile per stampare a console il risultato di una certa operazione.
     *
     * @param command Comando eseguito.
     * @param t       Oggetto da stampare soto forma di stringa.
     * @param sender  Indirizzo del mittente del messaggio.
     */
    private <T> void print(String command, Object t, String sender) {
        ConsolePrinter.printServer(command + " -> " + t, sender);
    }
}
