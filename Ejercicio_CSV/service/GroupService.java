package Ejercicio_CSV.service;

import Ejercicio_CSV.model.Department;
import Ejercicio_CSV.model.Group;
import Ejercicio_CSV.model.User;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private List<Group> groups = new ArrayList<>();

    // Obtener todos los grupos
    public List<Group> getAllGroups(){
        return groups;
    }

    //Buscar grupo por ID
    public  Group findGroupById(long id){
        return groups.stream().filter(group -> group.getId()== id).findFirst().orElse(null);
    }

    // Obtener grupos por usuario
    public List<Group> getGroupsByUser(long userId){
        List<Group> result = new ArrayList<>();
        for (Group group: groups){
            if (group.getUsers().stream().anyMatch(user -> user.getId()== userId)){
                result.add(group);
            }
        }
        return result;
    }

    // Crear un nuevo grupo
    public void createGroup(Group group) {
        groups.add(group);
    }

    // Actualizar información de un grupo
    public void updateGroup(Group group){
        Group existingGroup = findGroupById(group.getId());
        if (existingGroup != null){
            existingGroup.setName(group.getName());
            existingGroup.setDescription(group.getDescription());
        }
    }

    // Eliminar un grupo
    public void deleteGroup(long id) {
        groups.removeIf(group -> group.getId() == id);
    }

    public void addUsers(long id, List<User> users) {
        Group group = findGroupById(id);
        List<User> groupUsers = group.getUsers();
        groupUsers.addAll(users);
        group.setUsers(groupUsers);
        updateGroup(group);
    }

    /*public void returnUsersInGroup(String id) {
        Group group = findGroupById(id);
        List<User> users = group.getUsers();
        if (users.isEmpty()) {
            System.out.println("No hay usuarios en el grupo " + group.getName() + ".");
        } else {
            System.out.println("Usuarios en el grupo " + group + ":");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    */
}