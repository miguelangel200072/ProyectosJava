package Ejercicio6;

public class Main {
    public static void main(String[] args) {
        //Instanciar ambas clases
        EmpleadoTiempoCompleto empleadoTiempoCompleto = new EmpleadoTiempoCompleto(23.50);
        EmpleadoTiempoParcial empleadoTiempoParcial = new EmpleadoTiempoParcial(22.32, 4.5);
        //Llamar a los métodos
        System.out.println(empleadoTiempoCompleto.calcularSalario() + "$");
        System.out.println(empleadoTiempoParcial.calcularSalario() + "$");
    }
}
