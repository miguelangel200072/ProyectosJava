package Ejercicio6;

public class EmpleadoTiempoCompleto extends Empleado{
    private double salarioHora;
    public EmpleadoTiempoCompleto(Double salarioHora){
        this.salarioHora = salarioHora;
    }
    @Override
    double calcularSalario() {
        return salarioHora * 8 * 30;
    }
}
