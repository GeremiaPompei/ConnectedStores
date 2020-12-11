package com.example.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.example.model.RestRequest;
import com.example.model.RestResponse;

@Path("api")
public class RestService {
    private RestServiceParser parser = new RestServiceParser();

    @POST
    public RestResponse postIt(RestRequest restRequest) {
        RestResponse restResponse = new RestResponse();
        String command = restRequest.getRequest().toLowerCase().trim();
        switch (command) {
            case "push":
                restResponse.setResponse(parser.push(restRequest, command));
                break;
            case "remove":
                restResponse.setResponse(parser.remove(restRequest, command));
                break;
            case "get-all":
                restResponse.setResponse(parser.getAll(restRequest, command));
                break;
            case "get":
                restResponse.setResponse(parser.get(restRequest, command));
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
