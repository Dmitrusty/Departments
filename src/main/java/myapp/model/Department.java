package myapp.model;

public class Department {
    private static int counter = 0;

    private final int id;
    private String name;

    public Department(String name) {
        this.id = ++counter;
        this.name = name;
    }

    public Department(int id, String name) {
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

    public int getId() {
        return id;
    }
}