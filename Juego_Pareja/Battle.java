package Juego_Pareja;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static Juego_Pareja.Helpers.GUI.multipleChoice;
import static Juego_Pareja.Helpers.GUI.showBattleText;

public class Battle {
    // Función que inicia la batalla y devuelve si el jugador ganó (true) o perdió (false)
    public static boolean startBattle(Character character, Enemy enemy) {
        Random rand = new Random();

        System.out.println("The battle begins!");

        // Bucle para procesar la batalla
        while (character.isAlive() && enemy.isAlive()) {
            // Turno del jugador
            if (!character.isStunned()){
                showBattleText(character.getName() + "'s turn:", true, true);

                int action;
                // Validación de entrada
                do {
                    action = multipleChoice(new String[]{"Attack", "Use Skill", "Dodge", "Block", "Use Potion", "Show Inventory"}, "Battle actions: ", false);
                } while (action < 1 || action > 6);

                // Procesar la respuesta del jugador
                switch (action) {
                    case 1 -> enemy.receiveDamage(character.attack()); // Ataque básico
                    case 2 -> enemy.receiveDamage(character.useAbility()); // Usar habilidad especial
                    case 3 -> { // Esquivar
                        if (rand.nextInt(100) < character.getAgility() * 10) {
                            showBattleText("You dodged the enemy's attack!",false, false);
                            continue; // El enemigo no ataca en este turno
                        } else {
                            showBattleText("You tried to dodge but failed.",false, false);
                        }
                    }
                    case 4 -> { // Bloquear
                        if (rand.nextInt(100) < character.getEndurance() * 8) {
                            showBattleText("You blocked the enemy's attack!",false, false);
                            enemy.showEnemyInfo();
                        } else {
                            showBattleText("You tried to block the enemy's attack but failed.",false, false);
                        }
                    }
                    case 5 -> { // Usar poción
                        List<Item> potionsInInventory = character.returnPotionList();
                        String[] potions = potionsInInventory.stream().map(Item::toString).toArray(String[]::new);
                        int itemChosen = multipleChoice(potions, "", false);

                        String itemName = potionsInInventory.get(itemChosen-1).getName();

                        if (character.hasItem(itemName)) {
                            character.useItem(itemName);
                            showBattleText(character.getName() + " used " + itemName + "!",false, false);
                        } else {
                            showBattleText(character.getName() + " does not have " + itemName + " in the inventory!",false, false);
                            continue;
                        }
                    }
                    case 6 -> { // Mostrar inventario
                        List<Item> potionsInInventory = character.returnPotionList();
                        String inventoryString = "INVENTORY:\n" + potionsInInventory.stream().map(Item::toString).collect(Collectors.joining("\n"));
                        showBattleText(inventoryString, false, false);
                        continue; // Saltar al siguiente turno del jugador sin atacar
                    }
                }

                // Mostrar la información del enemigo después del ataque del jugador
                enemy.showEnemyInfo();

                // Comprobar si el enemigo sigue con vida después del turno del jugador
                if (!enemy.isAlive()) {
                    showBattleText("You won the battle!\n" + character.getName() + " gained 5 experience points!",true, false);
                    character.setExp(character.getExp() + 5);
                    character.resetState();
                    return true; // El jugador ganó
                }
            } else {
                // Si está aturdido, perderá este turno
                showBattleText(character.getName() + " is stunned and loses this turn!",false, false);
                character.resetState();  // El aturdimiento solo dura un turno
            }

            // Turno del enemigo
            showBattleText("Enemy's turn:", true, true);
            if (rand.nextInt(100) > 90) {
                showBattleText("The enemy deals you a critical hit", false, false);
                character.receiveDamage(enemy.attack() * 2); // Golpe crítico
            } else {
                showBattleText("The enemy hits you", false, false);
                character.receiveDamage(enemy.attack()); // Ataque normal
            }
            // Aplicar posibles estados tras el ataque del enemigo
            int stateChance = rand.nextInt(100);
            if (stateChance < 10) { // 10% de probabilidad de quedar aturdido
                character.applyState("Stunned");
                showBattleText(character.getName() + " has been stunned!", false, false);
            } else if (stateChance < 15) { // 10% de probabilidad de ser envenenado
                character.applyState("Poisoned");
                showBattleText(character.getName() + " has been poisoned!", false, false);
            }

            // Si el personaje está envenenado recibe daño por veneno
            if (character.isPoisoned()){
                character.receiveDamage(2);
                showBattleText(character.getName() + " takes 2 poison damage.", false, false);
            }

            // Mostrar la información del jugador después del turno del enemigo
            character.showCharacterInfo();

            // Comprobar si el jugador sigue con vida después del ataque del enemigo
            if (!character.isAlive()) {
                showBattleText("You have been defeated!", false, false);
                return false; // El jugador perdió
            }
        }

        return false; // El jugador perdió si salió del bucle sin ganar
    }
}
