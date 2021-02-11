package com.example.server;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.example.model.RecEntity;
import com.example.service.NotificationManager;
import com.example.service.StringfyRec;
import org.glassfish.grizzly.http.server.Request;

/**
 * Classe che risponde alle richieste effettuate dai client tramite i suoi metodi. Tali richieste vengono risposte da
 * questa classe solo se l'url ha come ultima stringa "api".
 *
 * @author geremiapompei
 */
@Path("api")
public class RestService {
    /**
     * Stato del rec contenuto in tale server.
     */
    private static RecEntity rec;

    /**
     * Metodo utile per rispondere a chiamate REST con metodo POST.
     *
     * @param rec Oggetto inviato.
     * @param re  Richiesta REST effettuata.
     * @return Riscontro positivo o negativo se l'operazione ha avuto successo o no.
     */
    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postRec(RecEntity rec, @Context Request re) {
        try {
            this.rec = rec;
            NotificationManager.getInstance()
                    .push(re.getRemoteAddr() + " : post\n" + StringfyRec.stringOf(rec));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo utile per rispondere a chiamate REST con metodo GET.
     *
     * @param re Richiesta REST effettuata.
     * @return Oggetto richiesto.
     */
    @GET
    @Path("get")
    public RecEntity getRec(@Context Request re) {
        NotificationManager.getInstance().push(re.getRemoteAddr() + " : get\n" + StringfyRec.stringOf(rec));
        return rec;
    }

    /**
     * Metodo utile per rispondere a chiamate REST con metodo GET.
     *
     * @param re Richiesta REST effettuata.
     * @return Oggetto richiesto.
     */
    @DELETE
    @Path("delete")
    public boolean deleteRec(@Context Request re) {
        NotificationManager.getInstance().push(re.getRemoteAddr() + " : delete\n");
        if (rec == null)
            return false;
        rec = null;
        return true;
    }
}
