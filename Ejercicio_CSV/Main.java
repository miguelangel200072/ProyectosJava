package Ejercicio_CSV;

import Ejercicio_CSV.controller.DepartmentController;
import Ejercicio_CSV.controller.GroupController;
import Ejercicio_CSV.controller.RoleController;
import Ejercicio_CSV.controller.UserController;
import Ejercicio_CSV.model.Department;
import Ejercicio_CSV.model.Group;
import Ejercicio_CSV.model.Role;
import Ejercicio_CSV.model.User;
import Ejercicio_CSV.util.CSVReader;

import java.util.List;

import static Ejercicio_CSV.util.GUI.showMenu;

public class Main {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        DepartmentController departmentController = new DepartmentController();
        GroupController groupController = new GroupController();
        RoleController roleController = new RoleController();

        // Cargar datos desde CSV
        System.out.println("Cargando datos de usuarios...");
        List<String[]> usersData = CSVReader.readCSV("src/Ejercicio_CSV/resources/usuarios.csv");
        for (String[] data : usersData.subList(1, usersData.size())) {
            User user = new User(Long.parseLong(data[0]), data[1], data[2], Integer.parseInt(data[3]));
            userController.createUser(user);
        }

        System.out.println("Cargando datos de departamentos...");
        List<String[]> departmentsData = CSVReader.readCSV("src/Ejercicio_CSV/resources/departamentos.csv");
        for (String[] data : departmentsData.subList(1, departmentsData.size())) {
            Department department = new Department(Long.parseLong(data[0]), data[1], data[2]);
            departmentController.createDepartment(department);
        }

        System.out.println("Cargando datos de grupos...");
        List<String[]> groupsData = CSVReader.readCSV("src/Ejercicio_CSV/resources/grupos.csv");
        for (String[] data : groupsData.subList(1, groupsData.size())) {
            Group group = new Group(Long.parseLong(data[0]), data[1], data[2]);
            groupController.createGroup(group);
        }

        System.out.println("Cargando datos de roles...");
        List<String[]> rolesData = CSVReader.readCSV("src/Ejercicio_CSV/resources/roles.csv");
        for (String[] data : rolesData.subList(1, rolesData.size())) {
            String permissions = data[2];

            // Check if the permissions field contains quotes
            if (permissions.startsWith("\"") && permissions.endsWith("\"")) {
                // Remove the leading and trailing quotes
                permissions = permissions.substring(1, permissions.length() - 1);
            }

            // Split the permissions by comma
            String[] permisosArray = permissions.split(",");

            // Create a Role object with parsed permissions
            Role role = new Role(Long.parseLong(data[0]), data[1], List.of(permisosArray));

            // Assuming roleController.createRole accepts an array or list of permissions
            roleController.createRole(role);
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        int exit;
        do {
            int num = showMenu("¿Qué acción desea realizar?", new String[]{
                    "Acciones sobre usuarios", "Acciones sobre departamentos",
                    "Acciones sobre grupos", "Acciones sobre roles"
            });

            switch (num) {
                case 1:
                    userController.userActions();
                    break;
                case 2:
                    departmentController.departmentActions();
                    break;
                case 3:
                    groupController.groupActions();
                    break;
                case 4:
                    roleController.roleActions();
                    break;
            }

            exit = showMenu("¿Qué desea hacer a continuación?", new String[]{
                    "Regresar al menú principal", "Salir"});

        } while (exit == 1);

    }
}
