package Ejercicio3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Crear círculo y calcular su area
        System.out.println("Ingrese el radio del círculo: ");
        double radio = scanner.nextDouble();
        Figura circulo = new Circulo(radio);
        System.out.println("El área del círculo es: " + circulo.calcularArea());

        //Crear un rectángulo y calcular su área
        System.out.println("Ingrese la anchura del rectángulo: ");
        double anchura = scanner.nextDouble();
        System.out.println("Ingrese la altura del rectángulo: ");
        double altura = scanner.nextDouble();
        Figura rectangulo = new Rectangulo(altura, anchura);
        System.out.println("El área del rectángulo es: " + rectangulo.calcularArea());
        scanner.close();
    }
}
