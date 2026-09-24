import java.io.Serializable;

public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L; // Buena práctica en serialización
    private String nombre;

    public Alumno(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Alumno: " + nombre;
    }
}