package Ejercicio_CSV.service;

import Ejercicio_CSV.model.*;
import Ejercicio_CSV.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers(){
        return users;
    }

    public User findUserById(long id){
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public List<User> findUsersByName(String name){
        List<User> match = new ArrayList<>();

        for (User user: users){
            if (user.getName().toLowerCase().contains(name.toLowerCase())){
                match.add(user);
            }
        }
        return match;
    }

    public List<User> getUsersByDepartment(long departmentId){
        List<User> result = new ArrayList<>();
        for (User user: users){
            if (user.getDepartments().stream().anyMatch(department -> department.getId() == departmentId)){
                result.add(user);
            }
        }
        return result;
    }

    public void createUser(User user){
        users.add(user);
    }

    public void updateUser(User user){
        User existingUser = findUserById(user.getId());

        if (existingUser != null){
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAge(user.getAge());
            existingUser.setDepartments(user.getDepartments());
            existingUser.setRoles(user.getRoles());
        }
    }

    public void deleteUser(long id){
        users.removeIf(user->user.getId() == id);
    }

    //TODO
    // Modify this so that it receives a list of ids instead of a list of roles
    public void asignRoles(long id, List<Role> roles) {
        User user = findUserById(id);
        if (user != null) {
            List<Role> currentUserRoles = user.getRoles();
            currentUserRoles.addAll(roles);
            user.setRoles(currentUserRoles);
        }
    }

    public boolean verifyUserPermissions(long id, String permission) {
        User user = findUserById(id);
        for (Role role : user.getRoles()) {
            for (String perm : role.getPermissions()) {
                if (Objects.equals(perm, permission)) {
                    return true;
                }
            }
        }
        return false;
    }


}
