package Ejercicio_CSV.service;

import Ejercicio_CSV.model.Group;
import Ejercicio_CSV.model.Role;
import Ejercicio_CSV.model.User;

import java.util.ArrayList;
import java.util.List;

public class RoleService {
    List<Role> roles = new ArrayList<>();

    // Obtener todos los roles
    public List<Role> getAllRoles(){
        return roles;
    }

    // Obtener rol por su ID
    public Role findRoleById(long id){
        return roles.stream().filter(role -> role.getId() == id).findFirst().orElse(null);
    }

    // Buscar roles por el ID del usuario
    public List<Role> findRolesByUser(long userId){
        for (Role role : roles) {
            for (User user : role.getUsers()) {
                if (user.getId() == userId) {
                    return user.getRoles();
                }
            }
        }

        // Si no se encuentra, se devuelve una lista vacía
        return new ArrayList<>();
    }

    // Crear un nuevo rol
    public void createRole(Role role) {
        roles.add(role);
    }

    // Actualizar un rol
    public void updateRole(Role role){
        Role existingRole = findRoleById(role.getId());
        if (existingRole != null){
            existingRole.setName(role.getName());
            existingRole.setPermissions(role.getPermissions());
        }
    }

    // Eliminar un rol por su ID
    public void deleteRole(long id){
        roles.removeIf(role -> role.getId() == id);
    }
}
