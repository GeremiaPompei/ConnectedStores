package com.example.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.example.model.*;

import java.util.Locale;

@Path("api")
public class RestService {
    private RestServiceParser parser = new RestServiceParser();

    @POST
    public RestResponse postIt(RestRequest restRequest) {
        RestResponse restResponse = new RestResponse();
        switch (restRequest.getRequest().toLowerCase()) {
            case "push":
                restResponse.setResponse(parser.push(restRequest));
                break;
            case "remove":
                restResponse.setResponse(parser.remove(restRequest));
                break;
            case "get-all":
                restResponse.setResponse(parser.getAll());
                break;
            case "get":
                restResponse.setResponse(parser.get(restRequest));
                break;
            default:
                restResponse.setResponse(false);
        }
        return restResponse;
    }

    @GET
    public String getIt() {
        return "On";
    }
}
