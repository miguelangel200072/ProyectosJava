package EjerciciosStreams;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ejercicio8 {
    public static void main(String[] args) {
        List<Persona> personas = Arrays.asList(
                new Persona("Juan", 25),
                new Persona("María", 17),
                new Persona("Pedro", 40),
                new Persona("Ana", 15),
                new Persona("Carlos", 18)
        );

        //Filtrar personas mayores de 18 y almacenar en una lista
        List<Persona> mayorDe18 = personas.stream().filter(persona -> persona.getEdad() >=18).collect(Collectors.toList());

        System.out.println("Mayores de 18: ");
        mayorDe18.forEach(System.out::println);

        //Filtrar personas menores de 18 y almacenar en una lista
        List<Persona> menorDe18 = personas.stream().filter(persona -> persona.getEdad() < 18).collect(Collectors.toList());

        System.out.println("\nMenores de 18: ");
        menorDe18.forEach(System.out::println);
    }
}
