package Ejercicio1;

import Ejercicio1.Animal;

// Subclase Ejercicio1.Perro que hereda de Ejercicio1.Animal y es final
public class Perro extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("El perro dice: Guau Guau");
    }
}