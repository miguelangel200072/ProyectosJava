package Ejercicio_CSV.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI {
    private static final Scanner input = new Scanner(System.in);

    public static int showMenu(String title, String[] options) {
        System.out.println(title);
        System.out.println();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("Seleccione una opción entre 1-" + options.length + ": ");

        // Validate the input
        while (!input.hasNextInt()) {
            System.out.println("Por favor, introduzca un número válido: ");
            input.nextLine();  // Consume the invalid input
        }

        int num = input.nextInt();
        while (num < 0 || num > options.length) {
            System.out.println("Introduzca un número válido: ");
            num = input.nextInt();
        }

        input.nextLine();

        return num;
    }

    public static List<Integer> showMenuList(String title, String[] options) {
        System.out.println(title);
        System.out.println();

        // Display the options
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        System.out.println("Seleccione opciones entre 1 y " + options.length + ", separadas por comas (ej: 1,2,3): ");

        List<Integer> selectedNumbers = new ArrayList<>();
        boolean validInput = false;

        while (!validInput) {
            String inputLine = input.nextLine();  // Get input as a string
            String[] inputValues = inputLine.split(",");  // Split by commas

            try {
                // Parse each value into an integer and validate it
                for (String value : inputValues) {
                    int num = Integer.parseInt(value.trim());  // Trim to remove spaces and parse to int

                    if (num < 1 || num > options.length) {  // Check if number is within valid range
                        throw new IllegalArgumentException("Número fuera de rango: " + num);
                    }

                    selectedNumbers.add(num);  // Add valid number to the list
                }

                validInput = true;  // If all numbers are valid, set the flag to exit loop

            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca solo números válidos separados por comas.");
                selectedNumbers.clear();  // Clear the list and re-prompt
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());  // Show range error
                selectedNumbers.clear();  // Clear the list and re-prompt
            }
        }

        return selectedNumbers;  // Return the list of selected numbers
    }

    public static String showMenuStr(String text) {
        System.out.println(text);
        String inputText = input.nextLine();
        while (inputText == null || inputText.isEmpty()) {
            System.out.println("El texto no puede estar vacío: ");
            inputText = input.nextLine();
        }
        return inputText;
    }

    public static long showMenuLong(String text) {
        System.out.println(text);
        // Validate the input
        while (!input.hasNextInt()) {
            System.out.println("Por favor, introduzca un número válido: ");
            input.nextLine();  // Consume the invalid input
        }

        return input.nextInt();
    }

    public static List<String> showMenuListStr(String title) {
        System.out.println(title);

        List<String> permissions = new ArrayList<>();

        String inputLine = input.nextLine();  // Get input
        String[] inputValues = inputLine.split(",");  // Split by commas

        for (String value : inputValues) {
            permissions.add(value.trim().toUpperCase());  // Trim and convert to uppercase
        }

        return permissions;
    }
}
