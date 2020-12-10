package com.example.server;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.example.model.*;

/**
 * Root resource (exposed at "api" path)
 */
@Path("api")
public class RestService {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    public Response getIt(Message message) {
        boolean res = message.getType().equals("tell");
        return new Response(res);
    }
}
