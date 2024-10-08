package EjerciciosStreams;

import java.util.Arrays;
import java.util.List;

// Cálculo del promedio
public class Ejercicio2 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9);
        // Convertir a int, sumar todos los elementos y dividir por el tamaño de la lista
        double promedio = numeros.stream().mapToInt(Integer::intValue).reduce(0, Integer::sum) / (double) numeros.size();
        System.out.println("El valor promedio es: "+promedio);
    }
}
