package com.example.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.example.model.LocalStore;
import com.example.model.Resource;
import com.example.model.RestRequest;
import com.example.model.RestResponse;
import com.example.view.ServerView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Classe che risponde alle richieste effettuate dai client tramite i suoi metodi. Tali richieste vengono risposte da
 * questa classe solo se l'url ha come ultima stringa "api".
 *
 * @author geremiapompei
 */
@Path("api")
public class RestService {
    /**
     * View del server che permette di stampare a console ciò che sta avvenendo nel server.
     */
    private ServerView view = new ServerView();

    /**
     * Metodo utile per rispondere alle richieste rest di tipo post.
     *
     * @param restRequest Richiesta post.
     * @return Risposta alla richiesta post.
     */
    @POST
    public RestResponse post(RestRequest restRequest) {
        String command = restRequest.getRequest().toLowerCase().trim();
        switch (command) {
            case "push":
                return new RestResponse<>(view.push(restRequest, command));
            case "remove":
                return new RestResponse<>(view.remove(restRequest, command));
            case "get-all":
                return new RestResponse<>(view.getAll(restRequest, command));
            case "get":
                return new RestResponse<>(view.get(restRequest, command));
            default:
                return new RestResponse<>(false);
        }
    }

    /**
     * Metodo utile per rispondere alle richieste rest di tipo get.
     *
     * @return Resources del local store.
     */
    @GET
    public String get() {
        String[] elHeaders = {"ID", "NAME", "DESCRIPTION"};
        List<Resource> elBody = LocalStore.getIstance().getResources();
        Function<Object, String> trParser =
                (s) -> "<td style=\"border: 1px solid black; width:33vw; text-align: center\">" + s + "</td>";
        String headers = Arrays.stream(elHeaders)
                .map((x) -> trParser.apply(x))
                .reduce((x, y) -> x + y).get();
        String body = elBody.isEmpty() ? "" : elBody.stream()
                .map((x) -> "<tr>" + trParser.apply(x.getId()) + trParser.apply(x.getName()) +
                        trParser.apply(x.getDescription()) + "</tr>")
                .reduce((x, y) -> x + y).get();
        return "<h1 style=\"text-align: center\">RESOURCES</h1><table><thead><tr>" + headers + "</tr></thead><tbody>" +
                body + "</tbody></table>";
    }
}
