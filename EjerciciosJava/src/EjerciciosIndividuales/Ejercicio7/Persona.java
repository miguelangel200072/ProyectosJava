package Ejercicio7;

public class Persona {
    private int id;
    private String nombre;

    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //Método para mostrar tanto el id como el nombre de la persona
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre;
    }


}
