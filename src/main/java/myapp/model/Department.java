package myapp.model;

import java.util.UUID;

public class Department {
    private String id = UUID.randomUUID().toString();
    private String name;

    public Department(String name) {
        this.name = name;
    }

    private Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department makeCopy(){
        return new Department(this.id, this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}