package com.example.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.example.MyDomain;
import com.example.Notification;
import com.example.view.GUIView.GUIViewController;
import it.mynext.iaf.nettrs.Rec;
import javafx.scene.control.TextArea;

/**
 * Classe che risponde alle richieste effettuate dai client tramite i suoi metodi. Tali richieste vengono risposte da
 * questa classe solo se l'url ha come ultima stringa "api".
 *
 * @author geremiapompei
 */
@Path("api")
public class RestService {
    private static Rec rec;

    @POST
    @Path("post")
    public boolean postRec(Rec rec) {
        try {
            this.rec = rec;
            Notification.getInstance().push(rec + "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GET
    @Path("get")
    public Rec getRec() {
        Rec rv = new Rec();
        rv.init(2, 1);
        rv.setFldData(0, "curruek", Rec.FDT_STR, 51);
        rv.setFldData(1, "nextruek", Rec.FDT_STR, 51);
        rv.setValue("curruek", "codubi");
        rv.setValue("nextruek", "");
        return rec;
    }
}
