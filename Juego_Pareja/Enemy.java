package Juego_Pareja;

public class Enemy {
    private final String name;
    private int health;
    private int strength;

    // Constructor
    public Enemy(String name, int health, int strength) {
        this.name = name;
        this.health = health;
        this.strength = strength;
    }

    // Ataque del enemigo
    public int attack() {
        return strength;
    }

    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;  // La salud no debe ser negativa
        }
        System.out.println(name + " receives " + damage + " points of damage.");
    }

    // Mostrar información del enemigo
    public void showEnemyInfo() {
        System.out.println("\nEnemy status: " + name);
        System.out.println("Health: " + health);
        System.out.println("Strength: " + strength);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
