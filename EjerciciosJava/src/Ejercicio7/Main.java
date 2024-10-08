package Ejercicio7;

public class Main {
    public static void main(String[] args) {
        RepositorioDePersonas repositorio = new RepositorioDePersonas();

        // Crear algunas personas
        Persona persona1 = new Persona(1, "Juan Pérez");
        Persona persona2 = new Persona(2, "Ana Gómez");
        Persona persona3 = new Persona(3, "Luis Rodríguez");

        // Agregar personas al repositorio
        repositorio.agregarPersona(persona1);
        repositorio.agregarPersona(persona2);
        repositorio.agregarPersona(persona3);

        // Listar personas
        repositorio.listarPersonas();

        // Eliminar una persona
        repositorio.eliminarPersona(2);

        // Listar personas después de la eliminación
        repositorio.listarPersonas();
    }
}