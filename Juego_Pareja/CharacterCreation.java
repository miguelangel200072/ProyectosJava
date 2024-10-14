package Juego_Pareja;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Juego_Pareja.Helpers.GUI.multipleChoice;

public class CharacterCreation {

    // Función estática para crear a un personaje
    public static Character createCharacter() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your character's name: ");
        String name = sc.nextLine();
        while (name == null || name.isEmpty()) {
            System.out.print("The name cannot be empty, enter your character's name: ");
            name = sc.nextLine();
        }

        int chosenClass = multipleChoice(new String[] {"Warrior", "Mage", "Archer"}, "Choose your class: ", false);

        String characterClass = "";
        int strength = 0, magic = 0, agility = 0, endurance = 0;
        List<Item> inventory = new ArrayList<>();

        switch (chosenClass) {
            case 1:
                characterClass = "Warrior";
                strength = 10; magic = 2; agility = 5; endurance = 10;
                inventory.add(new Item("Strength Potion", "Potion", 3));
                break;
            case 2:
                characterClass = "Mage";
                strength = 2; magic = 10; agility = 5; endurance = 5;
                inventory.add(new Item("Mana Potion", "Potion", 3));
                break;
            case 3:
                characterClass = "Archer";
                strength = 5; magic = 3; agility = 10; endurance = 7;
                inventory.add(new Item("Endurance Potion", "Potion", 3));
                break;
        }

        int chosenWeapon = multipleChoice(new String[]{"Sword", "Bow", "Magic Staff"}, "Choose your weapon:", false);

        String weapon = "";
        switch (chosenWeapon) {
            case 1:
                weapon = "Sword";
                strength += 5;
                break;
            case 2:
                weapon = "Bow";
                agility += 5;
                break;
            case 3:
                weapon = "Magic Staff";
                magic += 5;
                break;
        }

        // Validar la selección
        int chosenArmor = multipleChoice(new String[]{"Light", "Heavy"}, "Choose your armor:", false);


        String armor = "";
        if (chosenArmor == 1) {
            armor = "Light";
            endurance += 5;
        } else {
            armor = "Heavy";
            endurance += 10;
        }

        Character character = new Character(name, characterClass, strength, magic, agility, endurance, weapon, armor, inventory);

        distributePoints(character, 5);

        return character;  // Return the created character
    }

    // Función estática para distribuir los puntos iniciales
    public static void distributePoints(Character character, int points) {

        while (points > 0) {
            int choice = multipleChoice(new String[]{"Strength", "Magic", "Agility", "Endurance"}, "You have " + points + " points to distribute. Choose an attribute to improve:", false);

            switch (choice) {
                case 1:
                    character.setStrength(character.getStrength() + 1);
                    break;
                case 2:
                    character.setMagic(character.getMagic() + 1);
                    break;
                case 3:
                    character.setAgility(character.getAgility() + 1);
                    break;
                case 4:
                    character.setEndurance(character.getEndurance() + 1);
                    character.setHealth(character.getHealth() + 10);  // Increase health when improving endurance
                    break;
            }
            points--;
        }
    }
}
