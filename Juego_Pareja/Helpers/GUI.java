package Juego_Pareja.Helpers;

import java.util.Objects;
import java.util.Scanner;

public class GUI {
    static Scanner input = new Scanner(System.in);

    public static int multipleChoice(String[] options, String title, boolean decor) {
        // clearConsole();
        // TITLE
        System.out.println();
        showText(title, decor);
        System.out.println();

        // SHOW OPTIONS
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        // CHOICE
        System.out.print("Choose an action (1-" + options.length + "): ");
        int choice = input.nextInt();
        while (choice < 1 || choice > options.length) {
            System.out.print("Enter a valid option number: ");
            choice = input.nextInt();
        }

        input.nextLine();

        return choice;
    }

    public static void showStory(String text, boolean decor) {
        showText(text, decor);

        System.out.println("\nPress any key to continue...");
        input.nextLine();
    }

    public static void showBattleText(String text, boolean decor, boolean autoContinue) {
        showText(text, decor);

        if (!autoContinue) {
            System.out.println("\nPress any key to continue...");
            input.nextLine();
        }
    }

    public static void showText(String text, boolean decor) {

        // Maximum line length
        int maxLineLength = 100;

        if (decor) {
            // Output the formatted text
            System.out.println("#".repeat(maxLineLength));
            System.out.println(text);
            System.out.println("#".repeat(maxLineLength));
        } else {
            System.out.println(text);
        }
    }

}
