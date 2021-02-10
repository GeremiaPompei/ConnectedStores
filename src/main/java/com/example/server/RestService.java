package com.example.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.example.model.RecEntity;
import com.example.service.NotificationManager;
import com.example.service.StringfyRec;
import it.mynext.iaf.nettrs.Rec;
import org.glassfish.grizzly.http.server.Request;

/**
 * Classe che risponde alle richieste effettuate dai client tramite i suoi metodi. Tali richieste vengono risposte da
 * questa classe solo se l'url ha come ultima stringa "api".
 *
 * @author geremiapompei
 */
@Path("api")
public class RestService {
    private static RecEntity rec;

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

    @GET
    @Path("get")
    public RecEntity getRec(@Context Request re) {
        NotificationManager.getInstance().push(re.getRemoteAddr() + " : get\n" + StringfyRec.stringOf(rec));
        return rec;
    }
}
