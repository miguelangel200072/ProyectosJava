package Ejercicio_CSV.model;

import java.util.List;

public class User {
    private long id;
    private String name;
    private String email;
    private int age;
    private List<Department> departments; // Relación many to many
    private List<Role> roles; // Relación many to many

    public User(long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /*public void addRoles(List<Role> roles) { this.roles.addAll(roles); }
    public void removeRoles(List<Role> roles) {
        for (int i = 0; i < (long) this.roles.size(); i++) {
            for (Role role : roles) {
                if (role == this.roles.get(i)) {
                    this.roles.remove(i);
                }
            }
        }
    }*/

    @Override
    public String toString() {
        return  name + ", " + email + ", " + age + " años.";
    }
}