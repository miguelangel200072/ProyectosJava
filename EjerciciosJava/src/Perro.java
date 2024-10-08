// Subclase Perro que hereda de Animal y es final
final class Perro extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("El perro dice: Guau Guau");
    }
}