package Practica_03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class GestionAlumnos {

    private static final String ARCHIVO = "AlumnosDB.txt";

    public static void escribirAlumnos(){
        var alumnos = new ArrayList<Alumno>();
        alumnos.add(new Alumno("5799", "Sergio",9.7));
        alumnos.add(new Alumno("3466", "Diego",9));
        alumnos.add(new Alumno("2388", "Fernando",8.5));
        alumnos.add(new Alumno("2388", "Jaime",10));

        try (var fos = new FileOutputStream(ARCHIVO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(alumnos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    @SuppressWarnings("unchecked") // Soluciona la advertencia de conversión de tipos
    public static ArrayList<Alumno> leerAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        try (var fis = new FileInputStream(ARCHIVO);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            //---IMPLEMENTACION
            alumnos = (ArrayList<Alumno>) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Generando base de datos...");
            escribirAlumnos();
            return leerAlumnos();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return alumnos;
    }

    
    public static void mostrarAlumnos() {
        ArrayList<Alumno> alumnos = leerAlumnos();
        System.out.println("===== LISTA DE ALUMNOS =====");
        
        
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    // FUNCIONALIDAD 2: Buscar un alumno por matrícula
    public static Alumno buscarAlumnoPorMatricula(String matricula) {
        ArrayList<Alumno> alumnos = leerAlumnos();

        //---IMPLEMENTACION
        for (Alumno alumno : alumnos) {
            if (alumno.getMatricula().equals(matricula)) {
                return alumno; // Retorna el primer alumno encontrado
            }
        }
        return null; 
    }

    public static void mostrarBusqueda(String matricula) {
        System.out.println("===== BÚSQUEDA POR MATRÍCULA: " + matricula + " =====");
        Alumno encontrado = buscarAlumnoPorMatricula(matricula);

        if (encontrado != null) {
            System.out.println("Alumno encontrado: " + encontrado + "\n");
        } else {
            System.out.println("No se encontró ningún alumno con esa matrícula.\n");
        }
    }

    
    public static void agregarAlumno(Alumno nuevo) {
        ArrayList<Alumno> alumnos = leerAlumnos();
        alumnos.add(nuevo);

        try (var fos = new FileOutputStream(ARCHIVO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            //---IMPLEMENTACION
            oos.writeObject(alumnos);
            System.out.println("Alumno agregado correctamente.");
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}