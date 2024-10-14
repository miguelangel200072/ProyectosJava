package Ejercicio_CSV.controller;

import Ejercicio_CSV.model.Group;
import Ejercicio_CSV.model.Role;
import Ejercicio_CSV.model.Role;
import Ejercicio_CSV.model.Role;
import Ejercicio_CSV.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Ejercicio_CSV.util.GUI.*;
import static Ejercicio_CSV.util.GUI.showMenu;

public class RoleController {
    private final RoleService roleService = new RoleService();

    public void roleActions() {
        int back;
        do {
            int num = showMenu("Opciones de gestión de grupos:", new String[]{
                    "Mostrar todos los roles", "Buscar rol por ID",
                    "Crear rol", "Actualizar rol", "Eliminar rol"});

            switch (num) {
                case 1:
                    showAllRoles();
                    break;
                case 2:
                    findRoleById();
                    break;
                case 3:
                    createRole(new Role(0, "", new ArrayList<>()));
                    break;
                case 4:
                    updateRole();
                    break;
                case 5:
                    deleteRole();
                    break;
            }

            back = showMenu("¿Qué desea hacer?", new String[] {
                    "Regresar a opciones de roles", "Regresar a menú principal"});

        } while (back == 1);
    }

    private void showAllRoles() {
        List<Role> roles = roleService.getAllRoles();

        if (roles.isEmpty()) {
            System.out.println("No hay roles disponibles.");
        } else {
            System.out.println("Roles existentes: ");
            for (Role role : roles) {
                System.out.println("- " + role.getName());
            }
        }
    }

    public void createRole(Role role) {
        if (role.getId() == 0) {
            role.setId(roleService.getAllRoles().stream().mapToLong(Role::getId).max().orElse(0)+1);
            role.setName(showMenuStr("Introduzca el nombre del rol: "));
            role.setPermissions(showMenuListStr("Introduzca los permisos del rol, separados por comas (EJEMPLO,EJEMPLO2): "));
        }
        roleService.createRole(role);
        System.out.println("Rol creado: " + role.getName());
    }

    private void updateRole() {
        List<Role> roleList = roleService.getAllRoles();
        String[] roleNames = roleList.stream().map(Role::getName).toArray(String[]::new);
        int resultRole = showMenu("Seleccione un rol para modificar: ", roleNames)-1;
        Role role = roleList.get(resultRole);

        int num = showMenu("¿Desea modificar el nombre? Nombre actual: " + role.getName(), new String[] {"Modificar nombre", "No modificar nombre"});
        if (num == 1) {
            role.setName(showMenuStr("Introduzca el nombre modificado: "));
        }

        num = showMenu("¿Desea modificar los permisos?\nPermisos actuales: " + role.getPermissions(), new String[] {"Modificar permisos", "No modificar permisos"});
        if (num == 1) {
            role.setPermissions(showMenuListStr("Introduzca los permisos del rol, separados por comas (EJEMPLO,EJEMPLO2): "));
        }
        roleService.updateRole(role);
        System.out.println("Rol modificado: " + role.getName());
    }

    private void deleteRole() {
        if (roleService.getAllRoles().isEmpty()){
            System.out.println("No hay roles disponibles.");
            return;
        }

        String[] roleNames = roleService.getAllRoles().stream().map(Role::getName).toArray(String[]::new);
        int resultRole = showMenu("Seleccione un rol para eliminar: ", roleNames);
        long id = roleService.getAllRoles().get(resultRole-1).getId();

        roleService.deleteRole(id);
        System.out.println("Rol eliminado.");
    }

    private void findRoleById() {
        long id = showMenuLong("Introduzca un ID para buscar un rol: ");
        Role role = roleService.findRoleById(id);

        if (role == null) {
            System.out.println("Rol no encontrado.");
        } else {
            System.out.println("Rol encontrado:\n- " + role.getName());
        }
    }
}
