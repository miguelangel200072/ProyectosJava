package EjerciciosStreams;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ejercicio9 {
    public static void main(String[] args) {
        //Lista duplicada
        List<Integer> numeros = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6, 6, 7);
        //Filtrar los numeros únicos
        Set<Integer> numerosUnicos = numeros.stream().distinct().collect(Collectors.toSet());
        System.out.println("Números únicos: "+ numerosUnicos);
    }
}
