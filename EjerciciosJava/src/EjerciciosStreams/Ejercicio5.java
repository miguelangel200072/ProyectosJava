package EjerciciosStreams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio5 {
    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Miguel", "Ángel", "Juan", "Víctor");
        //Concatenar nombres en una sola cadena, separados por coma
        String resultado = nombres.stream().collect(Collectors.joining(","));

        System.out.println("Nombres concatenados: " +resultado);
    }
}
