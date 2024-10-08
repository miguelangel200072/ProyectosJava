package EjerciciosStreams;
//Filtrado de elementos pares
import java.util.Arrays;
import java.util.List;

public class Ejercicio1 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9);
        System.out.println("Los numeros pares de " + numeros + " son: ");
        //Filtrar y mostrar numeros pares
        numeros.stream().filter(it -> it%2==0).forEach(System.out::println);
    }
}
