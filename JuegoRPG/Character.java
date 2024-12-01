package Juego_Pareja;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Character {
    private final String name;
    private final String characterClass;
    private int strength;
    private int magic;
    private int agility;
    private int endurance;
    private int health;
    private int mana;
    private int exp = 0;
    private int sinPoints = 0;
    private List<Item> inventory;
    private String state = "Normal";
    private int level = 1;

    //Constructor
    public Character(String name, String characterClass, int strength, int magic, int agility, int endurance, String weapon, String armor, List<Item> inventory) {
        this.name = name;
        this.characterClass = characterClass;
        this.strength = strength;
        this.magic = magic;
        this.agility = agility;
        this.endurance = endurance;
        this.health = endurance * 10;  // Health depende de endurance
        this.mana = magic * 5;  // El mana depende de la magia
        this.inventory = inventory;
    }

    public void checkLevelUp(){
        if (exp >= 10){
            level ++;
            exp -= 10;
            System.out.println(name + " has leveled up! Now at level " + level);
            System.out.println("You can now increase one attribute by 1 point.");

            // Subir atributos
            distributeLevelUpPoint();
        }
    }

    // Función para distribuir puntos al subir de nivel
    public void distributeLevelUpPoint() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Choose an attribute to increase: 1. Strength 2. Magic 3. Agility 4. Endurance");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1, 2, 3, or 4):");
                sc.next(); // Consumir entrada inválida
            }
            choice = sc.nextInt();
        } while (choice < 1 || choice > 4);

        switch (choice) {
            case 1 -> {
                strength++;
                System.out.println("Strength increased to " + strength);
            }
            case 2 -> {
                magic++;
                System.out.println("Magic increased to " + magic);
            }
            case 3 -> {
                agility++;
                System.out.println("Agility increased to " + agility);
            }
            case 4 -> {
                endurance++;
                System.out.println("Endurance increased to " + endurance);
            }
        }
    }

    public void showCharacterInfo() {
        System.out.println("\nCurrent status of " + name + ":");
        System.out.println("Level: " + level);
        System.out.println("Class: " + characterClass);
        System.out.println("Health: " + health);
        System.out.println("Mana: " + mana);
        System.out.println("Strength: " + strength);
        System.out.println("Magic: " + magic);
        System.out.println("Agility: " + agility);
        System.out.println("Endurance: " + endurance);
    }

    //Función de ataque básico
    public int attack() {
        return strength;
    }

    //Función para recibir daño
    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;  // La salud no debe ser negativa
        }
        System.out.println(name + " receives " + damage + " points of damage.");
    }

    // Función para aplicar el estado de aturdido o envenenado
    public void applyState(String newState) {
        this.state = newState;
        System.out.println(name + " is now " + state + ".");
    }

    // Función para verificar si el personaje está aturdido
    public boolean isStunned() {
        return "Stunned".equals(state);
    }

    // Función para verificar si el personaje está envenenado
    public boolean isPoisoned() {
        return "Poisoned".equals(state);
    }

    // Resetear estado
    public void resetState() {
        this.state = "Normal";
    }

    //Función para utilizar la habilidad del personaje
    public int useAbility() {
        switch (characterClass) {
            case "Warrior":
                if (endurance >= 5) {
                    System.out.println(name + " uses their special ability: Devastating Strike!");
                    endurance -= 5;
                    return strength;
                } else {
                    System.out.println("Not enough endurance.");
                    return 0;
                }
            case "Mage":
                if (mana >= 10) {
                    System.out.println(name + " casts a fire spell.");
                    mana -= 10;
                    return magic * 2;
                } else {
                    System.out.println("Not enough mana.");
                    return 0;
                }

            case "Archer":
                if (endurance <= 0){
                    System.out.println(name + " shoots a precise arrow.");
                    endurance -= 5;
                    return agility;
                } else {
                    System.out.println("Not enough endurance.");
                    return 0;
                }
            default:
                System.out.println("Unknown class.");
                return  0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void addItem(Item item){
        inventory.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

    public List<Item> returnPotionList() {
        List<Item> items = new ArrayList<>();
        for (Item item : inventory) {
            if (Objects.equals(item.getType(), "Potion")) {
                items.add(item);
            }
        }
        return items;
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(itemName)) {
                inventory.remove(i);
                return;
            }
        }
    }

    public void useItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getName().equals(itemName)) {
                if (item.getAmount() > 0) {
                    item.use();
                    applyItemEffect(item);
                    // Verifica si la cantidad del objeto es cero
                    if (item.getAmount() == 0) {
                        inventory.remove(i); // Elimina el objeto del inventario
                        System.out.println(itemName + " has been removed from inventory.");
                    }
                } else {
                    System.out.println("You have no more " + itemName + " to use.");
                }
                return;
            }
        }
        System.out.println("You don't have " + itemName + " in your inventory");
    }

    private void applyItemEffect(Item item){
        switch (item.getName()){
            case "Health Potion":
                health += 20;
                System.out.println("You used a health potion. Your health increased to: " +health);
                break;
            case "Mana Potion":
                mana += 20;
                System.out.println("You used a mana potion. Your mana increased to: " +mana);
                break;
            default:
                System.out.println("Not a usable item.");
        }
    }

    // Getters y Setters
    public String getName() {
        return name;
    }
    public int getStrength(){ return strength; }
    public void setStrength(int strength){ this.strength = strength;}
    public int getMagic() {
        return magic;
    }
    public void setMagic(int magic) {
        this.magic = magic;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
        this.health = endurance * 10;
    }

    public int getHealth() {
        return health;
    }

    public int getAgility(){
        return agility;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getEndurance() {
        return endurance;
    }

    public int getSinPoints() { return sinPoints; }
    public void setSinPoints(int sinPoints) { this.sinPoints += sinPoints; }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        checkLevelUp();
    }

}
