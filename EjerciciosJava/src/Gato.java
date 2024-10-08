// Subclase Gato que hereda de Animal y es final
final class Gato extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("El gato dice: Miau Miau");
    }
}