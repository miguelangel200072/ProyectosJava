package Ejercicio1;

import Ejercicio1.Animal;

// Subclase Ejercicio1.Gato que hereda de Ejercicio1.Animal y es final
public class Gato extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("El gato dice: Miau Miau");
    }
}