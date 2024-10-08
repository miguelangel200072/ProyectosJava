package Ejercicio4;
// Clase coche que implementa la interafaz Vehiculo
public class Coche implements Vehiculo{
    @Override
    public void acelerar(){
        System.out.println("Acelerando...");
    }

    @Override
    public void frenar() {
        System.out.println("Frenando...");
    }
}
