package Ejercicio4;

import java.util.Scanner;
// Enum que representa los días de la semana
enum DiaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese un día
        System.out.print("Ingresa un día de la semana (LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO): ");
        String diaIngresado = scanner.nextLine().toUpperCase();

        // Determinar si es un día laboral o un fin de semana
        try {
            DiaSemana dia = DiaSemana.valueOf(diaIngresado);
            switch (dia) {
                case LUNES:
                case MARTES:
                case MIERCOLES:
                case JUEVES:
                case VIERNES:
                    System.out.println(dia + " es un día laboral.");
                    break;
                case SABADO:
                case DOMINGO:
                    System.out.println(dia + " es un fin de semana.");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Día inválido. Por favor, ingresa un día de la semana válido.");
        }

        scanner.close();

    }
}
