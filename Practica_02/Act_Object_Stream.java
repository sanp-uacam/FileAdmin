package Practica_02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Act_Object_Stream {

    // Obtener la ruta correcta del archivo object_data.txt
    private static File obtenerArchivo() {
        File f1 = new File("object_data.txt");
        if (f1.exists()) {
            return f1;
        }
        File f2 = new File("Practica_02/object_data.txt");
        if (f2.exists()) {
            return f2;
        }
        return f1;
    }

    // Funcionalidad 1: Guardar un nuevo alumno en la asistencia
    public static void agregarAlumnoAsitencia(String nombre) {
        // Validamos que no contenga números
        for (int i = 0; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i))) {
                System.out.println("Error: El nombre no puede contener números.");
                return;
            }
        }

        ArrayList<String> lista = leerListaAlumnos();
        lista.add(nombre);
        guardarListaAlumnos(lista);
        System.out.println("Alumno '" + nombre + "' agregado a la lista de asistencia.");
    }

    // Funcionalidad 2: Imprimir la lista de alumnos y el total de asistencia
    public static void verVerAlumno() {
        ArrayList<String> lista = leerListaAlumnos();

        if (lista.isEmpty()) {
            System.out.println("No hay alumnos registrados en la lista de asistencia.");
            return;
        }

        System.out.println("\n===== LISTA DE ALUMNOS =====");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
        System.out.println("Total de asistencia: " + lista.size());
    }

    // Método para leer la lista de alumnos desde el archivo
    @SuppressWarnings("unchecked")
    public static ArrayList<String> leerListaAlumnos() {
        File archivo = obtenerArchivo();
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (InputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Si el archivo tiene los encabezados del ejemplo (boolean y UTF), los leemos
            try {
                ois.readBoolean();
                ois.readUTF();
            } catch (Exception e) {
                // Si no los tiene, continuamos directamente
            }

            ArrayList<String> lista = (ArrayList<String>) ois.readObject();
            return lista;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aviso al leer archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para guardar la lista de alumnos en el archivo
    public static void guardarListaAlumnos(ArrayList<String> lista) {
        File archivo = obtenerArchivo();
        try (OutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Guardamos con la misma estructura que usó el profesor
            oos.writeBoolean(true);
            oos.writeUTF("Hola Mundo!");
            oos.writeObject(lista);

        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public static void escribirObjectoTryWith(){
        try (OutputStream fos = new FileOutputStream("object_data.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            ArrayList<String> listaAsistencia = new ArrayList<>();
            listaAsistencia.add("Arturo");
            listaAsistencia.add("Cecilia");
            listaAsistencia.add("Diego");
            listaAsistencia.add("Fernando");

            oos.writeBoolean(true);
            oos.writeUTF("Hola Mundo!");
            oos.writeObject(listaAsistencia);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void leerObjectoTryWith(){
        try (InputStream fis = new FileInputStream("object_data.txt");
             ObjectInputStream ois = new ObjectInputStream(fis);){

            System.out.println(ois.readBoolean());
            System.out.println(ois.readUTF());
            ArrayList<String> ListaAsistenciaInput = (ArrayList<String>) ois.readObject();
            System.out.println("Total de Estudiantes: "+ListaAsistenciaInput.size());
        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
