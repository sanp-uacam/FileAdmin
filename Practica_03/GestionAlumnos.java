package Practica_03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionAlumnos {

    // Método para obtener el archivo AlumnosDB.txt según el directorio de ejecución
    private static File obtenerArchivo() {
        File f1 = new File("AlumnosDB.txt");
        if (f1.exists()) {
            return f1;
        }
        File f2 = new File("Practica_03/AlumnosDB.txt");
        if (f2.exists()) {
            return f2;
        }
        File carpeta = new File("Practica_03");
        if (carpeta.isDirectory()) {
            return new File("Practica_03/AlumnosDB.txt");
        }
        return f1;
    }

    // Método para crear la base de datos inicial con 4 alumnos
    public static void escribirAlumnos() {
        var alumnos = new ArrayList<Alumno>();
        alumnos.add(new Alumno("5799", "Sergio", 9.7));
        alumnos.add(new Alumno("3466", "Diego", 9.0));
        alumnos.add(new Alumno("2388", "Fernando", 8.5));
        alumnos.add(new Alumno("2388", "Jaime", 10.0));

        guardarAlumnos(alumnos);
    }

    // Método auxiliar para escribir la lista de alumnos en el archivo binario
    private static void guardarAlumnos(ArrayList<Alumno> alumnos) {
        File archivo = obtenerArchivo();
        try (var fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(alumnos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // FUNCIONALIDAD 1: Leer y mostrar todos los alumnos
    public static ArrayList<Alumno> leerAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        File archivo = obtenerArchivo();

        try (var fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Leemos el objeto del archivo y lo convertimos a la lista de alumnos
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
        // Recorremos la lista e imprimimos cada alumno
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    // FUNCIONALIDAD 2: Buscar un alumno por matrícula
    public static Alumno buscarAlumnoPorMatricula(String matricula) {
        ArrayList<Alumno> alumnos = leerAlumnos();

        // Recorremos la lista buscando coincidencia de matrícula
        for (Alumno alumno : alumnos) {
            if (alumno.getMatricula().equals(matricula)) {
                return alumno;
            }
        }
        return null;
    }

    // Alias para compatibilidad con instrucciones de Classroom
    public static Alumno buscarPorMatricula(String mat) {
        return buscarAlumnoPorMatricula(mat);
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

    // FUNCIONALIDAD 3: Agregar un nuevo alumno y guardar
    // RETO 4: Validaciones (promedio entre 0 y 10, matrícula no repetida)
    public static void agregarAlumno(Alumno nuevo) {
        if (nuevo == null) {
            return;
        }

        // Validación 1: El promedio debe estar entre 0 y 10
        if (nuevo.getPromedio() < 0.0 || nuevo.getPromedio() > 10.0) {
            System.out.println("❌ Error: El promedio debe estar entre 0 y 10.");
            return;
        }

        // Validación 2: La matrícula no debe estar repetida
        if (buscarAlumnoPorMatricula(nuevo.getMatricula()) != null) {
            System.out.println("❌ Error: La matrícula '" + nuevo.getMatricula() + "' ya está registrada.");
            return;
        }

        ArrayList<Alumno> alumnos = leerAlumnos();
        alumnos.add(nuevo);
        guardarAlumnos(alumnos);
        System.out.println("✅ Alumno agregado correctamente.");
    }

    // Alias para compatibilidad con instrucciones de Classroom
    public static void AgregarAlumno(Alumno nuevo) {
        agregarAlumno(nuevo);
    }

    // RETO 1: Eliminar alumno
    public static boolean eliminarAlumno(String matricula) {
        ArrayList<Alumno> alumnos = leerAlumnos();
        Alumno alumnoAEliminar = null;

        // Buscamos el alumno con la matrícula especificada
        for (Alumno alumno : alumnos) {
            if (alumno.getMatricula().equals(matricula)) {
                alumnoAEliminar = alumno;
                break;
            }
        }

        if (alumnoAEliminar != null) {
            alumnos.remove(alumnoAEliminar);
            guardarAlumnos(alumnos);
            System.out.println("✅ Alumno con matrícula '" + matricula + "' eliminado correctamente.");
            return true;
        } else {
            System.out.println("❌ No se encontró ningún alumno con la matrícula: " + matricula);
            return false;
        }
    }

    // RETO 2: Calcular promedio grupal
    public static double promedioGrupal() {
        ArrayList<Alumno> alumnos = leerAlumnos();
        if (alumnos.isEmpty()) {
            return 0.0;
        }

        double suma = 0.0;
        for (Alumno alumno : alumnos) {
            suma += alumno.getPromedio();
        }

        return suma / alumnos.size();
    }

    // RETO 3: Alumno con mejor promedio
    public static Alumno mejorPromedio() {
        ArrayList<Alumno> alumnos = leerAlumnos();
        if (alumnos.isEmpty()) {
            return null;
        }

        Alumno mejor = alumnos.get(0);
        for (Alumno alumno : alumnos) {
            if (alumno.getPromedio() > mejor.getPromedio()) {
                mejor = alumno;
            }
        }

        return mejor;
    }
}