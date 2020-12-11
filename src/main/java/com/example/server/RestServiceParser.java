package com.example.server;

import com.example.model.LocalStore;
import com.example.model.RestRequest;
import com.example.model.TestResource;

import java.util.List;

public class RestServiceParser {
    private LocalStore  localStore = LocalStore.getIstance();

    public boolean push(RestRequest restRequest) {
        try {
            int id = Integer.parseInt(restRequest.getParams().get(0));
            String name = restRequest.getParams().get(1);
            String description = restRequest.getParams().get(2);
            TestResource resource = new TestResource(id, name, description);
            localStore.addResource(resource);
            print("push", resource);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TestResource> getAll() {
        print("get-all", localStore.getResources());
        return localStore.getResources();
    }

    public TestResource get(RestRequest restRequest) {
        int id = Integer.parseInt(restRequest.getParams().get(0));
        print("get", localStore.getResource(id));
        return localStore.getResource(id);
    }

    public boolean remove(RestRequest restRequest) {
        try {
            int id = Integer.parseInt(restRequest.getParams().get(0));
            localStore.removeResource(id);
            print("remove", id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void print (String s, Object t) {
        printServerFormat(s + " -> " + t.toString());
        printServerFormat(localStore.toString());
    }

    private void printServerFormat(String s) {
        System.out.println("[SERVER] : " + s);
    }
}
