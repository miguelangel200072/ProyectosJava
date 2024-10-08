package EjerciciosStreams;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Ejercicio10 {
    public static void main(String[] args) {
        //Lista de fechas
        List<LocalDate> fechas = Arrays.asList(
                LocalDate.of(2019, 12, 31),
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 5, 15),
                LocalDate.of(2021, 3, 10),
                LocalDate.of(2023, 8, 25)
        );

        //Filtrar fechas posteriores al 1 de enero de 2020
        LocalDate fechaLimite = LocalDate.of(2020,1,1);
        List<LocalDate> fechasFiltradas = fechas.stream().filter(fecha -> fecha.isAfter(fechaLimite)).toList();

        // Mostrar las fechas filtradas
        System.out.println("Fechas posteriores al 1 de enero de 2020:");
        fechasFiltradas.forEach(System.out::println);
    }
}
