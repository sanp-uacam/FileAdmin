package Practica_3;

// Jaime Michel Garcia Sostenes
import java.io.*;
import java.util.ArrayList;

public class GestionAlumnos {

    // Método base proporcionado para reiniciar la BD
    public static void escribirAlumnos() {
        var alumnos = new ArrayList<Alumno>();
        alumnos.add(new Alumno("5799", "Sergio", 9.7));
        alumnos.add(new Alumno("3466", "Diego", 9.0));
        alumnos.add(new Alumno("2388", "Fernando", 8.5));
        alumnos.add(new Alumno("2388", "Jaime", 10.0));

        try (var fos = new FileOutputStream("AlumnosDB.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(alumnos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void leerAlumnos() {
        System.out.println("\n=== LISTA DE ALUMNOS ===");
        try (var fis = new FileInputStream("AlumnosDB.txt");
             var ois = new ObjectInputStream(fis)) {

            ArrayList<Alumno> alumnos = (ArrayList<Alumno>) ois.readObject();
            if (alumnos.isEmpty()) {
                System.out.println("La base de datos está vacía.");
            } else {
                for (Alumno alumno : alumnos) {
                    System.out.println(alumno);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe aún.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Alumno buscarAlumnoPorMatricula(String matricula) {
        try (var fis = new FileInputStream("AlumnosDB.txt");
             var ois = new ObjectInputStream(fis)) {

            ArrayList<Alumno> alumnos = (ArrayList<Alumno>) ois.readObject();
            for (Alumno alumno : alumnos) {
                if (alumno.getMatricula().equals(matricula)) {
                    return alumno;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // Funcionalidad 3 + Reto 4 (Validaciones)
    @SuppressWarnings("unchecked")
    public static void agregarAlumno(Alumno nuevoAlumno) {
        // Validación 1: Promedio entre 0 y 10
        if (nuevoAlumno.getPromedio() < 0 || nuevoAlumno.getPromedio() > 10) {
            System.out.println("❌ Error: El promedio debe estar entre 0 y 10.");
            return;
        }

        var alumnos = new ArrayList<Alumno>();
        try (var fis = new FileInputStream("AlumnosDB.txt");
             var ois = new ObjectInputStream(fis)) {
            alumnos = (ArrayList<Alumno>) ois.readObject();
        } catch (Exception e) {
            // Ignorar si el archivo no existe, iniciará con lista vacía
        }

        // Validación 2: Matrícula repetida
        for (Alumno a : alumnos) {
            if (a.getMatricula().equals(nuevoAlumno.getMatricula())) {
                System.out.println("❌ Error: La matrícula " + nuevoAlumno.getMatricula() + " ya existe.");
                return;
            }
        }

        alumnos.add(nuevoAlumno);

        try (var fos = new FileOutputStream("AlumnosDB.txt");
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(alumnos);
            System.out.println("✅ Alumno agregado correctamente.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reto 1: Eliminar alumno
    @SuppressWarnings("unchecked")
    public static boolean eliminarAlumno(String matricula) {
        var alumnos = new ArrayList<Alumno>();
        try (var fis = new FileInputStream("AlumnosDB.txt");
             var ois = new ObjectInputStream(fis)) {
            alumnos = (ArrayList<Alumno>) ois.readObject();
        } catch (Exception e) {
            return false;
        }

        boolean eliminado = alumnos.removeIf(a -> a.getMatricula().equals(matricula));

        if (eliminado) {
            try (var fos = new FileOutputStream("AlumnosDB.txt");
                 var oos = new ObjectOutputStream(fos)) {
                oos.writeObject(alumnos);
                System.out.println("✅ Alumno eliminado correctamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return eliminado;
    }

    // Reto 2: Calcular promedio grupal
    @SuppressWarnings("unchecked")
    public static double promedioGrupal() {
        try (var fis = new FileInputStream("AlumnosDB.txt");
             var ois = new ObjectInputStream(fis)) {
            ArrayList<Alumno> alumnos = (ArrayList<Alumno>) ois.readObject();
            if (alumnos.isEmpty()) return 0.0;

            double suma = 0;
            for (Alumno a : alumnos) {
                suma += a.getPromedio();
            }
            return suma / alumnos.size();
        } catch (Exception e) {
            return 0.0;
        }
    }

    // Reto 3: Alumno con mejor promedio
    @SuppressWarnings("unchecked")
    public static Alumno mejorPromedio() {
        try (var fis = new FileInputStream("AlumnosDB.txt");
             var ois = new ObjectInputStream(fis)) {
            ArrayList<Alumno> alumnos = (ArrayList<Alumno>) ois.readObject();
            if (alumnos.isEmpty()) return null;

            Alumno mejor = alumnos.get(0);
            for (Alumno a : alumnos) {
                if (a.getPromedio() > mejor.getPromedio()) {
                    mejor = a;
                }
            }
            return mejor;
        } catch (Exception e) {
            return null;
        }
    }
}