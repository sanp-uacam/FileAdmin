package Practica_03;
import java.io.Serializable;

public class Alumno implements Serializable {
    // Propiedades
    private String matricula;
    private String nombre;
    private double promedio;

    // Constructor vacío
    public Alumno() {
    }

    // Constructor con parámetros
    public Alumno(String matricula, String nombre, double promedio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    // Getters y Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    // Método toString para mostrar la información del alumno
    @Override
    public String toString() {
        return "Alumno{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", promedio=" + promedio +
                '}';
    }
}