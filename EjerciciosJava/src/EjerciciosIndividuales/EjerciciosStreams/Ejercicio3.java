package EjerciciosStreams;

import java.util.Arrays;
import java.util.List;

public class Ejercicio3 {
    public static void main(String[] args) {
        List<String> palabras = Arrays.asList("Hola", "Adios", "Buenas", "Hasta Luego");
        //Convertir palabras a mayusculas
        List<String> palabrasMayusculas = palabras.stream().map(String::toUpperCase).toList();
        //Imprimir la nueva lista de palabras
        System.out.println(palabrasMayusculas);
    }
}
