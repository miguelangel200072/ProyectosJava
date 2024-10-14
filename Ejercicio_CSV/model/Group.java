package Ejercicio_CSV.model;

import java.util.List;

public class Group {
    private long id;
    private String name;
    private String description;
    private List<User> users; //Relación many to many

    public String toString() {
        return  name + ": " + description;
    }

    public Group(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /*public void addUsers(List<User> users) {
        this.users.addAll(users);
    }*/
}