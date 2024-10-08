package EjerciciosStreams;

import java.util.Arrays;
import java.util.List;

class Persona {
    String nombre;
    int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad;
    }
}
public class Ejercicio7 {
    public static void main(String[] args) {
        List<Persona> personas = Arrays.asList(
                new Persona("Juan", 25),
                new Persona("María", 35),
                new Persona("Pedro", 40),
                new Persona("Ana", 28)
        );

        // Encontrar la primera persona que tenga más de 30 años (o null si no se encuentra)
        Persona personaMayor30 = personas.stream().filter(persona -> persona.getEdad() > 30).findFirst().orElse(null); // Devolver null si no se encuentra


        System.out.println("La primera persona mayor de 30 años es: " + personaMayor30.getNombre());
    }
}
