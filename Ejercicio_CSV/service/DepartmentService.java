package Ejercicio_CSV.service;

import Ejercicio_CSV.model.Department;
import Ejercicio_CSV.model.User;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private List<Department> departments = new ArrayList<>();

    // Obtener todos los departamentos
    public List<Department> getAllDepartments() {
        return departments;
    }

    // Buscar departamentos por ID
    public Department findDepartmentById(long id) {
        return departments.stream().filter(department -> department.getId()== id).findFirst().orElse(null);
    }

    // Buscar departamentos por nombre
    public List<Department> findDepartmentByName(String name){
        List<Department> result = new ArrayList<>();
        for (Department department: departments){
            if (department.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(department);
            }
        }
        return result;
    }

    // Obtener departamentos por usuario
    public List<Department> getDepartmentsByUser(long userId){
        List<Department> result = new ArrayList<>();
        for (Department department: departments){
            if (department.getUsers().stream().anyMatch(user -> user.getId() == userId)){
                result.add(department);
            }
        }
        return result;
    }

    // Crear un nuevo departamento
    public void createDepartment(Department department) {
        departments.add(department);
    }

    // Actualizar información de un departamento
    public void updateDepartment(Department department) {
        Department existingDepartment = findDepartmentById(department.getId());
        if (existingDepartment != null){
            existingDepartment.setName(department.getName());
            existingDepartment.setDescription(department.getDescription());
        }
    }

    // Eliminar un departamento
    public void deleteDepartment(long id){
        departments.removeIf(department -> department.getId() == id);
    }

    public void addUsers(long id, List<User> users) {
        Department department = findDepartmentById(id);
        List<User> departmentUsers = department.getUsers();
        departmentUsers.addAll(users);
        department.setUsers(departmentUsers);
        updateDepartment(department);
    }

    /*public void returnUsersInDept(String id) {
        Department department = findDepartmentById(id);
        List<User> users = department.getUsers();
        if (users.isEmpty()) {
            System.out.println("No hay usuarios en el departamento " + department.getName() + ".");
        } else {
            System.out.println("Usuarios en el departamento " + department + ":");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }*/
}
