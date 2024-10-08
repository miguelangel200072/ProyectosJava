package EjerciciosStreams;

import java.util.Arrays;
import java.util.List;

public class Ejercicio4 {
    public static void main(String[] args) {

        List<String> cadenas = Arrays.asList("Tomate", "Pera", "Limón", "Melón", "Kiwi", "aguacate");
        //Filtrar segun la longitud de la cadena
        System.out.println(cadenas.stream().filter(it -> it.length() > 5).toList());
    }
}
