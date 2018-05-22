package model;

public class Shelve {
    private int id;
    private String name;

    public Shelve(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String shelveNumber) {
        this.name = shelveNumber;
    }
}
