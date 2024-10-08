package Ejercicio7;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDePersonas {
    //Lista donde se almacenan las personas
    private List<Persona> personas;

    public RepositorioDePersonas(){
        personas = new ArrayList<>();
    }

    //Método para agregar una persona
    public void agregarPersona(Persona persona){
        personas.add(persona);
        System.out.println("Persona agreagada: " + persona);
    }

    // Método para eliminar una persona por ID
    public void eliminarPersona(int id) {
        boolean encontrado = false; // Variable para verificar si se encontró la persona
        for (Persona persona : personas) {
            if (persona.getId() == id) {
                personas.remove(persona);
                System.out.println("Persona con ID " + id + " eliminada.");
                encontrado = true; // Se encontró y eliminó la persona
                break; // Salir del bucle una vez eliminada
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró persona con ID " + id + ".");
        }
    }


    // Método para listar todas las personas
    public void listarPersonas() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas en el repositorio.");
        } else {
            System.out.println("Lista de personas:");
            for (Persona persona : personas) {
                System.out.println(persona);
            }
        }
    }
}
