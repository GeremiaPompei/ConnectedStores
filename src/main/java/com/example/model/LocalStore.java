package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocalStore {

    private List<TestResource> resources;
    private static LocalStore istance;

    private LocalStore() {
        this.resources = new ArrayList<>();
    }

    public static LocalStore getIstance() {
        if (istance == null)
            istance = new LocalStore();
        return istance;
    }

    public TestResource getResource(int id) {
        return resources.stream().filter((res)->res.getId()==id).findAny().orElse(null);
    }

    public List<TestResource> getResources() {
        return resources;
    }

    public void addResource(TestResource resource) {
        this.resources.add(resource);
    }

    public boolean removeResource(int id) {
        if(getResource(id)!=null) {
            this.resources = this.resources.stream()
                    .filter((res) -> res.getId() != id).collect(Collectors.toList());
            return true;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "LocalStore{" +
                "resources=" + resources +
                '}';
    }
}
