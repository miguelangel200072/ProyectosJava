package EjerciciosStreams;

import java.util.Arrays;
import java.util.List;

public class Ejercicio6 {
    public static void main(String[] args) {

        List<Integer> numerosEnteros = Arrays.asList(1,10,20,30,9,2,110);
        //Filtrar numeros mayores de 10
        System.out.println(numerosEnteros.stream().filter(n->n>10).count());
    }

}
