package Ejercicio_CSV.controller;

import Ejercicio_CSV.model.Department;
import Ejercicio_CSV.model.User;
import Ejercicio_CSV.service.DepartmentService;

import java.util.List;
import java.util.Scanner;

import static Ejercicio_CSV.util.GUI.*;

public class DepartmentController {
    private final DepartmentService departmentService = new DepartmentService();

    public void departmentActions() {
        int back;
        do {
            int num = showMenu("Opciones de gestión de departamentos:", new String[]{
                    "Mostrar todos los departamentos", "Buscar departamento por ID", "Buscar departamentos por nombre",
                    "Mostrar los usuarios de un departamento", "Crear departamento", "Actualizar departamento", "Eliminar departamento"});

            switch (num) {
                case 1:
                    showAllDepartments();
                    break;
                case 2:
                    findDepartmentById();
                    break;
                case 3:
                    findDepartmentsByName();
                    break;
                case 4:
                    showUsersInDept();
                    break;
                case 5:
                    createDepartment(new Department(0, "", ""));
                    break;
                case 6:
                    updateDepartment();
                    break;
                case 7:
                    deleteDepartment();
                    break;
            }

            back = showMenu("¿Qué desea hacer?", new String[] {
                    "Regresar a opciones de departamentos", "Regresar a menú principal"});

        } while (back == 1);
    }

    private void showAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("No hay departamentos disponibles.");
        } else {
            System.out.println("Departamentos existentes: ");
            for (Department department : departments) {
                System.out.println("- " + department);
            }
        }
    }

    public void createDepartment(Department department) {
        if (department.getId() == 0) {
            department.setId(departmentService.getAllDepartments().stream().mapToLong(Department::getId).max().orElse(0)+1);
            department.setName(showMenuStr("Introduzca el nombre del departamento: "));
            department.setDescription(showMenuStr("Introduzca la descripción del departamento: "));
        }

        departmentService.createDepartment(department);
        System.out.println("Departamento creado: " + department.getName());
    }

    private void updateDepartment() {
        List<Department> departmentList = departmentService.getAllDepartments();
        String[] departmentNames = departmentList.stream().map(Department::getName).toArray(String[]::new);
        int resultDepartment = showMenu("Seleccione un departamento para modificar: ", departmentNames)-1;
        Department department = departmentList.get(resultDepartment);

        int num = showMenu("¿Desea modificar el nombre? Nombre actual: " + department.getName(), new String[] {"Modificar nombre", "No modificar nombre"});
        if (num == 1) {
            department.setName(showMenuStr("Introduzca el nombre modificado: "));
        }

        num = showMenu("¿Desea modificar la descripción?\nDescripción actual: " + department.getDescription(), new String[] {"Modificar descripción", "No modificar descripción"});
        if (num == 1) {
            department.setDescription(showMenuStr("Introduzca la descripción modificada: "));
        }

        departmentService.updateDepartment(department);
        System.out.println("Departamento modificado: " + department.getName());
    }

    private void deleteDepartment() {
        // Verificar si hay departamentos disponibles
        if (departmentService.getAllDepartments().isEmpty()){
            System.out.println("No hay usuarios disponibles.");
            return;
        }

        String[] departmentNames = departmentService.getAllDepartments().stream().map(Department::getName).toArray(String[]::new);
        int resultDepartment = showMenu("Seleccione un usuario para eliminar: ", departmentNames);
        long id = departmentService.getAllDepartments().get(resultDepartment-1).getId();

        departmentService.deleteDepartment(id);
        System.out.println("Departamento eliminado.");
    }

    private void findDepartmentById() {
        long id = showMenuLong("Introduzca un ID para buscar un departamento: ");
        Department department = departmentService.findDepartmentById(id);

        if (department == null) {
            System.out.println("Departamento no encontrado.");
        } else {
            System.out.println("Departamento encontrado:\n- " + department);
        }
    }

    private void findDepartmentsByName() {
        String name = showMenuStr("Introduzca el nombre a buscar: ");
        List<Department> departments = departmentService.findDepartmentByName(name);

        if (departments.isEmpty()) {
            System.out.println("No se encontraron departamentos.");
        } else {
            System.out.println("Departamentos encontrados: ");
            for (Department department : departments) {
                System.out.println("- " + department);
            }
        }
    }

    private void showUsersInDept() {
        List<Department> departmentList = departmentService.getAllDepartments();
        String[] departmentNames = departmentList.stream().map(Department::getName).toArray(String[]::new);
        int resultDept = showMenu("Seleccione un departamento para ver sus usuarios: ", departmentNames)-1;

        List<User> users = departmentList.get(resultDept).getUsers();

        if (users == null) {
            System.out.println("No hay usuarios en el departamento: " + departmentList.get(resultDept).getName());
        } else {
            System.out.println("Usuarios en el departamento " + departmentList.get(resultDept).getName() + ":");
            for (User user : users) {
                System.out.println("- " + user);
            }
        }
    }
}
