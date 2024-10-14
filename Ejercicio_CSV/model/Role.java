package Ejercicio_CSV.model;

import java.util.List;

public class Role {
    private long id;
    private String name;
    private List<String> permissions; // Relación many to many
    private List<User> users; // Relación many to many

    public Role(long id, String name, List<String> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public String toString() {
        return  name;
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

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}