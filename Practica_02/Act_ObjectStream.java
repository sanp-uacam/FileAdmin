import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Act_ObjectStream {

    private static final String ARCHIVO = "AsistenciaObjetos.dat";

    // Lee la lista completa de objetos Alumno
    @SuppressWarnings("unchecked")
    public static List<Alumno> leerAlumnos() {
        File archivo = new File(ARCHIVO);

        // Si el archivo no existe aún, regresamos una lista vacía
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Alumno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo de objetos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Guarda un nuevo alumno en el archivo
    public static void guardarAlumno(Alumno nuevoAlumno) {
        // 1. Leemos la lista actual
        List<Alumno> lista = leerAlumnos();

        // 2. Agregamos el nuevo alumno
        lista.add(nuevoAlumno);

        // 3. Sobreescribimos el archivo con la lista actualizada
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Error al guardar el objeto: " + e.getMessage());
        }
    }
}