public class Main {
    public static void main(String[] args) {
        // Instanciamos objetos de cada subclase
        Animal animal = new Animal();
        Animal miPerro = new Perro();
        Animal miGato = new Gato();

        // Llamamos al método hacerSonido en cada objeto
        animal.hacerSonido();
        miPerro.hacerSonido();
        miGato.hacerSonido();
    }
}