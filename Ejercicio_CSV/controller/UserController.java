package Ejercicio_CSV.controller;

import Ejercicio_CSV.model.Department;
import Ejercicio_CSV.model.User;
import Ejercicio_CSV.service.UserService;

import java.util.List;

import static Ejercicio_CSV.util.GUI.*;

public class UserController {
    private final UserService userService = new UserService();

    public void userActions() {
        int back;
        do {
            int num = showMenu("Opciones de gestión de usuarios:", new String[]{
                    "Mostrar todos los usuarios", "Buscar usuario por ID", "Buscar usuarios por nombre",
                    "Mostrar los departamentos de un usuario", "Asignar roles a un usuario",
                    "Mostrar permisos de un usuario", "Crear usuario", "Actualizar usuario", "Eliminar usuario"});

            switch (num) {
                case 1:
                    showAllUsers();
                    break;
                case 2:
                    findUserById();
                    break;
                case 3:
                    findUsersByName();
                    break;
                case 4:
                    showUserDepartments();
                    break;
                case 5:
                    assignRolesToUser();
                    break;
                case 6:
                    showUserPermissions();
                    break;
                case 7:
                    createUser(new User(0,"", "", 0));
                    break;
                case 8:
                    updateUser();
                    break;
                case 9:
                    deleteUser();
                    break;
            }

            back = showMenu("¿Qué desea hacer?", new String[] {
                    "Regresar a opciones de usuarios", "Regresar a menú principal"});

        } while (back == 1);
    }

    private void showAllUsers(){
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No hay usuarios disponibles.");
        } else {
            System.out.println("Usuarios existentes: ");
            for (User user : users) {
                System.out.println("- " + user);
            }
        }
    }

    public void createUser(User user) {
        // Create User from Input if parameter user is empty
        if (user.getId() == 0) {
            user.setId(userService.getAllUsers().stream().mapToLong(User::getId).max().orElse(1));
            user.setName(showMenuStr("Introduzca el nombre del usuario: "));
            user.setEmail(showMenuStr("Introduzca el correo del usuario: "));
            user.setAge(Integer.parseInt(showMenuStr("Introduzca la edad del usuario: ")));
        }

        userService.createUser(user);
        System.out.println("Usuario creado: " + user.getName());
    }

    private void updateUser() {
        List<User> userList = userService.getAllUsers();
        String[] userNames = userList.stream().map(User::getName).toArray(String[]::new);
        int resultUser = showMenu("Seleccione un usuario para modificar: ", userNames)-1;
        User user = userList.get(resultUser);

        int num = showMenu("¿Desea modificar el nombre? Nombre actual: " + user.getName(), new String[] {"Modificar nombre", "No modificar nombre"});
        if (num == 1) {
            user.setName(showMenuStr("Introduzca el nombre modificado: "));
        }

        num = showMenu("¿Desea modificar el correo? Correo actual: " + user.getEmail(), new String[] {"Modificar correo", "No modificar correo"});
        if (num == 1) {
            user.setEmail(showMenuStr("Introduzca el correo modificado: "));
        }

        num = showMenu("¿Desea modificar la edad? Edad actual: " + user.getAge(), new String[] {"Modificar edad", "No modificar edad"});
        if (num == 1) {
            user.setAge(Integer.parseInt(showMenuStr("Introduzca la edad modificada: ")));
        }

        userService.updateUser(user);
        System.out.println("Usuario actualizado: " + user.getName());
    }

    private void deleteUser() {
        // Verificar si hay usuarios disponibles
        if (userService.getAllUsers().isEmpty()){
            System.out.println("No hay usuarios disponibles.");
            return;
        }

        String[] userNames = userService.getAllUsers().stream().map(User::getName).toArray(String[]::new);
        int resultUser = showMenu("Seleccione un usuario para eliminar: ", userNames);
        long id = userService.getAllUsers().get(resultUser-1).getId();
        userService.deleteUser(id);
        System.out.println("Usuario  eliminado.");
    }

    private void findUserById() {
        long id = showMenuLong("Introduzca un ID para buscar un usuario: ");
        User user = userService.findUserById(id);

        if (user == null) {
            System.out.println("Usuario no encontrado.");
        } else {
            System.out.println("Usuario encontrado:\n- " + user);
        }
    }

    private void findUsersByName() {
        String name = showMenuStr("Introduzca el nombre a buscar: ");
        List<User> users = userService.findUsersByName(name);

        if (users.isEmpty()) {
            System.out.println("No se encontraron usuarios.");
        } else {
            System.out.println("Usuarios encontrados: ");
            for (User user : users) {
                System.out.println("- " + user);
            }
        }
    }

    private void showUserDepartments() {
        List<User> userList = userService.getAllUsers();
        String[] userNames = userList.stream().map(User::getName).toArray(String[]::new);
        int resultDept = showMenu("Seleccione un usuario para ver sus departamentos: ", userNames)-1;

        List<Department> departments = userList.get(resultDept).getDepartments();

        if (departments == null) {
            System.out.println("El usuario " + userList.get(resultDept).getName() + " no pertenece a ningún departamento.");
        } else {
            System.out.println("Departamentos a los que pertenece el usuario " + userList.get(resultDept).getName() + ":");
            for (Department department : departments) {
                System.out.println("- " + department);
            }
        }
    }

    //TODO implement
    private void assignRolesToUser() {

    }

    private void showUserPermissions() {

    }
}
