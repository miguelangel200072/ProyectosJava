package Ejercicio3;
// Subclase Rectangulo que extiende Figura
public class Rectangulo extends Figura{
    private double anchura;
    private double altura;

    // Constructor para inicializar ancho y alto
    public Rectangulo(double anchura, double altura) {
        this.anchura = anchura;
        this.altura = altura;
    }

    // Implementación del método calcularArea
    @Override
    public double calcularArea() {
        return anchura * altura;
    }
}
