package Ejercicio6;

public class EmpleadoTiempoParcial extends Empleado{
    private double salarioHora;
    private double horasTrabajadas;

    public EmpleadoTiempoParcial(double salarioHora, double horasTrabajadas){
        this.salarioHora = salarioHora;
        this.horasTrabajadas = horasTrabajadas;
    }
    @Override
    double calcularSalario() {
        return salarioHora * horasTrabajadas * 30;
    }
}
