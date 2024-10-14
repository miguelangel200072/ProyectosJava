package Ejercicio_CSV.controller;

import Ejercicio_CSV.model.Department;
import Ejercicio_CSV.model.Group;
import Ejercicio_CSV.model.User;
import Ejercicio_CSV.service.GroupService;

import java.util.List;
import java.util.Scanner;

import static Ejercicio_CSV.util.GUI.*;

public class GroupController {
    private final GroupService groupService = new GroupService();

    public void groupActions() {
        int back;
        do {
            int num = showMenu("Opciones de gestión de grupos:", new String[]{
                    "Mostrar todos los grupos", "Buscar grupo por ID", "Mostrar los usuarios de un grupo",
                    "Crear departamento", "Actualizar departamento", "Eliminar departamento"});

            switch (num) {
                case 1:
                    showAllGroups();
                    break;
                case 2:
                    findGroupById();
                    break;
                case 3:
                    showUsersInGroup();
                    break;
                case 4:
                    createGroup(new Group(0, "", ""));
                    break;
                case 5:
                    updateGroup();
                    break;
                case 6:
                    deleteGroup();
                    break;
            }

            back = showMenu("¿Qué desea hacer?", new String[] {
                    "Regresar a opciones de grupos", "Regresar a menú principal"});

        } while (back == 1);
    }

    private void showAllGroups() {
        List<Group> groups = groupService.getAllGroups();

        if (groups.isEmpty()) {
            System.out.println("No hay grupos disponibles.");
        } else {
            System.out.println("Grupos existentes: ");
            for (Group group : groups) {
                System.out.println("- " + group);
            }
        }
    }

    public void createGroup(Group group) {
        if (group.getId() == 0) {
            group.setId(groupService.getAllGroups().stream().mapToLong(Group::getId).max().orElse(0)+1);
            group.setName(showMenuStr("Introduzca el nombre del grupo: "));
            group.setDescription(showMenuStr("Introduzca la descripción del grupo: "));
        }
        groupService.createGroup(group);
        System.out.println("Grupo creado: " + group.getName());
    }

    private void updateGroup() {
        List<Group> groupList = groupService.getAllGroups();
        String[] groupNames = groupList.stream().map(Group::getName).toArray(String[]::new);
        int resultGroup = showMenu("Seleccione un grupo para modificar: ", groupNames)-1;
        Group group = groupList.get(resultGroup);

        int num = showMenu("¿Desea modificar el nombre? Nombre actual: " + group.getName(), new String[] {"Modificar nombre", "No modificar nombre"});
        if (num == 1) {
            group.setName(showMenuStr("Introduzca el nombre modificado: "));
        }

        num = showMenu("¿Desea modificar la descripción?\nDescripción actual: " + group.getDescription(), new String[] {"Modificar descripción", "No modificar descripción"});
        if (num == 1) {
            group.setDescription(showMenuStr("Introduzca la descripción modificada: "));
        }
        groupService.updateGroup(group);
        System.out.println("Grupo Modificado: " + group.getName());
    }

    private void deleteGroup() {
        // Verificar si hay grupos disponibles
        if (groupService.getAllGroups().isEmpty()){
            System.out.println("No hay grupos disponibles.");
            return;
        }

        String[] groupNames = groupService.getAllGroups().stream().map(Group::getName).toArray(String[]::new);
        int resultGroup = showMenu("Seleccione un grupo para eliminar: ", groupNames);
        long id = groupService.getAllGroups().get(resultGroup-1).getId();

        groupService.deleteGroup(id);
        System.out.println("Grupo eliminado.");
    }

    private void findGroupById() {
        long id = showMenuLong("Introduzca un ID para buscar un grupo: ");
        Group group = groupService.findGroupById(id);

        if (group == null) {
            System.out.println("Grupo no encontrado.");
        } else {
            System.out.println("Grupo encontrado:\n- " + group);
        }
    }

    private void showUsersInGroup() {
        List<Group> groupList = groupService.getAllGroups();
        String[] groupNames = groupList.stream().map(Group::getName).toArray(String[]::new);
        int resultDept = showMenu("Seleccione un grupo para ver sus usuarios: ", groupNames)-1;

        List<User> users = groupList.get(resultDept).getUsers();

        if (users == null) {
            System.out.println("No hay usuarios en el grupo: " + groupList.get(resultDept).getName());
        } else {
            System.out.println("Usuarios en el grupo " + groupList.get(resultDept).getName() + ":");
            for (User user : users) {
                System.out.println("- " + user);
            }
        }
    }

}

